package com.skiresort.dto.resort;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class ResortResponse {
    private Long id;
    private String name;
    private String description;
    private String country;
    private String region;
    private Integer altitude;
    private Integer baseAltitude;
    private BigDecimal latitude;
    private BigDecimal longitude;
    private String website;
    private String phone;
    private String imageUrl;
    private Double averageRating;
    private Integer totalReviews;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
