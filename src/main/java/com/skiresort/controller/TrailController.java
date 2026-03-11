package com.skiresort.controller;

import com.skiresort.dto.trail.TrailRequest;
import com.skiresort.dto.trail.TrailResponse;
import com.skiresort.service.TrailService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/resorts/{resortId}/trails")
@RequiredArgsConstructor
@Tag(name = "Trails", description = "Trail management for ski resorts")
public class TrailController {

    private final TrailService trailService;

    @GetMapping
    @Operation(summary = "Get all trails for a resort")
    public ResponseEntity<List<TrailResponse>> getAll(@PathVariable Long resortId) {
        return ResponseEntity.ok(trailService.findByResort(resortId));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get trail by ID")
    public ResponseEntity<TrailResponse> getById(@PathVariable Long resortId, @PathVariable Long id) {
        return ResponseEntity.ok(trailService.findById(resortId, id));
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    @SecurityRequirement(name = "bearerAuth")
    @Operation(summary = "Create a new trail (ADMIN only)")
    public ResponseEntity<TrailResponse> create(@PathVariable Long resortId,
                                                 @Valid @RequestBody TrailRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(trailService.create(resortId, request));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @SecurityRequirement(name = "bearerAuth")
    @Operation(summary = "Update a trail (ADMIN only)")
    public ResponseEntity<TrailResponse> update(@PathVariable Long resortId,
                                                 @PathVariable Long id,
                                                 @Valid @RequestBody TrailRequest request) {
        return ResponseEntity.ok(trailService.update(resortId, id, request));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @SecurityRequirement(name = "bearerAuth")
    @Operation(summary = "Delete a trail (ADMIN only)")
    public ResponseEntity<Void> delete(@PathVariable Long resortId, @PathVariable Long id) {
        trailService.delete(resortId, id);
        return ResponseEntity.noContent().build();
    }
}
