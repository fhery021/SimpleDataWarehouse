package com.example.simpledatawarehouse.service;

import com.example.simpledatawarehouse.domain.RegularDimensionEntity;

import java.util.List;

public interface RegularDimensionService {

    RegularDimensionEntity save(RegularDimensionEntity entity);

    RegularDimensionEntity saveIfNew(RegularDimensionEntity entity);

    List<RegularDimensionEntity> findByDatasourceAndCampaignFilter(String datasource, String campaign);

}
