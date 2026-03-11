package com.skiresort.service;

import com.skiresort.dto.resort.ResortRequest;
import com.skiresort.dto.resort.ResortResponse;
import com.skiresort.entity.Resort;
import com.skiresort.exception.ResourceNotFoundException;
import com.skiresort.mapper.ResortMapper;
import com.skiresort.repository.ResortRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ResortService {

    private final ResortRepository resortRepository;
    private final ResortMapper resortMapper;

    public Page<ResortResponse> findAll(String country, String region, Double minRating, Pageable pageable) {
        return resortRepository.findWithFilters(country, region, minRating, pageable)
                .map(resortMapper::toResponse);
    }

    public ResortResponse findById(Long id) {
        Resort resort = getResortOrThrow(id);
        return resortMapper.toResponse(resort);
    }

    @Transactional
    public ResortResponse create(ResortRequest request) {
        Resort resort = resortMapper.toEntity(request);
        resort.setAverageRating(0.0);
        resort.setTotalReviews(0);
        Resort saved = resortRepository.save(resort);
        return resortMapper.toResponse(saved);
    }

    @Transactional
    public ResortResponse update(Long id, ResortRequest request) {
        Resort resort = getResortOrThrow(id);
        resortMapper.updateEntity(request, resort);
        Resort saved = resortRepository.save(resort);
        return resortMapper.toResponse(saved);
    }

    @Transactional
    public void delete(Long id) {
        Resort resort = getResortOrThrow(id);
        resortRepository.delete(resort);
    }

    public Resort getResortOrThrow(Long id) {
        return resortRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Resort", id));
    }
}
