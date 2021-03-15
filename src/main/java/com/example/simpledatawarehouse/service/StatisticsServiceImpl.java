package com.example.simpledatawarehouse.service;

import com.example.simpledatawarehouse.csv.Statistics;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StatisticsServiceImpl implements StatisticsService {


//
//    private Optional<RegularDimensionEntity> findExample(RegularDimensionEntity entity) {
//        ExampleMatcher exampleMatcher = ExampleMatcher.matching().withIgnorePaths("id", "metricsEntityList");
//        Example<RegularDimensionEntity> example = Example.of(entity, exampleMatcher);
//
//        return regularDimensionRepository.findOne(example);
//    }
    @Override
    public void saveStatistics(Statistics statistics) {
        
    }

}