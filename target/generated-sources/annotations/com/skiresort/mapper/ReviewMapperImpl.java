package com.skiresort.mapper;

import com.skiresort.dto.review.ReviewRequest;
import com.skiresort.dto.review.ReviewResponse;
import com.skiresort.entity.Resort;
import com.skiresort.entity.Review;
import com.skiresort.entity.User;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-03-11T18:49:17+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 22.0.1 (Oracle Corporation)"
)
@Component
public class ReviewMapperImpl implements ReviewMapper {

    @Override
    public ReviewResponse toResponse(Review review) {
        if ( review == null ) {
            return null;
        }

        ReviewResponse reviewResponse = new ReviewResponse();

        reviewResponse.setResortId( reviewResortId( review ) );
        reviewResponse.setUserId( reviewUserId( review ) );
        reviewResponse.setUserName( reviewUserName( review ) );
        reviewResponse.setId( review.getId() );
        reviewResponse.setRating( review.getRating() );
        reviewResponse.setTitle( review.getTitle() );
        reviewResponse.setBody( review.getBody() );
        reviewResponse.setVisitDate( review.getVisitDate() );
        reviewResponse.setCreatedAt( review.getCreatedAt() );
        reviewResponse.setUpdatedAt( review.getUpdatedAt() );

        return reviewResponse;
    }

    @Override
    public Review toEntity(ReviewRequest request) {
        if ( request == null ) {
            return null;
        }

        Review.ReviewBuilder review = Review.builder();

        review.rating( request.getRating() );
        review.title( request.getTitle() );
        review.body( request.getBody() );
        review.visitDate( request.getVisitDate() );

        return review.build();
    }

    @Override
    public void updateEntity(ReviewRequest request, Review review) {
        if ( request == null ) {
            return;
        }

        if ( request.getRating() != null ) {
            review.setRating( request.getRating() );
        }
        if ( request.getTitle() != null ) {
            review.setTitle( request.getTitle() );
        }
        if ( request.getBody() != null ) {
            review.setBody( request.getBody() );
        }
        if ( request.getVisitDate() != null ) {
            review.setVisitDate( request.getVisitDate() );
        }
    }

    private Long reviewResortId(Review review) {
        if ( review == null ) {
            return null;
        }
        Resort resort = review.getResort();
        if ( resort == null ) {
            return null;
        }
        Long id = resort.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long reviewUserId(Review review) {
        if ( review == null ) {
            return null;
        }
        User user = review.getUser();
        if ( user == null ) {
            return null;
        }
        Long id = user.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private String reviewUserName(Review review) {
        if ( review == null ) {
            return null;
        }
        User user = review.getUser();
        if ( user == null ) {
            return null;
        }
        String name = user.getName();
        if ( name == null ) {
            return null;
        }
        return name;
    }
}
