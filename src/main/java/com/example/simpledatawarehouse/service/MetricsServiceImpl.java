package com.example.simpledatawarehouse.service;

import com.example.simpledatawarehouse.domain.MetricsEntity;
import com.example.simpledatawarehouse.domain.RegularDimensionEntity;
import com.example.simpledatawarehouse.domain.TimeDimensionEntity;
import com.example.simpledatawarehouse.repository.MetricsRepository;
import com.example.simpledatawarehouse.repository.RegularDimensionRepository;
import com.example.simpledatawarehouse.repository.TimeDimensionRepository;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MetricsServiceImpl implements MetricsService {

    private final RegularDimensionService regularDimensionService;
    private final TimeDimensionService timeDimensionService;
    private final MetricsRepository metricsRepository;


    public MetricsServiceImpl(RegularDimensionService regularDimensionService, TimeDimensionService timeDimensionService, MetricsRepository metricsRepository) {
        this.regularDimensionService = regularDimensionService;
        this.timeDimensionService = timeDimensionService;
        this.metricsRepository = metricsRepository;
    }

    @Override
    public MetricsEntity save(MetricsEntity metricsEntity) {
        // TODO MetricsSaveResponse
        RegularDimensionEntity regularDimension = saveRegularDimensionIfNew(metricsEntity.getRegularDimensionEntity());
        metricsEntity.setRegularDimensionEntity(regularDimension);

        TimeDimensionEntity timeDimensionEntity = saveTimeDimensionEntityIfNew(metricsEntity.getTimeDimensionEntity());
        metricsEntity.setTimeDimensionEntity(timeDimensionEntity);

        return metricsRepository.save(metricsEntity);
    }

    @Override
    public List<MetricsEntity> findAll() {
        return metricsRepository.findAll();
    }

    // TODO SaveIfNew class with BaseEntity parameter
    private RegularDimensionEntity saveRegularDimensionIfNew(RegularDimensionEntity regularDimension) {
        Optional<RegularDimensionEntity> regularDimensionFound = regularDimensionService.findExample(regularDimension);
        if (regularDimensionFound.isEmpty()) {
            return regularDimensionService.save(regularDimension);
        } else {
            return regularDimensionFound.orElseThrow();
        }
    }

    private TimeDimensionEntity saveTimeDimensionEntityIfNew(TimeDimensionEntity timeDimensionEntity) {
        Optional<TimeDimensionEntity> timeDimensionEntityFound = timeDimensionService.findExample(timeDimensionEntity);
        if (timeDimensionEntityFound.isEmpty()) {
            return timeDimensionService.save(timeDimensionEntity);
        } else {
            return timeDimensionEntityFound.orElseThrow();
        }
    }
}
