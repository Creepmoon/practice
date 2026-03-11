package com.skiresort.dto.condition;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ConditionResponse {
    private Long id;
    private Long resortId;
    private Integer snowDepthCm;
    private Integer freshSnowCm;
    private Double temperature;
    private String weatherDescription;
    private String visibility;
    private Boolean groomed;
    private LocalDateTime recordedAt;
}
