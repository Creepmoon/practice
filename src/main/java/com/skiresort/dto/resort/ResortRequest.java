package com.skiresort.dto.resort;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ResortRequest {

    @NotBlank(message = "Name is required")
    private String name;

    private String description;

    @NotBlank(message = "Country is required")
    private String country;

    private String region;

    private Integer altitude;

    private Integer baseAltitude;

    private BigDecimal latitude;

    private BigDecimal longitude;

    private String website;

    private String phone;

    private String imageUrl;
}
