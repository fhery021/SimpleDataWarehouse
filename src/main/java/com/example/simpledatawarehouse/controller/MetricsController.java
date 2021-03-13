package com.example.simpledatawarehouse.controller;

import com.example.simpledatawarehouse.controller.request.RequestWrapper;
import com.example.simpledatawarehouse.controller.response.MetricsResponse;
import com.example.simpledatawarehouse.controller.response.PaginatedMetricsResponse;
import com.example.simpledatawarehouse.service.MetricsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/metrics")
public class MetricsController {

    private static final Integer DEFAULT_PAGE_NUMBER = 0;
    private static final Integer DEFAULT_PAGE_SIZE = 25;

    private final MetricsService metricsService;

    public MetricsController(MetricsService metricsService) {
        this.metricsService = metricsService;
    }

    // TODO 1. request all paginated
    // TODO 2. request with requestBody

    @GetMapping("/all")
    public ResponseEntity<PaginatedMetricsResponse> getMetrics(
            @RequestParam(value = "pageNumber", required = false) Integer pageNumber,
            @RequestParam(value = "pageSize", required = false) Integer pageSize,
            @RequestBody RequestWrapper requestWrapper
    ) {
//        return ResponseEntity.ok(metricsService.)
        return null;
    }

    @GetMapping("/date")
    public ResponseEntity<MetricsResponse> getMetricsForOneDay(@RequestBody RequestWrapper requestWrapper) {
//        return ResponseEntity.ok(metricsService.)
        return null;
    }

}
