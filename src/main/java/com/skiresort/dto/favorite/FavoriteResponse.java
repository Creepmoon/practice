package com.skiresort.dto.favorite;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class FavoriteResponse {
    private Long id;
    private Long resortId;
    private String resortName;
    private String resortCountry;
    private String resortRegion;
    private Double averageRating;
    private LocalDateTime createdAt;
}
