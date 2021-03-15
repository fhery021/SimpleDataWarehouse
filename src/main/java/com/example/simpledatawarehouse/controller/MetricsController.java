package com.example.simpledatawarehouse.controller;

import com.example.simpledatawarehouse.controller.request.MetricsRequest;
import com.example.simpledatawarehouse.service.MetricsService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

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

    @GetMapping("/searchWithRequestBody")
    public ResponseEntity<Object> findAll(
            @RequestBody(required = false) MetricsRequest metricsRequest
    ) {
        return ResponseEntity.ok(metricsService.findAll(metricsRequest));
    }

    @GetMapping("/searchByParameter")
    public ResponseEntity<Object> findAllWithParameters(
            @RequestParam(name = "exactDay", required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate exactDay,

            @RequestParam(name = "fromDate", required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fromDate,

            @RequestParam(name = "toDate", required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate toDate,

            @RequestParam(name = "showOnlyFields", required = false) List<String> showOnlyFields,

            @RequestParam(name = "groupBy", required = false) String groupBy
    ) {
        MetricsRequest metricsRequest = MetricsRequest.builder()
                .dateFilter(exactDay)
                .fromDate(fromDate)
                .toDate(toDate)
                .showOnlyFields(showOnlyFields)
                .groupBy(groupBy)
                .build();

        return ResponseEntity.ok(metricsService.findAll(metricsRequest));
    }

}
