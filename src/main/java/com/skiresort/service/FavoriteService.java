package com.skiresort.service;

import com.skiresort.dto.favorite.FavoriteResponse;
import com.skiresort.entity.Favorite;
import com.skiresort.entity.Resort;
import com.skiresort.entity.User;
import com.skiresort.exception.BusinessException;
import com.skiresort.exception.ResourceNotFoundException;
import com.skiresort.repository.FavoriteRepository;
import com.skiresort.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FavoriteService {

    private final FavoriteRepository favoriteRepository;
    private final UserRepository userRepository;
    private final ResortService resortService;

    public List<FavoriteResponse> getFavorites(String email) {
        User user = getUserByEmail(email);
        return favoriteRepository.findByUserId(user.getId())
                .stream().map(this::toResponse).collect(Collectors.toList());
    }

    @Transactional
    public FavoriteResponse addFavorite(Long resortId, String email) {
        User user = getUserByEmail(email);
        Resort resort = resortService.getResortOrThrow(resortId);

        if (favoriteRepository.existsByUserIdAndResortId(user.getId(), resortId)) {
            throw new BusinessException("Resort is already in favorites");
        }

        Favorite favorite = Favorite.builder()
                .user(user)
                .resort(resort)
                .build();

        return toResponse(favoriteRepository.save(favorite));
    }

    @Transactional
    public void removeFavorite(Long resortId, String email) {
        User user = getUserByEmail(email);
        resortService.getResortOrThrow(resortId);

        Favorite favorite = favoriteRepository.findByUserIdAndResortId(user.getId(), resortId)
                .orElseThrow(() -> new ResourceNotFoundException("Favorite not found for resort id: " + resortId));

        favoriteRepository.delete(favorite);
    }

    private User getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with email: " + email));
    }

    private FavoriteResponse toResponse(Favorite favorite) {
        FavoriteResponse response = new FavoriteResponse();
        response.setId(favorite.getId());
        response.setResortId(favorite.getResort().getId());
        response.setResortName(favorite.getResort().getName());
        response.setResortCountry(favorite.getResort().getCountry());
        response.setResortRegion(favorite.getResort().getRegion());
        response.setAverageRating(favorite.getResort().getAverageRating());
        response.setCreatedAt(favorite.getCreatedAt());
        return response;
    }
}
