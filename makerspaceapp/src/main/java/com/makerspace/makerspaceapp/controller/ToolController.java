package com.makerspace.makerspaceapp.controller;

import com.makerspace.makerspaceapp.dto.ApiResponse;
import com.makerspace.makerspaceapp.dto.ToolRequest;
import com.makerspace.makerspaceapp.dto.ToolResponse;
import com.makerspace.makerspaceapp.service.ToolService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tools")
@CrossOrigin(origins = "*")
public class ToolController {
    
    private final ToolService toolService;
    
    public ToolController(ToolService toolService) {
        this.toolService = toolService;
    }
    
    @PostMapping
    public ResponseEntity<ApiResponse<ToolResponse>> createTool(@Valid @RequestBody ToolRequest request) {
        ToolResponse tool = toolService.createTool(request);
        ApiResponse<ToolResponse> response = new ApiResponse<>(true, "Tool created successfully", tool);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    
    @GetMapping
    public ResponseEntity<ApiResponse<List<ToolResponse>>> getAllTools() {
        List<ToolResponse> tools = toolService.getAllTools();
        ApiResponse<List<ToolResponse>> response = new ApiResponse<>(true, "Tools retrieved successfully", tools);
        return ResponseEntity.ok(response);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ToolResponse>> getToolById(@PathVariable Long id) {
        ToolResponse tool = toolService.getToolById(id);
        ApiResponse<ToolResponse> response = new ApiResponse<>(true, "Tool retrieved successfully", tool);
        return ResponseEntity.ok(response);
    }
    
    @GetMapping("/makerspace/{makerspaceId}")
    public ResponseEntity<ApiResponse<List<ToolResponse>>> getToolsByMakerspace(@PathVariable Long makerspaceId) {
        List<ToolResponse> tools = toolService.getToolsByMakerspace(makerspaceId);
        ApiResponse<List<ToolResponse>> response = new ApiResponse<>(true, "Tools retrieved successfully", tools);
        return ResponseEntity.ok(response);
    }
    
    @GetMapping("/category/{category}")
    public ResponseEntity<ApiResponse<List<ToolResponse>>> getToolsByCategory(@PathVariable String category) {
        List<ToolResponse> tools = toolService.getToolsByCategory(category);
        ApiResponse<List<ToolResponse>> response = new ApiResponse<>(true, "Tools retrieved successfully", tools);
        return ResponseEntity.ok(response);
    }
    
    @GetMapping("/available/{makerspaceId}")
    public ResponseEntity<ApiResponse<List<ToolResponse>>> getAvailableTools(@PathVariable Long makerspaceId) {
        List<ToolResponse> tools = toolService.getAvailableTools(makerspaceId);
        ApiResponse<List<ToolResponse>> response = new ApiResponse<>(true, "Available tools retrieved successfully", tools);
        return ResponseEntity.ok(response);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ToolResponse>> updateTool(
            @PathVariable Long id,
            @Valid @RequestBody ToolRequest request) {
        ToolResponse tool = toolService.updateTool(id, request);
        ApiResponse<ToolResponse> response = new ApiResponse<>(true, "Tool updated successfully", tool);
        return ResponseEntity.ok(response);
    }
    
    @PatchMapping("/{id}/status")
    public ResponseEntity<ApiResponse<String>> updateToolStatus(
            @PathVariable Long id,
            @RequestParam String status) {
        toolService.updateToolStatus(id, status);
        ApiResponse<String> response = new ApiResponse<>(true, "Tool status updated successfully", status);
        return ResponseEntity.ok(response);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteTool(@PathVariable Long id) {
        toolService.deleteTool(id);
        ApiResponse<Void> response = new ApiResponse<>(true, "Tool deleted successfully", null);
        return ResponseEntity.ok(response);
    }
}