package com.skiresort.entity;

import com.skiresort.enums.SkiPassType;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "ski_passes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SkiPass {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "resort_id", nullable = false)
    private Resort resort;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SkiPassType type;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal priceRub;

    private Integer durationDays;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false)
    private Boolean isAvailable = true;
}
