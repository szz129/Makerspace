package com.makerspace.makerspaceapp.dto;

import jakarta.validation.constraints.NotNull;

public class ProjectMemberRequest {
    
    @NotNull(message = "Project ID is required")
    private Long projectId;
    
    @NotNull(message = "User ID is required")
    private Long userId;
    
    private String roleInProject;  // Developer, Designer, Manager, etc.

    // Constructors
    public ProjectMemberRequest() {}

    public ProjectMemberRequest(Long projectId, Long userId, String roleInProject) {
        this.projectId = projectId;
        this.userId = userId;
        this.roleInProject = roleInProject;
    }

    // Getters and Setters
    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getRoleInProject() {
        return roleInProject;
    }

    public void setRoleInProject(String roleInProject) {
        this.roleInProject = roleInProject;
    }
}