package com.example.simpledatawarehouse.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Min;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MetricsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Min(0)
    @Column(nullable = false)
    private Integer clicks;

    @Min(0)
    @Column(nullable = false)
    private Long impressions;

    @ManyToOne
    @JoinColumn(name = "regDim_id", nullable = false)
    private RegularDimensionEntity regularDimensionEntity;

    @ManyToOne
    @JoinColumn(name = "timeDim_id")
    private TimeDimensionEntity timeDimensionEntity;

}
