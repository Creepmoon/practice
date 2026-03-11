package com.skiresort.service;

import com.skiresort.dto.skipass.SkiPassRequest;
import com.skiresort.dto.skipass.SkiPassResponse;
import com.skiresort.entity.Resort;
import com.skiresort.entity.SkiPass;
import com.skiresort.exception.ResourceNotFoundException;
import com.skiresort.mapper.SkiPassMapper;
import com.skiresort.repository.SkiPassRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SkiPassService {

    private final SkiPassRepository skiPassRepository;
    private final SkiPassMapper skiPassMapper;
    private final ResortService resortService;

    public List<SkiPassResponse> findByResort(Long resortId) {
        resortService.getResortOrThrow(resortId);
        return skiPassRepository.findByResortId(resortId)
                .stream().map(skiPassMapper::toResponse).collect(Collectors.toList());
    }

    @Transactional
    public SkiPassResponse create(Long resortId, SkiPassRequest request) {
        Resort resort = resortService.getResortOrThrow(resortId);
        SkiPass skiPass = skiPassMapper.toEntity(request);
        skiPass.setResort(resort);
        return skiPassMapper.toResponse(skiPassRepository.save(skiPass));
    }

    @Transactional
    public SkiPassResponse update(Long resortId, Long id, SkiPassRequest request) {
        resortService.getResortOrThrow(resortId);
        SkiPass skiPass = skiPassRepository.findByIdAndResortId(id, resortId)
                .orElseThrow(() -> new ResourceNotFoundException("SkiPass", id));
        skiPassMapper.updateEntity(request, skiPass);
        return skiPassMapper.toResponse(skiPassRepository.save(skiPass));
    }

    @Transactional
    public void delete(Long resortId, Long id) {
        resortService.getResortOrThrow(resortId);
        SkiPass skiPass = skiPassRepository.findByIdAndResortId(id, resortId)
                .orElseThrow(() -> new ResourceNotFoundException("SkiPass", id));
        skiPassRepository.delete(skiPass);
    }
}
