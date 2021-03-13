package com.example.simpledatawarehouse.service;

import com.example.simpledatawarehouse.domain.TimeDimensionEntity;
import com.example.simpledatawarehouse.repository.TimeDimensionRepository;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TimeDimensionServiceImpl implements TimeDimensionService {

    private final TimeDimensionRepository timeDimensionRepository;

    public TimeDimensionServiceImpl(TimeDimensionRepository timeDimensionRepository) {
        this.timeDimensionRepository = timeDimensionRepository;
    }

    @Override
    public Optional<TimeDimensionEntity> findExample(TimeDimensionEntity entity) {
        ExampleMatcher exampleMatcher = ExampleMatcher.matching().withIgnorePaths("id");
        Example<TimeDimensionEntity> example = Example.of(entity, exampleMatcher);

        return timeDimensionRepository.findOne(example);
    }

    @Override
    public TimeDimensionEntity save(TimeDimensionEntity timeDimensionEntity) {
        return timeDimensionRepository.save(timeDimensionEntity);
    }
}
