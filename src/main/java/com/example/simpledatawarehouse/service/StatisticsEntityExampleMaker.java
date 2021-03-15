package com.example.simpledatawarehouse.service;

import com.example.simpledatawarehouse.controller.request.StatisticsRequest;
import com.example.simpledatawarehouse.domain.StatisticsEntity;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class StatisticsEntityExampleMaker {

    Example<StatisticsEntity> createExampleFrom(StatisticsRequest request) {
        StatisticsEntity entity = StatisticsEntity.builder().build();

        Set<String> ignorePaths = new HashSet<>(Arrays.asList("id", "datasource", "campaign", "daily", "clicks", "impressions"));

        if (notNullOrEmpty(request.getDatasourceFilter())) {
            entity.setDatasource(request.getDatasourceFilter());
            ignorePaths.remove("datasource");
        }

        if (notNullOrEmpty(request.getCampaignFilter())) {
            entity.setCampaign(request.getCampaignFilter());
            ignorePaths.remove("campaign");
        }

        if (request.getExactDayFilter() != null) {
            entity.setDaily(request.getExactDayFilter());
            ignorePaths.remove("daily");
        }

        if (request.getClicksFilter() != null) {
            entity.setClicks(request.getClicksFilter());
            ignorePaths.remove("clicks");
        }

        if (request.getImpressionsFilter() != null) {
            entity.setImpressions(request.getImpressionsFilter());
            ignorePaths.remove("impressions");
        }

        String[] ignorePathsArray = ignorePaths.toArray(new String[0]);
        ExampleMatcher exampleMatcher = ExampleMatcher.matching()
                .withIgnorePaths(ignorePathsArray);

        return Example.of(entity, exampleMatcher);
    }

    private boolean notNullOrEmpty(String str) {
        return str != null && !str.isEmpty();
    }
}
