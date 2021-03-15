package com.example.simpledatawarehouse.mapper;

import com.example.simpledatawarehouse.controller.response.StatisticsResponse;
import com.example.simpledatawarehouse.domain.StatisticsEntity;
import org.springframework.stereotype.Component;

@Component
public class StatisticsEntityToResponseMapper {

    public StatisticsResponse entityToResponse(StatisticsEntity entity) {
        return StatisticsResponse.builder()
                .datasource(entity.getDatasource())
                .campaign(entity.getCampaign())
                .daily(entity.getDaily())
                .clicks(entity.getClicks())
                .impressions(entity.getImpressions())
                .ctr(calculateCtr(entity.getClicks(), entity.getImpressions()))
                .build();
    }

    private Double calculateCtr(Integer clicks, Long impressions) {
        return (double) clicks / impressions;
    }
}
