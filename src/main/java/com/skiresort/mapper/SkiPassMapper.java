package com.skiresort.mapper;

import com.skiresort.dto.skipass.SkiPassRequest;
import com.skiresort.dto.skipass.SkiPassResponse;
import com.skiresort.entity.SkiPass;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface SkiPassMapper {

    @Mapping(target = "resortId", source = "resort.id")
    SkiPassResponse toResponse(SkiPass skiPass);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "resort", ignore = true)
    SkiPass toEntity(SkiPassRequest request);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "resort", ignore = true)
    void updateEntity(SkiPassRequest request, @MappingTarget SkiPass skiPass);
}
