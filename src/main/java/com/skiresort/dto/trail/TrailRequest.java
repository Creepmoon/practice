package com.skiresort.dto.trail;

import com.skiresort.enums.TrailDifficulty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class TrailRequest {

    @NotBlank(message = "Name is required")
    private String name;

    @NotNull(message = "Difficulty is required")
    private TrailDifficulty difficulty;

    private Double lengthKm;

    private Integer verticalDropM;

    private Boolean isOpen = true;

    private String description;
}
