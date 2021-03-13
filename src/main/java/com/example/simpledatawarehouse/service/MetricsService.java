package com.example.simpledatawarehouse.service;

import com.example.simpledatawarehouse.domain.MetricsEntity;

import java.util.List;

public interface MetricsService {

    MetricsEntity save(MetricsEntity metricsEntity);

    List<MetricsEntity> findAll();
}
