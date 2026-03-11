package com.skiresort.mapper;

import com.skiresort.dto.trail.TrailRequest;
import com.skiresort.dto.trail.TrailResponse;
import com.skiresort.entity.Trail;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface TrailMapper {

    @Mapping(target = "resortId", source = "resort.id")
    TrailResponse toResponse(Trail trail);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "resort", ignore = true)
    Trail toEntity(TrailRequest request);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "resort", ignore = true)
    void updateEntity(TrailRequest request, @MappingTarget Trail trail);
}
