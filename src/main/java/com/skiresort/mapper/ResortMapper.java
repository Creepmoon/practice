package com.skiresort.mapper;

import com.skiresort.dto.resort.ResortRequest;
import com.skiresort.dto.resort.ResortResponse;
import com.skiresort.entity.Resort;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface ResortMapper {

    ResortResponse toResponse(Resort resort);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "averageRating", ignore = true)
    @Mapping(target = "totalReviews", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "trails", ignore = true)
    @Mapping(target = "conditions", ignore = true)
    @Mapping(target = "skiPasses", ignore = true)
    @Mapping(target = "reviews", ignore = true)
    @Mapping(target = "favorites", ignore = true)
    Resort toEntity(ResortRequest request);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "averageRating", ignore = true)
    @Mapping(target = "totalReviews", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "trails", ignore = true)
    @Mapping(target = "conditions", ignore = true)
    @Mapping(target = "skiPasses", ignore = true)
    @Mapping(target = "reviews", ignore = true)
    @Mapping(target = "favorites", ignore = true)
    void updateEntity(ResortRequest request, @MappingTarget Resort resort);
}
