package com.skiresort.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "resort_conditions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResortCondition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "resort_id", nullable = false)
    private Resort resort;

    private Integer snowDepthCm;

    private Integer freshSnowCm;

    private Double temperature;

    private String weatherDescription;

    private String visibility;

    private Boolean groomed;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime recordedAt;
}
