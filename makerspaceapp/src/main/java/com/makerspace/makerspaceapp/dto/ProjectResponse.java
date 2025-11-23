package com.makerspace.makerspaceapp.dto;

import java.time.LocalDate;

public class ProjectResponse {
    private Long projectId;
    private Long creatorId;
    private String creatorName;
    private String title;
    private String description;
    private String category;
    private LocalDate startDate;
    private LocalDate endDate;
    private String status;

    // Constructors
    public ProjectResponse() {}

    public ProjectResponse(Long projectId, Long creatorId, String creatorName, String title, 
                          String description, String category, LocalDate startDate, 
                          LocalDate endDate, String status) {
        this.projectId = projectId;
        this.creatorId = creatorId;
        this.creatorName = creatorName;
        this.title = title;
        this.description = description;
        this.category = category;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
    }

    // Getters and Setters
    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public Long getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Long creatorId) {
        this.creatorId = creatorId;
    }

    public String getCreatorName() {
        return creatorName;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}