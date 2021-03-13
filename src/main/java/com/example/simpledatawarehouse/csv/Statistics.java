package com.example.simpledatawarehouse.csv;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvCustomBindByName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Statistics {

    @CsvBindByName(column = "Datasource")
    private String Datasource;

    @CsvBindByName(column = "Campaign")
    private String campaign;

    @CsvCustomBindByName(column = "Daily", converter = LocalDateConverter.class)
    private LocalDate daily;

    @CsvBindByName(column = "Clicks")
    private Integer clicks;

    @CsvBindByName(column = "Impressions")
    private Long impressions;

}
