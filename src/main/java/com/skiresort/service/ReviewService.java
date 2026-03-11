package com.skiresort.service;

import com.skiresort.dto.review.ReviewRequest;
import com.skiresort.dto.review.ReviewResponse;
import com.skiresort.entity.Resort;
import com.skiresort.entity.Review;
import com.skiresort.entity.User;
import com.skiresort.exception.BusinessException;
import com.skiresort.exception.ResourceNotFoundException;
import com.skiresort.mapper.ReviewMapper;
import com.skiresort.repository.ResortRepository;
import com.skiresort.repository.ReviewRepository;
import com.skiresort.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final ReviewMapper reviewMapper;
    private final ResortService resortService;
    private final ResortRepository resortRepository;
    private final UserRepository userRepository;

    public Page<ReviewResponse> findByResort(Long resortId, Pageable pageable) {
        resortService.getResortOrThrow(resortId);
        return reviewRepository.findByResortId(resortId, pageable)
                .map(reviewMapper::toResponse);
    }

    @Transactional
    public ReviewResponse create(Long resortId, ReviewRequest request, String email) {
        Resort resort = resortService.getResortOrThrow(resortId);
        User user = getUserByEmail(email);

        if (reviewRepository.existsByResortIdAndUserId(resortId, user.getId())) {
            throw new BusinessException("You have already reviewed this resort");
        }

        Review review = reviewMapper.toEntity(request);
        review.setResort(resort);
        review.setUser(user);

        Review saved = reviewRepository.save(review);
        updateResortRating(resort);

        return reviewMapper.toResponse(saved);
    }

    @Transactional
    public ReviewResponse update(Long resortId, Long id, ReviewRequest request, String email) {
        resortService.getResortOrThrow(resortId);
        Review review = reviewRepository.findByIdAndResortId(id, resortId)
                .orElseThrow(() -> new ResourceNotFoundException("Review", id));

        User user = getUserByEmail(email);
        if (!review.getUser().getId().equals(user.getId())) {
            throw new AccessDeniedException("You can only edit your own reviews");
        }

        reviewMapper.updateEntity(request, review);
        Review saved = reviewRepository.save(review);
        updateResortRating(review.getResort());

        return reviewMapper.toResponse(saved);
    }

    @Transactional
    public void delete(Long resortId, Long id, String email, boolean isAdmin) {
        resortService.getResortOrThrow(resortId);
        Review review = reviewRepository.findByIdAndResortId(id, resortId)
                .orElseThrow(() -> new ResourceNotFoundException("Review", id));

        if (!isAdmin) {
            User user = getUserByEmail(email);
            if (!review.getUser().getId().equals(user.getId())) {
                throw new AccessDeniedException("You can only delete your own reviews");
            }
        }

        Resort resort = review.getResort();
        reviewRepository.delete(review);
        updateResortRating(resort);
    }

    private void updateResortRating(Resort resort) {
        Double avg = reviewRepository.calculateAverageRating(resort.getId());
        long count = reviewRepository.countByResortId(resort.getId());
        resort.setAverageRating(avg != null ? avg : 0.0);
        resort.setTotalReviews((int) count);
        resortRepository.save(resort);
    }

    private User getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with email: " + email));
    }
}
