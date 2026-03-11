package com.skiresort.repository;

import com.skiresort.entity.Resort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ResortRepository extends JpaRepository<Resort, Long> {

    @Query("SELECT r FROM Resort r WHERE " +
            "(:country IS NULL OR LOWER(r.country) = LOWER(:country)) AND " +
            "(:region IS NULL OR LOWER(r.region) = LOWER(:region)) AND " +
            "(:minRating IS NULL OR r.averageRating >= :minRating)")
    Page<Resort> findWithFilters(
            @Param("country") String country,
            @Param("region") String region,
            @Param("minRating") Double minRating,
            Pageable pageable);
}
