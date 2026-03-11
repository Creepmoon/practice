package com.skiresort.controller;

import com.skiresort.dto.review.ReviewRequest;
import com.skiresort.dto.review.ReviewResponse;
import com.skiresort.service.ReviewService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/resorts/{resortId}/reviews")
@RequiredArgsConstructor
@Tag(name = "Reviews", description = "Resort review management")
public class ReviewController {

    private final ReviewService reviewService;

    @GetMapping
    @Operation(summary = "Get all reviews for a resort")
    public ResponseEntity<Page<ReviewResponse>> getAll(@PathVariable Long resortId, Pageable pageable) {
        return ResponseEntity.ok(reviewService.findByResort(resortId, pageable));
    }

    @PostMapping
    @SecurityRequirement(name = "bearerAuth")
    @Operation(summary = "Add a review for a resort")
    public ResponseEntity<ReviewResponse> create(@PathVariable Long resortId,
                                                  @Valid @RequestBody ReviewRequest request,
                                                  Authentication authentication) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(reviewService.create(resortId, request, authentication.getName()));
    }

    @PutMapping("/{id}")
    @SecurityRequirement(name = "bearerAuth")
    @Operation(summary = "Update your review")
    public ResponseEntity<ReviewResponse> update(@PathVariable Long resortId,
                                                  @PathVariable Long id,
                                                  @Valid @RequestBody ReviewRequest request,
                                                  Authentication authentication) {
        return ResponseEntity.ok(reviewService.update(resortId, id, request, authentication.getName()));
    }

    @DeleteMapping("/{id}")
    @SecurityRequirement(name = "bearerAuth")
    @Operation(summary = "Delete a review (owner or ADMIN)")
    public ResponseEntity<Void> delete(@PathVariable Long resortId,
                                        @PathVariable Long id,
                                        Authentication authentication) {
        boolean isAdmin = authentication.getAuthorities()
                .contains(new SimpleGrantedAuthority("ROLE_ADMIN"));
        reviewService.delete(resortId, id, authentication.getName(), isAdmin);
        return ResponseEntity.noContent().build();
    }
}
