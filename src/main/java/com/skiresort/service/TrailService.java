package com.skiresort.service;

import com.skiresort.dto.trail.TrailRequest;
import com.skiresort.dto.trail.TrailResponse;
import com.skiresort.entity.Resort;
import com.skiresort.entity.Trail;
import com.skiresort.exception.ResourceNotFoundException;
import com.skiresort.mapper.TrailMapper;
import com.skiresort.repository.TrailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TrailService {

    private final TrailRepository trailRepository;
    private final TrailMapper trailMapper;
    private final ResortService resortService;

    public List<TrailResponse> findByResort(Long resortId) {
        resortService.getResortOrThrow(resortId);
        return trailRepository.findByResortId(resortId)
                .stream().map(trailMapper::toResponse).collect(Collectors.toList());
    }

    public TrailResponse findById(Long resortId, Long id) {
        resortService.getResortOrThrow(resortId);
        Trail trail = trailRepository.findByIdAndResortId(id, resortId)
                .orElseThrow(() -> new ResourceNotFoundException("Trail", id));
        return trailMapper.toResponse(trail);
    }

    @Transactional
    public TrailResponse create(Long resortId, TrailRequest request) {
        Resort resort = resortService.getResortOrThrow(resortId);
        Trail trail = trailMapper.toEntity(request);
        trail.setResort(resort);
        return trailMapper.toResponse(trailRepository.save(trail));
    }

    @Transactional
    public TrailResponse update(Long resortId, Long id, TrailRequest request) {
        resortService.getResortOrThrow(resortId);
        Trail trail = trailRepository.findByIdAndResortId(id, resortId)
                .orElseThrow(() -> new ResourceNotFoundException("Trail", id));
        trailMapper.updateEntity(request, trail);
        return trailMapper.toResponse(trailRepository.save(trail));
    }

    @Transactional
    public void delete(Long resortId, Long id) {
        resortService.getResortOrThrow(resortId);
        Trail trail = trailRepository.findByIdAndResortId(id, resortId)
                .orElseThrow(() -> new ResourceNotFoundException("Trail", id));
        trailRepository.delete(trail);
    }
}
