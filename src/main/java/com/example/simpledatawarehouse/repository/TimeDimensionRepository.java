package com.example.simpledatawarehouse.repository;

import com.example.simpledatawarehouse.domain.TimeDimensionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface TimeDimensionRepository extends JpaRepository<TimeDimensionEntity, Long> {

    List<TimeDimensionEntity> findByDateBetween(LocalDate fromDate, LocalDate toDate);

}
