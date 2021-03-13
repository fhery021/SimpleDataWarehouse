package com.example.simpledatawarehouse.repository;

import com.example.simpledatawarehouse.domain.RegularDimensionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegularDimensionRepository extends JpaRepository<RegularDimensionEntity, Long> {
}
