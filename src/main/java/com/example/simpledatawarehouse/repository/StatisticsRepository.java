package com.example.simpledatawarehouse.repository;

import com.example.simpledatawarehouse.domain.StatisticsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface StatisticsRepository extends JpaRepository<StatisticsEntity, Long> {

    List<StatisticsEntity> findByDatasourceAndCampaign(String datasource, String campaign);
    List<StatisticsEntity> findByDateBetween(LocalDate fromDate, LocalDate toDate);
}
