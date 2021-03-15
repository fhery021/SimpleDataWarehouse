package com.example.simpledatawarehouse.controller.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.validation.constraints.Min;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StatisticsRequest {

    @JsonFormat(pattern = "MM/dd/yyyy")
    private LocalDate fromDate;

    @JsonFormat(pattern = "MM/dd/yyyy")
    private LocalDate toDate;

    private String datasourceFilter;
    private String campaignFilter;
    @JsonFormat(pattern = "MM/dd/yyyy")
    private LocalDate exactDayFilter;
    private Integer clicksFilter;
    private Long impressionsFilter;

}
