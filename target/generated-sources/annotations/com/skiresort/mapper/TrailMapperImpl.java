package com.skiresort.mapper;

import com.skiresort.dto.trail.TrailRequest;
import com.skiresort.dto.trail.TrailResponse;
import com.skiresort.entity.Resort;
import com.skiresort.entity.Trail;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-03-11T18:49:17+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 22.0.1 (Oracle Corporation)"
)
@Component
public class TrailMapperImpl implements TrailMapper {

    @Override
    public TrailResponse toResponse(Trail trail) {
        if ( trail == null ) {
            return null;
        }

        TrailResponse trailResponse = new TrailResponse();

        trailResponse.setResortId( trailResortId( trail ) );
        trailResponse.setId( trail.getId() );
        trailResponse.setName( trail.getName() );
        trailResponse.setDifficulty( trail.getDifficulty() );
        trailResponse.setLengthKm( trail.getLengthKm() );
        trailResponse.setVerticalDropM( trail.getVerticalDropM() );
        trailResponse.setIsOpen( trail.getIsOpen() );
        trailResponse.setDescription( trail.getDescription() );

        return trailResponse;
    }

    @Override
    public Trail toEntity(TrailRequest request) {
        if ( request == null ) {
            return null;
        }

        Trail.TrailBuilder trail = Trail.builder();

        trail.name( request.getName() );
        trail.difficulty( request.getDifficulty() );
        trail.lengthKm( request.getLengthKm() );
        trail.verticalDropM( request.getVerticalDropM() );
        trail.isOpen( request.getIsOpen() );
        trail.description( request.getDescription() );

        return trail.build();
    }

    @Override
    public void updateEntity(TrailRequest request, Trail trail) {
        if ( request == null ) {
            return;
        }

        if ( request.getName() != null ) {
            trail.setName( request.getName() );
        }
        if ( request.getDifficulty() != null ) {
            trail.setDifficulty( request.getDifficulty() );
        }
        if ( request.getLengthKm() != null ) {
            trail.setLengthKm( request.getLengthKm() );
        }
        if ( request.getVerticalDropM() != null ) {
            trail.setVerticalDropM( request.getVerticalDropM() );
        }
        if ( request.getIsOpen() != null ) {
            trail.setIsOpen( request.getIsOpen() );
        }
        if ( request.getDescription() != null ) {
            trail.setDescription( request.getDescription() );
        }
    }

    private Long trailResortId(Trail trail) {
        if ( trail == null ) {
            return null;
        }
        Resort resort = trail.getResort();
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
