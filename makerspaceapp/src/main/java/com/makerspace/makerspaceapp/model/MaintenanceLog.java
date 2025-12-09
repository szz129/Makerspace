package com.makerspace.makerspaceapp.model;


import jakarta.persistence.*;
import java.time.LocalDateTime;


@Entity
@Table(name = "MAINTENANCE_LOG")
public class MaintenanceLog {


@Id
@SequenceGenerator(name = "maintenance_seq_gen", sequenceName = "MAINTENANCE_SEQ", allocationSize = 1)
@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "maintenance_seq_gen")
@Column(name = "LOG_ID")
private Long logId;


@ManyToOne
@JoinColumn(name = "TOOL_ID")
private Tool tool;


@Column(name = "TECHNICIAN_NAME")
private String technicianName;


@Column(name = "DESCRIPTION", length = 2000)
private String description;


@Column(name = "MAINTENANCE_DATE")
private LocalDateTime maintenanceDate;


@Enumerated(EnumType.STRING)
@Column(name = "STATUS")
private MaintenanceStatus status;


@PrePersist
protected void onCreate() {
if (this.maintenanceDate == null) this.maintenanceDate = LocalDateTime.now();
}


public MaintenanceLog() {}


public Long getLogId() { return logId; }
public void setLogId(Long logId) { this.logId = logId; }
public Tool getTool() { return tool; }
public void setTool(Tool tool) { this.tool = tool; }
public String getTechnicianName() { return technicianName; }
public void setTechnicianName(String technicianName) { this.technicianName = technicianName; }
public String getDescription() { return description; }
public void setDescription(String description) { this.description = description; }
public LocalDateTime getMaintenanceDate() { return maintenanceDate; }
public void setMaintenanceDate(LocalDateTime maintenanceDate) { this.maintenanceDate = maintenanceDate; }
public MaintenanceStatus getStatus() { return status; }
public void setStatus(MaintenanceStatus status) { this.status = status; }
}