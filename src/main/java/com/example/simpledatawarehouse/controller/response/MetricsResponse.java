package com.example.simpledatawarehouse.controller.response;

import com.fasterxml.jackson.annotation.JsonFilter;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
//@JsonFilter("PropertyFilter")
public class MetricsResponse {

    private String dataSource;
    private String campaign;
    private LocalDate daily;
    private Integer clicks;
    private Long impressions;
    private Double ctr;

}
