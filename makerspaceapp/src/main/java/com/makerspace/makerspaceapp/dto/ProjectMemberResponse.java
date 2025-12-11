package com.makerspace.makerspaceapp.dto;

import java.time.LocalDateTime;

public class ProjectMemberResponse {
    
    private Long projectId;
    private String projectTitle;
    private Long userId;
    private String userName;
    private String userEmail;
    private String roleInProject;
    private LocalDateTime joinedAt;

    // Constructors
    public ProjectMemberResponse() {}

    public ProjectMemberResponse(Long projectId, String projectTitle, Long userId, 
                                String userName, String userEmail, String roleInProject, 
                                LocalDateTime joinedAt) {
        this.projectId = projectId;
        this.projectTitle = projectTitle;
        this.userId = userId;
        this.userName = userName;
        this.userEmail = userEmail;
        this.roleInProject = roleInProject;
        this.joinedAt = joinedAt;
    }

    // Getters and Setters
    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public String getProjectTitle() {
        return projectTitle;
    }

    public void setProjectTitle(String projectTitle) {
        this.projectTitle = projectTitle;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getRoleInProject() {
        return roleInProject;
    }

    public void setRoleInProject(String roleInProject) {
        this.roleInProject = roleInProject;
    }

    public LocalDateTime getJoinedAt() {
        return joinedAt;
    }

    public void setJoinedAt(LocalDateTime joinedAt) {
        this.joinedAt = joinedAt;
    }
}