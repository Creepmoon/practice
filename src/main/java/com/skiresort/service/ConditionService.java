package com.skiresort.service;

import com.skiresort.dto.condition.ConditionRequest;
import com.skiresort.dto.condition.ConditionResponse;
import com.skiresort.entity.Resort;
import com.skiresort.entity.ResortCondition;
import com.skiresort.exception.ResourceNotFoundException;
import com.skiresort.mapper.ConditionMapper;
import com.skiresort.repository.ResortConditionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ConditionService {

    private final ResortConditionRepository conditionRepository;
    private final ConditionMapper conditionMapper;
    private final ResortService resortService;

    public ConditionResponse getLatest(Long resortId) {
        resortService.getResortOrThrow(resortId);
        ResortCondition condition = conditionRepository.findTopByResortIdOrderByRecordedAtDesc(resortId)
                .orElseThrow(() -> new ResourceNotFoundException("Conditions not found for resort with id: " + resortId));
        return conditionMapper.toResponse(condition);
    }

    @Transactional
    public ConditionResponse create(Long resortId, ConditionRequest request) {
        Resort resort = resortService.getResortOrThrow(resortId);
        ResortCondition condition = conditionMapper.toEntity(request);
        condition.setResort(resort);
        return conditionMapper.toResponse(conditionRepository.save(condition));
    }
}
