package com.example.simpledatawarehouse.controller.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StatisticsResponse {
    private String datasource;

    private String campaign;

    @JsonFormat(pattern = "MM/dd/yyyy")
    private LocalDate daily;

    private Integer clicks;

    private Long impressions;

    private Double ctr;

}
