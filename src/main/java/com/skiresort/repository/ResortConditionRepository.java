package com.skiresort.repository;

import com.skiresort.entity.ResortCondition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ResortConditionRepository extends JpaRepository<ResortCondition, Long> {
    Optional<ResortCondition> findTopByResortIdOrderByRecordedAtDesc(Long resortId);
}
