package com.skiresort.mapper;

import com.skiresort.dto.condition.ConditionRequest;
import com.skiresort.dto.condition.ConditionResponse;
import com.skiresort.entity.ResortCondition;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface ConditionMapper {

    @Mapping(target = "resortId", source = "resort.id")
    ConditionResponse toResponse(ResortCondition condition);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "resort", ignore = true)
    @Mapping(target = "recordedAt", ignore = true)
    ResortCondition toEntity(ConditionRequest request);
}
