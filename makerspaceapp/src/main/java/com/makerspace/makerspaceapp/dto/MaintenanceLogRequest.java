package com.makerspace.makerspaceapp.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class MaintenanceLogRequest {
    
    @NotNull(message = "Tool ID is required")
    private Long toolId;
    
    @NotBlank(message = "Technician name is required")
    private String technicianName;
    
    @NotBlank(message = "Description is required")
    private String description;
    
    private LocalDateTime maintenanceDate;
    
    private String status;  // OPEN, IN_PROGRESS, DONE

    // Constructors
    public MaintenanceLogRequest() {}

    public MaintenanceLogRequest(Long toolId, String technicianName, String description, 
                                LocalDateTime maintenanceDate, String status) {
        this.toolId = toolId;
        this.technicianName = technicianName;
        this.description = description;
        this.maintenanceDate = maintenanceDate;
        this.status = status;
    }

    // Getters and Setters
    public Long getToolId() {
        return toolId;
    }

    public void setToolId(Long toolId) {
        this.toolId = toolId;
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