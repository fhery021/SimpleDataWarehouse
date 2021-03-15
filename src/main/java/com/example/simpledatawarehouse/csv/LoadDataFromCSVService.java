package com.example.simpledatawarehouse.csv;

import com.example.simpledatawarehouse.service.StatisticsService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class LoadDataFromCSVService {

    private final CSVReaderService csvReaderService;
    private final StatisticsService statisticsService;
    public LoadDataFromCSVService(CSVReaderService csvReaderService, StatisticsService statisticsService) {
        this.csvReaderService = csvReaderService;
        this.statisticsService = statisticsService;
    }

    public void load() throws IOException {
        if (statisticsService.noEntries()) {
            List<Statistics> statisticsList = csvReaderService.processCSV();
            statisticsList.stream().forEach(statisticsService::saveStatistics);
        }
    }
}
