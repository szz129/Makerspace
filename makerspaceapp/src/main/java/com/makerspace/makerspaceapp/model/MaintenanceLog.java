package com.makerspace.makerspaceapp.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class MaintenanceLog {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "maintenance_seq")
    @SequenceGenerator(name = "maintenance_seq", sequenceName = "MAINTENANCE_LOG_SEQ", allocationSize = 1)
    private Long logId;

    @ManyToOne
    @JoinColumn(name = "tool_id")
    private Tool tool;

    private String technicianName;
    
    @Column(length = 2000)
    private String description;
    
    @Column(name = "maintenance_date")  // Changed column name
    private LocalDateTime maintenanceDate;  // Changed field name from 'date' to 'maintenanceDate'
    
    private String status;

    // Constructors
    public MaintenanceLog() {}

    // Getters and Setters
    public Long getLogId() {
        return logId;
    }

    public void setLogId(Long logId) {
        this.logId = logId;
    }

    public Tool getTool() {
        return tool;
    }

    public void setTool(Tool tool) {
        this.tool = tool;
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

    public LocalDateTime getMaintenanceDate() {  // Changed getter
        return maintenanceDate;
    }

    public void setMaintenanceDate(LocalDateTime maintenanceDate) {  // Changed setter
        this.maintenanceDate = maintenanceDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}