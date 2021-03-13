package com.example.simpledatawarehouse.controller.request;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
import java.util.List;

public class RequestWrapper {

    private List<MetricsType> metricsToShow;
    private List<DimensionType> dimensionsToShow;
    private List<DimensionType> groupByDimensions;
    private List<DimensionType> filterByDimensions;

    @JsonFormat(pattern = "MM/dd/yyyy")
    private LocalDate fromDate;

    @JsonFormat(pattern = "MM/dd/yyyy")
    private LocalDate toDate;
}
