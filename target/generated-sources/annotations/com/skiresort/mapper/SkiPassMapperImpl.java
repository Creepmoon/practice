package com.skiresort.mapper;

import com.skiresort.dto.skipass.SkiPassRequest;
import com.skiresort.dto.skipass.SkiPassResponse;
import com.skiresort.entity.Resort;
import com.skiresort.entity.SkiPass;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-03-11T18:49:17+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 22.0.1 (Oracle Corporation)"
)
@Component
public class SkiPassMapperImpl implements SkiPassMapper {

    @Override
    public SkiPassResponse toResponse(SkiPass skiPass) {
        if ( skiPass == null ) {
            return null;
        }

        SkiPassResponse skiPassResponse = new SkiPassResponse();

        skiPassResponse.setResortId( skiPassResortId( skiPass ) );
        skiPassResponse.setId( skiPass.getId() );
        skiPassResponse.setType( skiPass.getType() );
        skiPassResponse.setName( skiPass.getName() );
        skiPassResponse.setPriceRub( skiPass.getPriceRub() );
        skiPassResponse.setDurationDays( skiPass.getDurationDays() );
        skiPassResponse.setDescription( skiPass.getDescription() );
        skiPassResponse.setIsAvailable( skiPass.getIsAvailable() );

        return skiPassResponse;
    }

    @Override
    public SkiPass toEntity(SkiPassRequest request) {
        if ( request == null ) {
            return null;
        }

        SkiPass.SkiPassBuilder skiPass = SkiPass.builder();

        skiPass.type( request.getType() );
        skiPass.name( request.getName() );
        skiPass.priceRub( request.getPriceRub() );
        skiPass.durationDays( request.getDurationDays() );
        skiPass.description( request.getDescription() );
        skiPass.isAvailable( request.getIsAvailable() );

        return skiPass.build();
    }

    @Override
    public void updateEntity(SkiPassRequest request, SkiPass skiPass) {
        if ( request == null ) {
            return;
        }

        if ( request.getType() != null ) {
            skiPass.setType( request.getType() );
        }
        if ( request.getName() != null ) {
            skiPass.setName( request.getName() );
        }
        if ( request.getPriceRub() != null ) {
            skiPass.setPriceRub( request.getPriceRub() );
        }
        if ( request.getDurationDays() != null ) {
            skiPass.setDurationDays( request.getDurationDays() );
        }
        if ( request.getDescription() != null ) {
            skiPass.setDescription( request.getDescription() );
        }
        if ( request.getIsAvailable() != null ) {
            skiPass.setIsAvailable( request.getIsAvailable() );
        }
    }

    private Long skiPassResortId(SkiPass skiPass) {
        if ( skiPass == null ) {
            return null;
        }
        Resort resort = skiPass.getResort();
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
