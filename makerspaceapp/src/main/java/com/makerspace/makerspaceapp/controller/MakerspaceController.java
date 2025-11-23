package com.makerspace.makerspaceapp.controller;

import com.makerspace.makerspaceapp.dto.ApiResponse;
import com.makerspace.makerspaceapp.model.Makerspace;
import com.makerspace.makerspaceapp.service.MakerspaceService;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<ApiResponse<Makerspace>> create(@RequestBody Makerspace makerspace) {
        Makerspace saved = makerspaceService.create(makerspace);
        ApiResponse<Makerspace> response = new ApiResponse<>(true, "Makerspace created successfully", saved);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    
    @GetMapping
    public ResponseEntity<ApiResponse<List<Makerspace>>> getAll() {
        List<Makerspace> makerspaces = makerspaceService.getAll();
        ApiResponse<List<Makerspace>> response = new ApiResponse<>(true, "Makerspaces retrieved successfully", makerspaces);
        return ResponseEntity.ok(response);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Makerspace>> getById(@PathVariable Long id) {
        Makerspace makerspace = makerspaceService.getById(id);
        ApiResponse<Makerspace> response = new ApiResponse<>(true, "Makerspace retrieved successfully", makerspace);
        return ResponseEntity.ok(response);
    }
    
    @GetMapping("/location/{location}")
    public ResponseEntity<ApiResponse<List<Makerspace>>> getByLocation(@PathVariable String location) {
        List<Makerspace> makerspaces = makerspaceService.getByLocation(location);
        ApiResponse<List<Makerspace>> response = new ApiResponse<>(true, "Makerspaces retrieved successfully", makerspaces);
        return ResponseEntity.ok(response);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Makerspace>> update(@PathVariable Long id, @RequestBody Makerspace makerspace) {
        Makerspace updated = makerspaceService.update(id, makerspace);
        ApiResponse<Makerspace> response = new ApiResponse<>(true, "Makerspace updated successfully", updated);
        return ResponseEntity.ok(response);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
        makerspaceService.delete(id);
        ApiResponse<Void> response = new ApiResponse<>(true, "Makerspace deleted successfully", null);
        return ResponseEntity.ok(response);
    }
}