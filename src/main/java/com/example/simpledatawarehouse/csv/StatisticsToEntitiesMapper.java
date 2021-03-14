package com.example.simpledatawarehouse.csv;

import com.example.simpledatawarehouse.domain.MetricsEntity;
import com.example.simpledatawarehouse.domain.RegularDimensionEntity;
import com.example.simpledatawarehouse.domain.TimeDimensionEntity;
import org.springframework.stereotype.Component;

@Component
public class StatisticsToEntitiesMapper {

    private MetricsEntity metricsEntity;

    public MetricsEntity mapToMetrics(Statistics statistics) {
        RegularDimensionEntity regularDimensionEntity = RegularDimensionEntity.builder()
                .datasource(statistics.getDatasource())
                .campaign(statistics.getCampaign())
                .build();
        TimeDimensionEntity timeDimensionEntity = TimeDimensionEntity.builder()
                .date(statistics.getDaily())
                .build();
        metricsEntity = MetricsEntity.builder()
                .clicks(statistics.getClicks())
                .impressions(statistics.getImpressions())
                .regularDimensionEntity(regularDimensionEntity)
                .timeDimensionEntity(timeDimensionEntity)
                .build();
        return metricsEntity;
    }

    public MetricsEntity getMetricsEntity() {
        return metricsEntity;
    }
}
