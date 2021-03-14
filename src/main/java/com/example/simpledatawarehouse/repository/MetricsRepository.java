package com.example.simpledatawarehouse.repository;

import com.example.simpledatawarehouse.domain.MetricsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MetricsRepository extends JpaRepository<MetricsEntity, Long> {

    @Query("SELECT m From MetricsEntity m where m.timeDimensionEntity.id = :id")
    List<MetricsEntity> findAllByTimeDimensionId(Long id);

    @Query("SELECT m From MetricsEntity m where m.regularDimensionEntity.id = :id")
    List<MetricsEntity> findAllByRegularDimensionEntityId(Long id);

    @Query("SELECT m From MetricsEntity m where  m.timeDimensionEntity.id = :timeDimensionId and m.regularDimensionEntity.id = :regularDimensionId")
    List<MetricsEntity> findAllByTimeAndRegularDimensionEntityId(Long timeDimensionId, Long regularDimensionId);

    // TODO GROUP BY HERE
}
