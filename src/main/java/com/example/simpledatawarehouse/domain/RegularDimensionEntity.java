package com.example.simpledatawarehouse.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegularDimensionEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "dataSource is mandatory")
    @Column(nullable = false)
    private String datasource;

    @NotBlank(message = "campaign is mandatory")
    @Column(nullable = false)
    private String campaign;
}
