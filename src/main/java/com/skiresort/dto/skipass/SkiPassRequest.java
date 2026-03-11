package com.skiresort.dto.skipass;

import com.skiresort.enums.SkiPassType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class SkiPassRequest {

    @NotNull(message = "Type is required")
    private SkiPassType type;

    @NotBlank(message = "Name is required")
    private String name;

    @NotNull(message = "Price is required")
    @Positive(message = "Price must be positive")
    private BigDecimal priceRub;

    private Integer durationDays;

    private String description;

    private Boolean isAvailable = true;
}
