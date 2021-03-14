package com.example.simpledatawarehouse.controller.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Data
public class MetricsRequest {

    @JsonFormat(pattern = "MM/dd/yyyy")
    private LocalDate fromDate;

    @JsonFormat(pattern = "MM/dd/yyyy")
    private LocalDate toDate;

    private String datasourceFilter;
    private String campaignFilter;
    @JsonFormat(pattern = "MM/dd/yyyy")
    private LocalDate dateFilter;

    // TODO replace with enum
    private List<String> groupByDimensions;
    private Set<String> filterTypes;

}
