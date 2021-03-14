package com.example.simpledatawarehouse.service;

import com.example.simpledatawarehouse.controller.request.MetricsRequest;
import com.example.simpledatawarehouse.domain.MetricsEntity;
import org.springframework.http.converter.json.MappingJacksonValue;

import java.util.List;

public interface MetricsService {

    MetricsEntity save(MetricsEntity metricsEntity);

    MappingJacksonValue findAll(MetricsRequest metricsRequest);

}
