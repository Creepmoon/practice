package com.skiresort.controller;

import com.skiresort.dto.condition.ConditionRequest;
import com.skiresort.dto.condition.ConditionResponse;
import com.skiresort.service.ConditionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/resorts/{resortId}/conditions")
@RequiredArgsConstructor
@Tag(name = "Conditions", description = "Resort conditions management")
public class ConditionController {

    private final ConditionService conditionService;

    @GetMapping("/latest")
    @Operation(summary = "Get latest conditions for a resort")
    public ResponseEntity<ConditionResponse> getLatest(@PathVariable Long resortId) {
        return ResponseEntity.ok(conditionService.getLatest(resortId));
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    @SecurityRequirement(name = "bearerAuth")
    @Operation(summary = "Add conditions for a resort (ADMIN only)")
    public ResponseEntity<ConditionResponse> create(@PathVariable Long resortId,
                                                     @RequestBody ConditionRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(conditionService.create(resortId, request));
    }
}
