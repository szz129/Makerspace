package com.makerspace.makerspaceapp.controller;

import com.makerspace.makerspaceapp.dto.ApiResponse;
import com.makerspace.makerspaceapp.dto.ProjectRequest;
import com.makerspace.makerspaceapp.dto.ProjectResponse;
import com.makerspace.makerspaceapp.service.ProjectService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projects")
@CrossOrigin(origins = "*")
public class ProjectController {
    private final ProjectService projectService;
    
    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }
    
    @PostMapping
    public ResponseEntity<ApiResponse<ProjectResponse>> create(@Valid @RequestBody ProjectRequest request) {
        ProjectResponse project = projectService.create(request);
        ApiResponse<ProjectResponse> response = new ApiResponse<>(true, "Project created successfully", project);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    
    @GetMapping
    public ResponseEntity<ApiResponse<List<ProjectResponse>>> getAll() {
        List<ProjectResponse> projects = projectService.getAll();
        ApiResponse<List<ProjectResponse>> response = new ApiResponse<>(true, "Projects retrieved successfully", projects);
        return ResponseEntity.ok(response);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ProjectResponse>> getById(@PathVariable Long id) {
        ProjectResponse project = projectService.getById(id);
        ApiResponse<ProjectResponse> response = new ApiResponse<>(true, "Project retrieved successfully", project);
        return ResponseEntity.ok(response);
    }
    
    @GetMapping("/creator/{creatorId}")
    public ResponseEntity<ApiResponse<List<ProjectResponse>>> getByCreator(@PathVariable Long creatorId) {
        List<ProjectResponse> projects = projectService.getByCreator(creatorId);
        ApiResponse<List<ProjectResponse>> response = new ApiResponse<>(true, "Creator projects retrieved successfully", projects);
        return ResponseEntity.ok(response);
    }
    
    @GetMapping("/status/{status}")
    public ResponseEntity<ApiResponse<List<ProjectResponse>>> getByStatus(@PathVariable String status) {
        List<ProjectResponse> projects = projectService.getByStatus(status);
        ApiResponse<List<ProjectResponse>> response = new ApiResponse<>(true, "Projects retrieved successfully", projects);
        return ResponseEntity.ok(response);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ProjectResponse>> update(@PathVariable Long id, @Valid @RequestBody ProjectRequest request) {
        ProjectResponse project = projectService.update(id, request);
        ApiResponse<ProjectResponse> response = new ApiResponse<>(true, "Project updated successfully", project);
        return ResponseEntity.ok(response);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
        projectService.delete(id);
        ApiResponse<Void> response = new ApiResponse<>(true, "Project deleted successfully", null);
        return ResponseEntity.ok(response);
    }
}