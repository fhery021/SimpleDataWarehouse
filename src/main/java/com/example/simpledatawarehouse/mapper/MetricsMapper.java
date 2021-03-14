package com.example.simpledatawarehouse.mapper;

import com.example.simpledatawarehouse.controller.response.MetricsResponse;
import com.example.simpledatawarehouse.domain.MetricsEntity;
import org.springframework.stereotype.Component;

@Component
public class MetricsMapper {
//
//    public Example requestToExample(MetricsRequest request) {
//
//        request.getFromDate();
//        request.getToDate();
//
//        MetricsEntity entity = new MetricsEntity();
//        entity.set
//        ExampleMatcher exampleMatcher = ExampleMatcher.matching().withIgnorePaths("id");
//        Example<RegularDimensionEntity> example = Example.of(entity, exampleMatcher);
//
//
//        return example;
//    }

//    entityToResponse()


    /// MappingJacksonValue

    public MetricsResponse metricsEntityToMetricsResponse(MetricsEntity entity) {
        MetricsResponse response = MetricsResponse.builder()
                .dataSource(entity.getRegularDimensionEntity().getDatasource())
                .campaign(entity.getRegularDimensionEntity().getCampaign())
                .daily(entity.getTimeDimensionEntity().getDate())
                .clicks(entity.getClicks())
                .impressions(entity.getImpressions())
                .ctr(calculateCTR(entity.getClicks(), entity.getImpressions()))
                .build();

        return response;
    }


    private double calculateCTR(Integer clicks, Long impressions) {
        return clicks/impressions;
    }
}
