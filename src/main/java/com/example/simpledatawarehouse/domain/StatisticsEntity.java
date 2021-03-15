package com.example.simpledatawarehouse.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StatisticsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    @NotBlank(message = "dataSource is mandatory")
    @Column(nullable = false)
    private String datasource;

    @NotBlank(message = "campaign is mandatory")
    @Column(nullable = false)
    private String campaign;

    @Column(nullable = false)
    private LocalDate daily;

    @Min(0)
    @Column(nullable = false)
    private Integer clicks;

    @Min(0)
    @Column(nullable = false)
    private Long impressions;

}
