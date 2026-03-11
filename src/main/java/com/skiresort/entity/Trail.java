package com.skiresort.entity;

import com.skiresort.enums.TrailDifficulty;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "trails")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Trail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "resort_id", nullable = false)
    private Resort resort;

    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TrailDifficulty difficulty;

    private Double lengthKm;

    private Integer verticalDropM;

    @Column(nullable = false)
    private Boolean isOpen = true;

    @Column(columnDefinition = "TEXT")
    private String description;
}
