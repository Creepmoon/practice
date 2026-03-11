package com.skiresort.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "resorts")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Resort {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false)
    private String country;

    private String region;

    private Integer altitude;

    private Integer baseAltitude;

    @Column(precision = 9, scale = 6)
    private BigDecimal latitude;

    @Column(precision = 9, scale = 6)
    private BigDecimal longitude;

    private String website;

    private String phone;

    private String imageUrl;

    @Column(nullable = false)
    private Double averageRating = 0.0;

    @Column(nullable = false)
    private Integer totalReviews = 0;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "resort", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Trail> trails;

    @OneToMany(mappedBy = "resort", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ResortCondition> conditions;

    @OneToMany(mappedBy = "resort", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<SkiPass> skiPasses;

    @OneToMany(mappedBy = "resort", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Review> reviews;

    @OneToMany(mappedBy = "resort", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Favorite> favorites;
}
