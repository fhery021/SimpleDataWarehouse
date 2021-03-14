package com.example.simpledatawarehouse.csv;

import com.example.simpledatawarehouse.domain.MetricsEntity;
import com.example.simpledatawarehouse.domain.RegularDimensionEntity;
import com.example.simpledatawarehouse.service.MetricsService;
import com.example.simpledatawarehouse.service.StatisticsService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class LoadDataFromCSVService {

    private final CSVReaderService csvReaderService;
    private final StatisticsService statisticsService;

    private final MetricsService metricsService;

    public LoadDataFromCSVService(CSVReaderService csvReaderService, StatisticsService statisticsService, MetricsService metricsService) {
        this.csvReaderService = csvReaderService;
        this.statisticsService = statisticsService;
        this.metricsService = metricsService;
    }

    public void load() throws IOException {
        if (metricsService.findAll().isEmpty()) {
            List<Statistics> statisticsList = csvReaderService.processCSV();

            statisticsList.stream().forEach(statisticsService::saveStatistics);
        }
    }
}
