package com.example.simpledatawarehouse.service;

import com.example.simpledatawarehouse.csv.Statistics;
import com.example.simpledatawarehouse.domain.StatisticsEntity;

import java.util.Collection;
import java.util.List;

public interface StatisticsService {

    void saveStatistics(Statistics statistics);

    List<StatisticsEntity> findAll();
}
