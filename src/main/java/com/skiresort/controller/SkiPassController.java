package com.skiresort.controller;

import com.skiresort.dto.skipass.SkiPassRequest;
import com.skiresort.dto.skipass.SkiPassResponse;
import com.skiresort.service.SkiPassService;
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
@RequestMapping("/api/v1/resorts/{resortId}/ski-passes")
@RequiredArgsConstructor
@Tag(name = "Ski Passes", description = "Ski pass management")
public class SkiPassController {

    private final SkiPassService skiPassService;

    @GetMapping
    @Operation(summary = "Get all ski passes for a resort")
    public ResponseEntity<List<SkiPassResponse>> getAll(@PathVariable Long resortId) {
        return ResponseEntity.ok(skiPassService.findByResort(resortId));
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    @SecurityRequirement(name = "bearerAuth")
    @Operation(summary = "Create a ski pass (ADMIN only)")
    public ResponseEntity<SkiPassResponse> create(@PathVariable Long resortId,
                                                   @Valid @RequestBody SkiPassRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(skiPassService.create(resortId, request));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @SecurityRequirement(name = "bearerAuth")
    @Operation(summary = "Update a ski pass (ADMIN only)")
    public ResponseEntity<SkiPassResponse> update(@PathVariable Long resortId,
                                                   @PathVariable Long id,
                                                   @Valid @RequestBody SkiPassRequest request) {
        return ResponseEntity.ok(skiPassService.update(resortId, id, request));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @SecurityRequirement(name = "bearerAuth")
    @Operation(summary = "Delete a ski pass (ADMIN only)")
    public ResponseEntity<Void> delete(@PathVariable Long resortId, @PathVariable Long id) {
        skiPassService.delete(resortId, id);
        return ResponseEntity.noContent().build();
    }
}
