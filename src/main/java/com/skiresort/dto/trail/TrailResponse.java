package com.skiresort.dto.trail;

import com.skiresort.enums.TrailDifficulty;
import lombok.Data;

@Data
public class TrailResponse {
    private Long id;
    private Long resortId;
    private String name;
    private TrailDifficulty difficulty;
    private Double lengthKm;
    private Integer verticalDropM;
    private Boolean isOpen;
    private String description;
}
