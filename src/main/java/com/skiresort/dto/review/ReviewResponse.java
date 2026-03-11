package com.skiresort.dto.review;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class ReviewResponse {
    private Long id;
    private Long resortId;
    private Long userId;
    private String userName;
    private Integer rating;
    private String title;
    private String body;
    private LocalDate visitDate;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
