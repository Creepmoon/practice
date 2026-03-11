package com.skiresort.repository;

import com.skiresort.entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    Page<Review> findByResortId(Long resortId, Pageable pageable);
    boolean existsByResortIdAndUserId(Long resortId, Long userId);
    Optional<Review> findByIdAndResortId(Long id, Long resortId);

    @Query("SELECT AVG(r.rating) FROM Review r WHERE r.resort.id = :resortId")
    Double calculateAverageRating(@Param("resortId") Long resortId);

    long countByResortId(Long resortId);
}
