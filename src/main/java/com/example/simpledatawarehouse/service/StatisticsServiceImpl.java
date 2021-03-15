package com.example.simpledatawarehouse.service;

import com.example.simpledatawarehouse.controller.request.StatisticsRequest;
import com.example.simpledatawarehouse.controller.response.StatisticsPagedList;
import com.example.simpledatawarehouse.controller.response.StatisticsResponse;
import com.example.simpledatawarehouse.csv.Statistics;
import com.example.simpledatawarehouse.domain.StatisticsEntity;
import com.example.simpledatawarehouse.mapper.StatisticsEntityToResponseMapper;
import com.example.simpledatawarehouse.repository.StatisticsRepository;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class StatisticsServiceImpl implements StatisticsService {

    private final StatisticsRepository statisticsRepository;
    private final StatisticsEntityToResponseMapper mapper;

    public StatisticsServiceImpl(StatisticsRepository statisticsRepository, StatisticsEntityToResponseMapper mapper) {
        this.statisticsRepository = statisticsRepository;
        this.mapper = mapper;
    }

    //
//    private Optional<RegularDimensionEntity> findExample(RegularDimensionEntity entity) {
//        ExampleMatcher exampleMatcher = ExampleMatcher.matching().withIgnorePaths("id", "metricsEntityList");
//        Example<RegularDimensionEntity> example = Example.of(entity, exampleMatcher);
//
//        return regularDimensionRepository.findOne(example);
//    }
    @Override
    public void saveStatistics(Statistics statisticsEntry) {
        StatisticsEntity entity = StatisticsEntity.builder()
                .datasource(statisticsEntry.getDatasource())
                .campaign(statisticsEntry.getCampaign())
                .daily(statisticsEntry.getDaily())
                .clicks(statisticsEntry.getClicks())
                .impressions(statisticsEntry.getImpressions())
                .build();

        statisticsRepository.save(entity);
    }

    @Override
    public boolean noEntries() {
        return statisticsRepository.count() == 0;
    }

    @Override
    public List<StatisticsResponse> findAllByDateRange(StatisticsRequest request) {
        return statisticsRepository.findAllByDailyBetween(
                request.getFromDate(), request.getToDate())
                .stream()
                .map(mapper::entityToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public StatisticsPagedList findAll(PageRequest pageRequest) {
        Page<StatisticsEntity> page;
        page = statisticsRepository.findAll(pageRequest);

        return new StatisticsPagedList(page
                .getContent()
                .stream()
                .map(mapper::entityToResponse)
                .collect(Collectors.toList()),
                PageRequest.of(page.getPageable().getPageNumber(), page.getPageable().getPageSize()),
                page.getTotalElements()
        );
    }

    @Override
    public List<StatisticsResponse> findAll(StatisticsRequest request) {
        StatisticsEntityExampleMaker exampleMaker = new StatisticsEntityExampleMaker();
        Example<StatisticsEntity> example = exampleMaker.createExampleFrom(request);

        return statisticsRepository.findAll(example)
                .stream()
                .map(mapper::entityToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<StatisticsResponse> findAllByRangeAndDatasource(StatisticsRequest request) {
        return statisticsRepository.findAllByDailyBetweenAndDatasource(
                request.getFromDate(), request.getToDate(), request.getDatasourceFilter())
                .stream()
                .map(mapper::entityToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public Map<String, List<StatisticsResponse>> findAndGroupByDatasource(StatisticsRequest request) {
        StatisticsEntityExampleMaker exampleMaker = new StatisticsEntityExampleMaker();
        Example<StatisticsEntity> example = exampleMaker.createExampleFrom(request);

        return statisticsRepository.findAll(example)
                .stream()
                .map(mapper::entityToResponse)
                .collect(Collectors.groupingBy(StatisticsResponse::getDatasource));
    }

    @Override
    public Map<String, List<StatisticsResponse>> findAndGroupByCampaign(StatisticsRequest request) {
        StatisticsEntityExampleMaker exampleMaker = new StatisticsEntityExampleMaker();
        Example<StatisticsEntity> example = exampleMaker.createExampleFrom(request);

        return statisticsRepository.findAll(example)
                .stream()
                .map(mapper::entityToResponse)
                .collect(Collectors.groupingBy(StatisticsResponse::getCampaign));
    }

}