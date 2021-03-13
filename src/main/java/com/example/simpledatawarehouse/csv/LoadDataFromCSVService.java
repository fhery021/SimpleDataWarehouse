package com.example.simpledatawarehouse.csv;

import com.example.simpledatawarehouse.domain.MetricsEntity;
import com.example.simpledatawarehouse.service.MetricsService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class LoadDataFromCSVService {

    private final CSVReaderService csvReaderService;
    private final StatisticsToEntitiesMapper statisticsToEntitiesMapper;

    private final MetricsService metricsService;

    public LoadDataFromCSVService(CSVReaderService csvReaderService, StatisticsToEntitiesMapper statisticsToEntitiesMapper, MetricsService metricsService) {
        this.csvReaderService = csvReaderService;
        this.statisticsToEntitiesMapper = statisticsToEntitiesMapper;
        this.metricsService = metricsService;
    }

    public void load() throws IOException {
        if (metricsService.findAll().isEmpty()) {
            List<Statistics> statisticsList = csvReaderService.processCSV();
            statisticsList.stream().forEach(statistic -> {
                MetricsEntity metric = statisticsToEntitiesMapper.mapToMetrics(statistic);
                metricsService.save(metric);
            });
        }
    }
}
