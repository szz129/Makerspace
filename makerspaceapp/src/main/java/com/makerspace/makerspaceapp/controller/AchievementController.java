package com.makerspace.makerspaceapp.controller;

import com.makerspace.makerspaceapp.dto.AchievementRequest;
import com.makerspace.makerspaceapp.dto.AchievementResponse;
import com.makerspace.makerspaceapp.dto.ApiResponse;
import com.makerspace.makerspaceapp.service.AchievementService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/achievements")
@CrossOrigin(origins = "*")
public class AchievementController {

    private final AchievementService achievementService;

    public AchievementController(AchievementService achievementService) {
        this.achievementService = achievementService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<AchievementResponse>> createAchievement(
            @Valid @RequestBody AchievementRequest request) {
        AchievementResponse achievement = achievementService.createAchievement(request);
        ApiResponse<AchievementResponse> response = 
            new ApiResponse<>(true, "Achievement created successfully", achievement);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<AchievementResponse>>> getAllAchievements() {
        List<AchievementResponse> achievements = achievementService.getAllAchievements();
        ApiResponse<List<AchievementResponse>> response = 
            new ApiResponse<>(true, "Achievements retrieved successfully", achievements);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<AchievementResponse>> getAchievementById(@PathVariable Long id) {
        AchievementResponse achievement = achievementService.getAchievementById(id);
        ApiResponse<AchievementResponse> response = 
            new ApiResponse<>(true, "Achievement retrieved successfully", achievement);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<ApiResponse<List<AchievementResponse>>> getAchievementsByUser(
            @PathVariable Long userId) {
        List<AchievementResponse> achievements = achievementService.getAchievementsByUser(userId);
        ApiResponse<List<AchievementResponse>> response = 
            new ApiResponse<>(true, "User achievements retrieved successfully", achievements);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/type/{type}")
    public ResponseEntity<ApiResponse<List<AchievementResponse>>> getAchievementsByType(
            @PathVariable String type) {
        List<AchievementResponse> achievements = achievementService.getAchievementsByType(type);
        ApiResponse<List<AchievementResponse>> response = 
            new ApiResponse<>(true, "Achievements retrieved successfully", achievements);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<AchievementResponse>> updateAchievement(
            @PathVariable Long id,
            @Valid @RequestBody AchievementRequest request) {
        AchievementResponse achievement = achievementService.updateAchievement(id, request);
        ApiResponse<AchievementResponse> response = 
            new ApiResponse<>(true, "Achievement updated successfully", achievement);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteAchievement(@PathVariable Long id) {
        achievementService.deleteAchievement(id);
        ApiResponse<Void> response = 
            new ApiResponse<>(true, "Achievement deleted successfully", null);
        return ResponseEntity.ok(response);
    }
}