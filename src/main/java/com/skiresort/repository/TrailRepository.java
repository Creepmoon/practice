package com.skiresort.repository;

import com.skiresort.entity.Trail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TrailRepository extends JpaRepository<Trail, Long> {
    List<Trail> findByResortId(Long resortId);
    Optional<Trail> findByIdAndResortId(Long id, Long resortId);
}
