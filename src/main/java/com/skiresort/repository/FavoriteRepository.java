package com.skiresort.repository;

import com.skiresort.entity.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FavoriteRepository extends JpaRepository<Favorite, Long> {
    List<Favorite> findByUserId(Long userId);
    Optional<Favorite> findByUserIdAndResortId(Long userId, Long resortId);
    boolean existsByUserIdAndResortId(Long userId, Long resortId);
}
