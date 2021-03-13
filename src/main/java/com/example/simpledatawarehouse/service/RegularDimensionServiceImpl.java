package com.example.simpledatawarehouse.service;

import com.example.simpledatawarehouse.domain.RegularDimensionEntity;
import com.example.simpledatawarehouse.repository.RegularDimensionRepository;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RegularDimensionServiceImpl implements RegularDimensionService {

    private final RegularDimensionRepository regularDimensionRepository;

    public RegularDimensionServiceImpl(RegularDimensionRepository regularDimensionRepository) {
        this.regularDimensionRepository = regularDimensionRepository;
    }

    public Optional<RegularDimensionEntity> findExample(RegularDimensionEntity entity) {
        ExampleMatcher exampleMatcher = ExampleMatcher.matching().withIgnorePaths("id");
        Example<RegularDimensionEntity> example = Example.of(entity, exampleMatcher);

        return regularDimensionRepository.findOne(example);
    }

    @Override
    public RegularDimensionEntity save(RegularDimensionEntity regularDimension) {
        return regularDimensionRepository.save(regularDimension);
    }
}
