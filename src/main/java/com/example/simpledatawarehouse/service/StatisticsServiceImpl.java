package com.example.simpledatawarehouse.service;

import com.example.simpledatawarehouse.csv.Statistics;
import com.example.simpledatawarehouse.domain.MetricsEntity;
import com.example.simpledatawarehouse.domain.RegularDimensionEntity;
import com.example.simpledatawarehouse.domain.TimeDimensionEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatisticsServiceImpl implements StatisticsService {

    private final RegularDimensionService regularDimensionService;
    private final TimeDimensionService timeDimensionService;
    private final MetricsService metricsService;

    public StatisticsServiceImpl(RegularDimensionService regularDimensionService, TimeDimensionService timeDimensionService, MetricsService metricsService) {
        this.regularDimensionService = regularDimensionService;
        this.timeDimensionService = timeDimensionService;
        this.metricsService = metricsService;
    }


    @Override
    public void saveStatistics(Statistics statistics) {

        MetricsEntity metricsEntity = createMetrics(statistics);

        RegularDimensionEntity regularDimensionEntity = findOrCreateRegularDimension(statistics);
        TimeDimensionEntity timeDimensionEntity = findOrCreateTimeDimension(statistics);

        addMetricsToRegularDimension(metricsEntity, regularDimensionEntity);
        addMetricsToTimeDimension(metricsEntity, timeDimensionEntity);
    }

    private MetricsEntity createMetrics(Statistics statistics) {
        return metricsService.save(
                MetricsEntity.builder()
                        .clicks(statistics.getClicks())
                        .impressions(statistics.getImpressions())
                        .build());
    }

    private RegularDimensionEntity findOrCreateRegularDimension(Statistics statistics) {
        return regularDimensionService.saveIfNew(
                RegularDimensionEntity.builder()
                        .datasource(statistics.getDatasource())
                        .campaign(statistics.getCampaign())
                        .build());
    }

    private TimeDimensionEntity findOrCreateTimeDimension(Statistics statistics) {
        return timeDimensionService.saveIfNew(
                TimeDimensionEntity.builder()
                        .date(statistics.getDaily())
                        .build());
    }

    private void addMetricsToRegularDimension(MetricsEntity metricsEntity, RegularDimensionEntity regularDimensionEntity) {
        if (regularDimensionEntity.getMetricsEntityList() == null) {
            regularDimensionEntity.setMetricsEntityList(List.of(metricsEntity));
        } else {
            regularDimensionEntity.getMetricsEntityList().add(metricsEntity);
        }

        regularDimensionService.save(regularDimensionEntity);
    }

    private void addMetricsToTimeDimension(MetricsEntity metricsEntity, TimeDimensionEntity timeDimensionEntity) {
        if (timeDimensionEntity.getMetricsEntityList() == null) {
            timeDimensionEntity.setMetricsEntityList(List.of(metricsEntity));
        } else {
            timeDimensionEntity.getMetricsEntityList().add(metricsEntity);
        }

        timeDimensionService.save(timeDimensionEntity);
    }
}