package com.makerspace.makerspaceapp.controller;

import com.makerspace.makerspaceapp.dto.*;
import com.makerspace.makerspaceapp.service.MakerspaceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/makerspaces")
@CrossOrigin(origins = "*")
public class MakerspaceController {

    private final MakerspaceService makerspaceService;

    public MakerspaceController(MakerspaceService makerspaceService) {
        this.makerspaceService = makerspaceService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<MakerspaceResponse>> create(
            @RequestBody MakerspaceRequest request
    ) {
        MakerspaceResponse saved = makerspaceService.create(request);
        return ResponseEntity.ok(
                new ApiResponse<>(true, "Makerspace created successfully", saved)
        );
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<MakerspaceResponse>>> getAll() {
        List<MakerspaceResponse> makerspaces = makerspaceService.getAll();
        return ResponseEntity.ok(
                new ApiResponse<>(true, "Makerspaces retrieved successfully", makerspaces)
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<MakerspaceResponse>> getById(@PathVariable Long id) {
        MakerspaceResponse makerspace = makerspaceService.getById(id);
        return ResponseEntity.ok(
                new ApiResponse<>(true, "Makerspace retrieved successfully", makerspace)
        );
    }

    @GetMapping("/location/{location}")
    public ResponseEntity<ApiResponse<List<MakerspaceResponse>>> getByLocation(
            @PathVariable String location
    ) {
        List<MakerspaceResponse> makerspaces = makerspaceService.getByLocation(location);
        return ResponseEntity.ok(
                new ApiResponse<>(true, "Makerspaces retrieved successfully", makerspaces)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<MakerspaceResponse>> update(
            @PathVariable Long id,
            @RequestBody MakerspaceRequest request
    ) {
        MakerspaceResponse updated = makerspaceService.update(id, request);
        return ResponseEntity.ok(
                new ApiResponse<>(true, "Makerspace updated successfully", updated)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
        makerspaceService.delete(id);
        return ResponseEntity.ok(
                new ApiResponse<>(true, "Makerspace deleted successfully", null)
        );
    }
}
