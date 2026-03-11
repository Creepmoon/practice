package com.skiresort.mapper;

import com.skiresort.dto.condition.ConditionRequest;
import com.skiresort.dto.condition.ConditionResponse;
import com.skiresort.entity.Resort;
import com.skiresort.entity.ResortCondition;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-03-11T18:49:17+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 22.0.1 (Oracle Corporation)"
)
@Component
public class ConditionMapperImpl implements ConditionMapper {

    @Override
    public ConditionResponse toResponse(ResortCondition condition) {
        if ( condition == null ) {
            return null;
        }

        ConditionResponse conditionResponse = new ConditionResponse();

        conditionResponse.setResortId( conditionResortId( condition ) );
        conditionResponse.setId( condition.getId() );
        conditionResponse.setSnowDepthCm( condition.getSnowDepthCm() );
        conditionResponse.setFreshSnowCm( condition.getFreshSnowCm() );
        conditionResponse.setTemperature( condition.getTemperature() );
        conditionResponse.setWeatherDescription( condition.getWeatherDescription() );
        conditionResponse.setVisibility( condition.getVisibility() );
        conditionResponse.setGroomed( condition.getGroomed() );
        conditionResponse.setRecordedAt( condition.getRecordedAt() );

        return conditionResponse;
    }

    @Override
    public ResortCondition toEntity(ConditionRequest request) {
        if ( request == null ) {
            return null;
        }

        ResortCondition.ResortConditionBuilder resortCondition = ResortCondition.builder();

        resortCondition.snowDepthCm( request.getSnowDepthCm() );
        resortCondition.freshSnowCm( request.getFreshSnowCm() );
        resortCondition.temperature( request.getTemperature() );
        resortCondition.weatherDescription( request.getWeatherDescription() );
        resortCondition.visibility( request.getVisibility() );
        resortCondition.groomed( request.getGroomed() );

        return resortCondition.build();
    }

    private Long conditionResortId(ResortCondition resortCondition) {
        if ( resortCondition == null ) {
            return null;
        }
        Resort resort = resortCondition.getResort();
        if ( resort == null ) {
            return null;
        }
        Long id = resort.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
