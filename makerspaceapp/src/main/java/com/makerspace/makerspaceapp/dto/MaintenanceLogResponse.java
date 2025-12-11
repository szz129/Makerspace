package com.makerspace.makerspaceapp.dto;

import java.time.LocalDateTime;

public class MaintenanceLogResponse {
    
    private Long logId;
    private Long toolId;
    private String toolName;
    private String technicianName;
    private String description;
    private LocalDateTime maintenanceDate;
    private String status;

    // Constructors
    public MaintenanceLogResponse() {}

    public MaintenanceLogResponse(Long logId, Long toolId, String toolName, 
                                 String technicianName, String description, 
                                 LocalDateTime maintenanceDate, String status) {
        this.logId = logId;
        this.toolId = toolId;
        this.toolName = toolName;
        this.technicianName = technicianName;
        this.description = description;
        this.maintenanceDate = maintenanceDate;
        this.status = status;
    }

    // Getters and Setters
    public Long getLogId() {
        return logId;
    }

    public void setLogId(Long logId) {
        this.logId = logId;
    }

    public Long getToolId() {
        return toolId;
    }

    public void setToolId(Long toolId) {
        this.toolId = toolId;
    }

    public String getToolName() {
        return toolName;
    }

    public void setToolName(String toolName) {
        this.toolName = toolName;
    }

    public String getTechnicianName() {
        return technicianName;
    }

    public void setTechnicianName(String technicianName) {
        this.technicianName = technicianName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getMaintenanceDate() {
        return maintenanceDate;
    }

    public void setMaintenanceDate(LocalDateTime maintenanceDate) {
        this.maintenanceDate = maintenanceDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}