package com.example.simpledatawarehouse.service;

import com.example.simpledatawarehouse.domain.TimeDimensionEntity;

import java.time.LocalDate;
import java.util.List;

public interface TimeDimensionService {

    TimeDimensionEntity save(TimeDimensionEntity timeDimensionEntity);

    TimeDimensionEntity saveIfNew(TimeDimensionEntity timeDimensionEntity);

    List<TimeDimensionEntity> findAllByDateFilterOrInterval(LocalDate dateFilter, LocalDate fromDate, LocalDate toDate);
}
