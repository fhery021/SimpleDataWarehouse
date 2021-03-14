package com.example.simpledatawarehouse.controller;

import com.example.simpledatawarehouse.controller.request.MetricsRequest;
import com.example.simpledatawarehouse.controller.response.MetricsResponse;
import com.example.simpledatawarehouse.controller.response.PaginatedMetricsResponse;
import com.example.simpledatawarehouse.service.MetricsService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

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
            @RequestBody MetricsRequest metricsRequest
    ) {
//        MappingJackson
//        return ResponseEntity.ok(metricsService.)
        return null;
    }

    @GetMapping("/date")
    public ResponseEntity<MetricsResponse> getMetricsForOneDay(
            @RequestParam(name = "date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
            @RequestBody MetricsRequest metricsRequest
    ) {
//        return ResponseEntity.ok(metricsService.)
        return null;
    }


    @GetMapping("/date/page")
    public ResponseEntity<MetricsResponse> getMetricsForOneDayPaginated(
            @RequestParam(name = "date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
            @RequestBody MetricsRequest metricsRequest,
            @RequestParam(value = "pageNumber", required = false) Integer pageNumber,
            @RequestParam(value = "pageSize", required = false) Integer pageSize
    ) {
//        return ResponseEntity.ok(metricsService.)
        return null;
    }

    private Integer checkPageNumber(Integer pageNumber) {
        return pageNumber == null || pageNumber < 0 ? DEFAULT_PAGE_NUMBER : pageNumber;
    }

    private Integer checkPageSize(Integer pageSize) {
        return pageSize == null || pageSize < 0 ? DEFAULT_PAGE_SIZE : pageSize;
    }
}
