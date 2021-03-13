package com.example.simpledatawarehouse.service;

import com.example.simpledatawarehouse.domain.TimeDimensionEntity;

import java.util.Optional;

public interface TimeDimensionService {

    Optional<TimeDimensionEntity> findExample(TimeDimensionEntity entity);

    TimeDimensionEntity save(TimeDimensionEntity timeDimensionEntity);
}
