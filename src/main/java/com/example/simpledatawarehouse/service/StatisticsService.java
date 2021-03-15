package com.example.simpledatawarehouse.service;

import com.example.simpledatawarehouse.controller.request.StatisticsRequest;
import com.example.simpledatawarehouse.controller.response.StatisticsPagedList;
import com.example.simpledatawarehouse.controller.response.StatisticsResponse;
import com.example.simpledatawarehouse.csv.Statistics;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Map;

public interface StatisticsService {

    void saveStatistics(Statistics statistics);

    List<StatisticsResponse> findAll(StatisticsRequest request);

    List<StatisticsResponse> findAllByRangeAndDatasource(StatisticsRequest request);

    List<StatisticsResponse> findAllByDateRange(StatisticsRequest statisticsRequest);

    StatisticsPagedList findAll(PageRequest pageRequest);

    Map<String, List<StatisticsResponse>> findAndGroupByDatasource(StatisticsRequest request);

    Map<String, List<StatisticsResponse>> findAndGroupByCampaign(StatisticsRequest request);

    boolean noEntries();

}
