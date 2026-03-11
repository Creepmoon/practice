package com.skiresort.repository;

import com.skiresort.entity.SkiPass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SkiPassRepository extends JpaRepository<SkiPass, Long> {
    List<SkiPass> findByResortId(Long resortId);
    Optional<SkiPass> findByIdAndResortId(Long id, Long resortId);
}
