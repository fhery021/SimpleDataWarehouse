package com.example.simpledatawarehouse.service;

import com.example.simpledatawarehouse.controller.request.MetricsRequest;
import com.example.simpledatawarehouse.controller.response.JsonMapper;
import com.example.simpledatawarehouse.controller.response.MetricsResponse;
import com.example.simpledatawarehouse.domain.MetricsEntity;
import com.example.simpledatawarehouse.domain.RegularDimensionEntity;
import com.example.simpledatawarehouse.domain.TimeDimensionEntity;
import com.example.simpledatawarehouse.repository.MetricsRepository;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class MetricsServiceImpl implements MetricsService {

    private final RegularDimensionService regularDimensionService;
    private final TimeDimensionService timeDimensionService;
    private final MetricsRepository metricsRepository;
    private final JsonMapper jsonMapper;

    public MetricsServiceImpl(RegularDimensionService regularDimensionService, TimeDimensionService timeDimensionService, MetricsRepository metricsRepository, JsonMapper jsonMapper) {
        this.regularDimensionService = regularDimensionService;
        this.timeDimensionService = timeDimensionService;
        this.metricsRepository = metricsRepository;
        this.jsonMapper = jsonMapper;
    }

    @Override
    public MetricsEntity save(MetricsEntity metricsEntity) {
        return metricsRepository.save(metricsEntity);
    }

    @Override
    public List<MetricsEntity> findAll() {
        return metricsRepository.findAll();
    }


    // TODO find an easier way
    // TODO Branch with simpler db structure
    @Override
    public List<String> findAll(MetricsRequest metricsRequest) {
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

        return jsonMapper.mapToResponse(metricsRequest, metricsResponses);
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


}
