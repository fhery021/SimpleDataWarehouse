package com.example.simpledatawarehouse.service;

import com.example.simpledatawarehouse.domain.RegularDimensionEntity;

import java.util.Optional;

public interface RegularDimensionService {

    Optional<RegularDimensionEntity> findExample(RegularDimensionEntity entity);

    RegularDimensionEntity save(RegularDimensionEntity regularDimension);
}
