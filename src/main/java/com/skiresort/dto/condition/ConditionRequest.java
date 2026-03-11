package com.skiresort.dto.condition;

import lombok.Data;

@Data
public class ConditionRequest {
    private Integer snowDepthCm;
    private Integer freshSnowCm;
    private Double temperature;
    private String weatherDescription;
    private String visibility;
    private Boolean groomed;
}
