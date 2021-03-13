package com.example.simpledatawarehouse.repository;

import com.example.simpledatawarehouse.domain.TimeDimensionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TimeDimensionRepository extends JpaRepository<TimeDimensionEntity, Long> {
}
