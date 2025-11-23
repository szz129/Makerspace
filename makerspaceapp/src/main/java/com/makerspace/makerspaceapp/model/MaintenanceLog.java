package com.makerspace.makerspaceapp.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class MaintenanceLog {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long logId;

    @ManyToOne
    @JoinColumn(name = "tool_id")
    private Tool tool;

    private String technicianName;
    
    @Column(length = 2000)
    private String description;
    
    private LocalDateTime date;
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

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}