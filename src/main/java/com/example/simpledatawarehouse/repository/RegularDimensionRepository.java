package com.example.simpledatawarehouse.repository;

import com.example.simpledatawarehouse.domain.RegularDimensionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RegularDimensionRepository extends JpaRepository<RegularDimensionEntity, Long> {

    List<RegularDimensionEntity> findByDatasourceAndCampaign(String datasource, String campaign);

    List<RegularDimensionEntity> findByDatasourceOrCampaign(String datasource, String campaign);
}
