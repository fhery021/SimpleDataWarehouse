package com.example.simpledatawarehouse.repository;

import com.example.simpledatawarehouse.domain.StatisticsEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface StatisticsRepository extends JpaRepository<StatisticsEntity, Long> {

    List<StatisticsEntity> findAllByDailyBetweenAndDatasource(LocalDate fromDate, LocalDate toDate, String datasource);

    List<StatisticsEntity> findAllByDailyBetween(LocalDate fromDate, LocalDate toDate);

    Page<StatisticsEntity> findAll(Pageable pageable);
}
