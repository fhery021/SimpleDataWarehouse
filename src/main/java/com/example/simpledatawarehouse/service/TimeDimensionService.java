package com.example.simpledatawarehouse.service;

import com.example.simpledatawarehouse.domain.TimeDimensionEntity;

public interface TimeDimensionService {

    TimeDimensionEntity saveIfNew(TimeDimensionEntity timeDimensionEntity);
}
