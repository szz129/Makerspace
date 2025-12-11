package com.makerspace.makerspaceapp.controller;

import com.makerspace.makerspaceapp.dto.ApiResponse;
import com.makerspace.makerspaceapp.dto.UserProfileRequest;
import com.makerspace.makerspaceapp.dto.UserProfileResponse;
import com.makerspace.makerspaceapp.service.UserProfileService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/profiles")
@CrossOrigin(origins = "*")
public class UserProfileController {

    private final UserProfileService userProfileService;

    public UserProfileController(UserProfileService userProfileService) {
        this.userProfileService = userProfileService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<UserProfileResponse>> createProfile(
            @RequestBody UserProfileRequest request) {
        UserProfileResponse profile = userProfileService.createProfile(request);
        ApiResponse<UserProfileResponse> response = 
            new ApiResponse<>(true, "Profile created successfully", profile);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<UserProfileResponse>>> getAllProfiles() {
        List<UserProfileResponse> profiles = userProfileService.getAllProfiles();
        ApiResponse<List<UserProfileResponse>> response = 
            new ApiResponse<>(true, "Profiles retrieved successfully", profiles);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<UserProfileResponse>> getProfileById(@PathVariable Long id) {
        UserProfileResponse profile = userProfileService.getProfileById(id);
        ApiResponse<UserProfileResponse> response = 
            new ApiResponse<>(true, "Profile retrieved successfully", profile);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<ApiResponse<UserProfileResponse>> getProfileByUserId(
            @PathVariable Long userId) {
        UserProfileResponse profile = userProfileService.getProfileByUserId(userId);
        ApiResponse<UserProfileResponse> response = 
            new ApiResponse<>(true, "User profile retrieved successfully", profile);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<UserProfileResponse>> updateProfile(
            @PathVariable Long id,
            @RequestBody UserProfileRequest request) {
        UserProfileResponse profile = userProfileService.updateProfile(id, request);
        ApiResponse<UserProfileResponse> response = 
            new ApiResponse<>(true, "Profile updated successfully", profile);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/user/{userId}")
    public ResponseEntity<ApiResponse<UserProfileResponse>> updateProfileByUserId(
            @PathVariable Long userId,
            @RequestBody UserProfileRequest request) {
        UserProfileResponse profile = userProfileService.updateProfileByUserId(userId, request);
        ApiResponse<UserProfileResponse> response = 
            new ApiResponse<>(true, "Profile updated successfully", profile);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteProfile(@PathVariable Long id) {
        userProfileService.deleteProfile(id);
        ApiResponse<Void> response = 
            new ApiResponse<>(true, "Profile deleted successfully", null);
        return ResponseEntity.ok(response);
    }
}