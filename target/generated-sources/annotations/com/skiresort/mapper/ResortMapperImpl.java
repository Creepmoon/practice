package com.skiresort.mapper;

import com.skiresort.dto.resort.ResortRequest;
import com.skiresort.dto.resort.ResortResponse;
import com.skiresort.entity.Resort;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-03-11T18:49:17+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 22.0.1 (Oracle Corporation)"
)
@Component
public class ResortMapperImpl implements ResortMapper {

    @Override
    public ResortResponse toResponse(Resort resort) {
        if ( resort == null ) {
            return null;
        }

        ResortResponse resortResponse = new ResortResponse();

        resortResponse.setId( resort.getId() );
        resortResponse.setName( resort.getName() );
        resortResponse.setDescription( resort.getDescription() );
        resortResponse.setCountry( resort.getCountry() );
        resortResponse.setRegion( resort.getRegion() );
        resortResponse.setAltitude( resort.getAltitude() );
        resortResponse.setBaseAltitude( resort.getBaseAltitude() );
        resortResponse.setLatitude( resort.getLatitude() );
        resortResponse.setLongitude( resort.getLongitude() );
        resortResponse.setWebsite( resort.getWebsite() );
        resortResponse.setPhone( resort.getPhone() );
        resortResponse.setImageUrl( resort.getImageUrl() );
        resortResponse.setAverageRating( resort.getAverageRating() );
        resortResponse.setTotalReviews( resort.getTotalReviews() );
        resortResponse.setCreatedAt( resort.getCreatedAt() );
        resortResponse.setUpdatedAt( resort.getUpdatedAt() );

        return resortResponse;
    }

    @Override
    public Resort toEntity(ResortRequest request) {
        if ( request == null ) {
            return null;
        }

        Resort.ResortBuilder resort = Resort.builder();

        resort.name( request.getName() );
        resort.description( request.getDescription() );
        resort.country( request.getCountry() );
        resort.region( request.getRegion() );
        resort.altitude( request.getAltitude() );
        resort.baseAltitude( request.getBaseAltitude() );
        resort.latitude( request.getLatitude() );
        resort.longitude( request.getLongitude() );
        resort.website( request.getWebsite() );
        resort.phone( request.getPhone() );
        resort.imageUrl( request.getImageUrl() );

        return resort.build();
    }

    @Override
    public void updateEntity(ResortRequest request, Resort resort) {
        if ( request == null ) {
            return;
        }

        if ( request.getName() != null ) {
            resort.setName( request.getName() );
        }
        if ( request.getDescription() != null ) {
            resort.setDescription( request.getDescription() );
        }
        if ( request.getCountry() != null ) {
            resort.setCountry( request.getCountry() );
        }
        if ( request.getRegion() != null ) {
            resort.setRegion( request.getRegion() );
        }
        if ( request.getAltitude() != null ) {
            resort.setAltitude( request.getAltitude() );
        }
        if ( request.getBaseAltitude() != null ) {
            resort.setBaseAltitude( request.getBaseAltitude() );
        }
        if ( request.getLatitude() != null ) {
            resort.setLatitude( request.getLatitude() );
        }
        if ( request.getLongitude() != null ) {
            resort.setLongitude( request.getLongitude() );
        }
        if ( request.getWebsite() != null ) {
            resort.setWebsite( request.getWebsite() );
        }
        if ( request.getPhone() != null ) {
            resort.setPhone( request.getPhone() );
        }
        if ( request.getImageUrl() != null ) {
            resort.setImageUrl( request.getImageUrl() );
        }
    }
}
