package com.example.simpledatawarehouse.controller.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
public class MetricsResponse {

    private String dataSource;
    private String campaign;
    private LocalDate daily;
    private Integer clicks;
    private Long impressions;
    private Double ctr;

}
