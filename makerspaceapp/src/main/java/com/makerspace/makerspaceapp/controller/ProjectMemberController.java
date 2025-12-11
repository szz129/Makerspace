package com.makerspace.makerspaceapp.controller;

import com.makerspace.makerspaceapp.dto.ApiResponse;
import com.makerspace.makerspaceapp.dto.ProjectMemberRequest;
import com.makerspace.makerspaceapp.dto.ProjectMemberResponse;
import com.makerspace.makerspaceapp.service.ProjectMemberService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/project-members")
@CrossOrigin(origins = "*")
public class ProjectMemberController {

    private final ProjectMemberService projectMemberService;

    public ProjectMemberController(ProjectMemberService projectMemberService) {
        this.projectMemberService = projectMemberService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<ProjectMemberResponse>> addMemberToProject(
            @Valid @RequestBody ProjectMemberRequest request) {
        ProjectMemberResponse member = projectMemberService.addMemberToProject(request);
        ApiResponse<ProjectMemberResponse> response = 
            new ApiResponse<>(true, "Member added to project successfully", member);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<ProjectMemberResponse>>> getAllProjectMembers() {
        List<ProjectMemberResponse> members = projectMemberService.getAllProjectMembers();
        ApiResponse<List<ProjectMemberResponse>> response = 
            new ApiResponse<>(true, "Project members retrieved successfully", members);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/project/{projectId}")
    public ResponseEntity<ApiResponse<List<ProjectMemberResponse>>> getMembersByProject(
            @PathVariable Long projectId) {
        List<ProjectMemberResponse> members = projectMemberService.getMembersByProject(projectId);
        ApiResponse<List<ProjectMemberResponse>> response = 
            new ApiResponse<>(true, "Project members retrieved successfully", members);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<ApiResponse<List<ProjectMemberResponse>>> getProjectsByUser(
            @PathVariable Long userId) {
        List<ProjectMemberResponse> projects = projectMemberService.getProjectsByUser(userId);
        ApiResponse<List<ProjectMemberResponse>> response = 
            new ApiResponse<>(true, "User projects retrieved successfully", projects);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/project/{projectId}/user/{userId}")
    public ResponseEntity<ApiResponse<ProjectMemberResponse>> updateMemberRole(
            @PathVariable Long projectId,
            @PathVariable Long userId,
            @RequestParam String role) {
        ProjectMemberResponse member = projectMemberService.updateMemberRole(projectId, userId, role);
        ApiResponse<ProjectMemberResponse> response = 
            new ApiResponse<>(true, "Member role updated successfully", member);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/project/{projectId}/user/{userId}")
    public ResponseEntity<ApiResponse<Void>> removeMemberFromProject(
            @PathVariable Long projectId,
            @PathVariable Long userId) {
        projectMemberService.removeMemberFromProject(projectId, userId);
        ApiResponse<Void> response = 
            new ApiResponse<>(true, "Member removed from project successfully", null);
        return ResponseEntity.ok(response);
    }
}