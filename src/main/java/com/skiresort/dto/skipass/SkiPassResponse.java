package com.skiresort.dto.skipass;

import com.skiresort.enums.SkiPassType;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class SkiPassResponse {
    private Long id;
    private Long resortId;
    private SkiPassType type;
    private String name;
    private BigDecimal priceRub;
    private Integer durationDays;
    private String description;
    private Boolean isAvailable;
}
