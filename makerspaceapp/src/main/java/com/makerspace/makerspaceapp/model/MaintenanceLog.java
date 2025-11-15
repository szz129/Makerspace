package com.makerspace.makerspaceapp.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MaintenanceLog {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long logId;


@ManyToOne
@JoinColumn(name = "tool_id")
private Tool tool;


private String technicianName;
@Column(length = 2000)
private String description;
private LocalDateTime date;
private String status; // OPEN, IN_PROGRESS, DONE
}