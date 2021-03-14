package com.example.simpledatawarehouse.service;

import com.example.simpledatawarehouse.controller.request.MetricsRequest;
import com.example.simpledatawarehouse.controller.response.MetricsResponse;
import com.example.simpledatawarehouse.domain.MetricsEntity;
import com.example.simpledatawarehouse.domain.RegularDimensionEntity;
import com.example.simpledatawarehouse.domain.TimeDimensionEntity;
import com.example.simpledatawarehouse.mapper.MetricsMapper;
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
    private final MetricsMapper metricsMapper;

    public MetricsServiceImpl(RegularDimensionService regularDimensionService, TimeDimensionService timeDimensionService, MetricsRepository metricsRepository, MetricsMapper metricsMapper) {
        this.regularDimensionService = regularDimensionService;
        this.timeDimensionService = timeDimensionService;
        this.metricsRepository = metricsRepository;
        this.metricsMapper = metricsMapper;
    }

    @Override
    public MetricsEntity save(MetricsEntity metricsEntity) {
        // TODO MetricsSaveResponse
        RegularDimensionEntity regularDimension = regularDimensionService.saveIfNew(metricsEntity.getRegularDimensionEntity());
//        RegularDimensionEntity regularDimension = regularDimensionService.save(metricsEntity.getRegularDimensionEntity());
        metricsEntity.setRegularDimensionEntity(regularDimension);

        TimeDimensionEntity timeDimensionEntity = timeDimensionService.saveIfNew(metricsEntity.getTimeDimensionEntity());
//        TimeDimensionEntity timeDimensionEntity = timeDimensionService.save(metricsEntity.getTimeDimensionEntity());
        metricsEntity.setTimeDimensionEntity(timeDimensionEntity);

        return metricsRepository.save(metricsEntity);
    }

    @Override
    public List<MetricsEntity> findAll() {
        return metricsRepository.findAll();
    }

    @Override
    public MappingJacksonValue findAll(MetricsRequest metricsRequest) {
        List<TimeDimensionEntity> timeDimensions = timeDimensionService
                .findAllByDateFilterOrInterval(metricsRequest.getDateFilter(), metricsRequest.getFromDate(), metricsRequest.getToDate());

        List<RegularDimensionEntity> regularDimensions = regularDimensionService
                .findByDatasourceAndCampaignFilter(metricsRequest.getDatasourceFilter(), metricsRequest.getCampaignFilter());

        // TODO null checks?
        // TODO maybe it's better to throw all in one table?
        List<MetricsEntity> metricsEntities = new ArrayList<>();
        timeDimensions.forEach(td -> {
            regularDimensions.forEach(rd -> {
                // TODO GROUP BY DATASOURCE, CAMPAIGN, DATE
                metricsEntities.addAll(metricsRepository
                        .findAllByTimeAndRegularDimensionEntityId(td.getId(), rd.getId()));
            });
        });

        List<MetricsResponse> metricsResponses = metricsEntities.stream()
                .map(metricsMapper::metricsEntityToMetricsResponse).collect(Collectors.toList());

        SimpleBeanPropertyFilter simpleBeanPropertyFilter = SimpleBeanPropertyFilter.serializeAllExcept(metricsRequest.getFilterTypes());
        FilterProvider filterProvider = new SimpleFilterProvider().addFilter("metricsFilter",simpleBeanPropertyFilter);

        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(metricsResponses);
        mappingJacksonValue.setFilters(filterProvider);

        return mappingJacksonValue;
    }


}
