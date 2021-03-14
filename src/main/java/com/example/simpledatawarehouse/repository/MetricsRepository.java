package com.example.simpledatawarehouse.repository;

import com.example.simpledatawarehouse.domain.MetricsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MetricsRepository extends JpaRepository<MetricsEntity, Long> {

}
