package com.example.simpledatawarehouse.service;

import com.example.simpledatawarehouse.controller.request.MetricsRequest;
import com.example.simpledatawarehouse.controller.response.MetricsResponse;
import com.example.simpledatawarehouse.domain.MetricsEntity;
import com.example.simpledatawarehouse.domain.RegularDimensionEntity;
import com.example.simpledatawarehouse.domain.TimeDimensionEntity;
import com.example.simpledatawarehouse.repository.MetricsRepository;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class MetricsServiceImpl implements MetricsService {

    private final RegularDimensionService regularDimensionService;
    private final TimeDimensionService timeDimensionService;
    private final MetricsRepository metricsRepository;

    public MetricsServiceImpl(RegularDimensionService regularDimensionService, TimeDimensionService timeDimensionService, MetricsRepository metricsRepository) {
        this.regularDimensionService = regularDimensionService;
        this.timeDimensionService = timeDimensionService;
        this.metricsRepository = metricsRepository;
    }

    @Override
    public MetricsEntity save(MetricsEntity metricsEntity) {
        // TODO MetricsSaveResponse
//        RegularDimensionEntity regularDimension = regularDimensionService.save(metricsEntity.getRegularDimensionEntity());
//        metricsEntity.setRegularDimensionEntity(regularDimension);
//
//        TimeDimensionEntity timeDimensionEntity = timeDimensionService.save(metricsEntity.getTimeDimensionEntity());
//        metricsEntity.setTimeDimensionEntity(timeDimensionEntity);

        return metricsRepository.save(metricsEntity);
    }

    @Override
    public List<MetricsEntity> findAll() {
        return metricsRepository.findAll();
    }

    // TODO find an easier way

    @Override
    public MappingJacksonValue findAll(MetricsRequest metricsRequest) {
        List<MetricsResponse> metricsResponses = new ArrayList<>();

        List<RegularDimensionEntity> foundInRegularDimension = findInRegularDimensions(metricsRequest);
        List<TimeDimensionEntity> foundInTimeDimension = findInTimeDimension(metricsRequest);

        Set<MetricsEntity> metricsFromRegularDimensions = foundInRegularDimension.stream()
                .map(RegularDimensionEntity::getMetricsEntityList)
                .flatMap(List::stream).collect(Collectors.toCollection(HashSet::new));

        Set<MetricsEntity> metricsFromTimeDimensions = foundInTimeDimension.stream()
                .map(TimeDimensionEntity::getMetricsEntityList)
                .flatMap(List::stream).collect(Collectors.toCollection(HashSet::new));

        Set<MetricsEntity> commonMetrics = metricsFromRegularDimensions.stream()
                .filter(metricsFromTimeDimensions::contains)
                .collect(Collectors.toSet());

        commonMetrics.forEach(metric -> {
            RegularDimensionEntity regularDimensionEntity = foundInRegularDimension.stream()
                    .filter(regDim -> regDim.getMetricsEntityList().contains(metric))
                    .findFirst()
                    .orElseThrow();
            TimeDimensionEntity timeDimensionEntity = foundInTimeDimension.stream()
                    .filter(timeDim -> timeDim.getMetricsEntityList().contains(metric))
                    .findFirst()
                    .orElseThrow();

            metricsResponses.add(MetricsResponse.builder()
                    .dataSource(regularDimensionEntity.getDatasource())
                    .campaign(regularDimensionEntity.getCampaign())
                    .daily(timeDimensionEntity.getDate())
                    .clicks(metric.getClicks())
                    .impressions(metric.getImpressions())
                    .ctr(calculateCtr(metric.getClicks(), metric.getImpressions()))
                    .build());
        });

        return jacksonValueOf(metricsRequest, metricsResponses);
    }

    private Double calculateCtr(Integer clicks, Long impressions) {
        return (double) clicks / impressions;
    }

    private List<TimeDimensionEntity> findInTimeDimension(MetricsRequest metricsRequest) {
        return timeDimensionService.findAllByDateFilterOrInterval(
                metricsRequest.getDateFilter(),
                metricsRequest.getFromDate(),
                metricsRequest.getToDate());
    }

    private List<RegularDimensionEntity> findInRegularDimensions(MetricsRequest metricsRequest) {
        return regularDimensionService.findByDatasourceAndCampaignFilter(
                metricsRequest.getDatasourceFilter(),
                metricsRequest.getCampaignFilter());
    }

    private MappingJacksonValue jacksonValueOf(MetricsRequest metricsRequest, List<MetricsResponse> metricsResponses) {
        SimpleBeanPropertyFilter simpleBeanPropertyFilter = SimpleBeanPropertyFilter.serializeAllExcept(
                metricsRequest.getFilterTypes());
        FilterProvider filterProvider = new SimpleFilterProvider().addFilter("metricsFilter", simpleBeanPropertyFilter);

        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(metricsResponses);
        mappingJacksonValue.setFilters(filterProvider);

        return mappingJacksonValue;
    }

}
