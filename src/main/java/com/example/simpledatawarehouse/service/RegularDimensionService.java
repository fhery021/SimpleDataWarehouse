package com.example.simpledatawarehouse.service;

import com.example.simpledatawarehouse.domain.RegularDimensionEntity;

import java.util.Optional;

public interface RegularDimensionService {

    RegularDimensionEntity saveIfNew(RegularDimensionEntity entity);
}
