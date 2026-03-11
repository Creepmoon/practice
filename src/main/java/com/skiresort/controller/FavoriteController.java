package com.skiresort.controller;

import com.skiresort.dto.favorite.FavoriteResponse;
import com.skiresort.service.FavoriteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/favorites")
@RequiredArgsConstructor
@Tag(name = "Favorites", description = "Favorites management")
@SecurityRequirement(name = "bearerAuth")
public class FavoriteController {

    private final FavoriteService favoriteService;

    @GetMapping
    @Operation(summary = "Get current user's favorite resorts")
    public ResponseEntity<List<FavoriteResponse>> getFavorites(Authentication authentication) {
        return ResponseEntity.ok(favoriteService.getFavorites(authentication.getName()));
    }

    @PostMapping("/{resortId}")
    @Operation(summary = "Add resort to favorites")
    public ResponseEntity<FavoriteResponse> addFavorite(@PathVariable Long resortId,
                                                         Authentication authentication) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(favoriteService.addFavorite(resortId, authentication.getName()));
    }

    @DeleteMapping("/{resortId}")
    @Operation(summary = "Remove resort from favorites")
    public ResponseEntity<Void> removeFavorite(@PathVariable Long resortId,
                                                Authentication authentication) {
        favoriteService.removeFavorite(resortId, authentication.getName());
        return ResponseEntity.noContent().build();
    }
}
