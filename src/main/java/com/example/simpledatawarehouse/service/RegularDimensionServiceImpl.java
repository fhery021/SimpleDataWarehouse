package com.example.simpledatawarehouse.service;

import com.example.simpledatawarehouse.domain.RegularDimensionEntity;
import com.example.simpledatawarehouse.repository.RegularDimensionRepository;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RegularDimensionServiceImpl implements RegularDimensionService {

    private final RegularDimensionRepository regularDimensionRepository;

    public RegularDimensionServiceImpl(RegularDimensionRepository regularDimensionRepository) {
        this.regularDimensionRepository = regularDimensionRepository;
    }

    @Override
    public RegularDimensionEntity saveIfNew(RegularDimensionEntity entity) {
        Optional<RegularDimensionEntity> regularDimensionFound = findExample(entity);
        if (regularDimensionFound.isEmpty()) {
            return regularDimensionRepository.save(entity);
        } else {
            return regularDimensionFound.orElseThrow();
        }
    }

    @Override
    public List<RegularDimensionEntity> findByDatasourceAndCampaignFilter(String datasource, String campaign) {
        if (!emptyOrNull(datasource) && !emptyOrNull(campaign)) {
            return regularDimensionRepository.findByDatasourceAndCampaign(datasource, campaign);
        } else {
            // TODO attack here
            return regularDimensionRepository.findAll();
        }
    }

    private boolean emptyOrNull(String str) {
        return str == null || str.isEmpty();
    }

    private Optional<RegularDimensionEntity> findExample(RegularDimensionEntity entity) {
        ExampleMatcher exampleMatcher = ExampleMatcher.matching().withIgnorePaths("id");
        Example<RegularDimensionEntity> example = Example.of(entity, exampleMatcher);

        return regularDimensionRepository.findOne(example);
    }

}
