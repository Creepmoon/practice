package com.skiresort.controller;

import com.skiresort.dto.resort.ResortRequest;
import com.skiresort.dto.resort.ResortResponse;
import com.skiresort.service.ResortService;
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
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/resorts")
@RequiredArgsConstructor
@Tag(name = "Resorts", description = "Ski resort management")
public class ResortController {

    private final ResortService resortService;

    @GetMapping
    @Operation(summary = "Get all resorts with optional filters")
    public ResponseEntity<Page<ResortResponse>> getAll(
            @RequestParam(required = false) String country,
            @RequestParam(required = false) String region,
            @RequestParam(required = false) Double minRating,
            Pageable pageable) {
        return ResponseEntity.ok(resortService.findAll(country, region, minRating, pageable));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get resort by ID")
    public ResponseEntity<ResortResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(resortService.findById(id));
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    @SecurityRequirement(name = "bearerAuth")
    @Operation(summary = "Create a new resort (ADMIN only)")
    public ResponseEntity<ResortResponse> create(@Valid @RequestBody ResortRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(resortService.create(request));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @SecurityRequirement(name = "bearerAuth")
    @Operation(summary = "Update a resort (ADMIN only)")
    public ResponseEntity<ResortResponse> update(@PathVariable Long id,
                                                  @Valid @RequestBody ResortRequest request) {
        return ResponseEntity.ok(resortService.update(id, request));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @SecurityRequirement(name = "bearerAuth")
    @Operation(summary = "Delete a resort (ADMIN only)")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        resortService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
