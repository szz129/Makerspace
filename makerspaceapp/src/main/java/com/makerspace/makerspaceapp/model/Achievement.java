package com.makerspace.makerspaceapp.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Achievement {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long achievementId;


@ManyToOne
@JoinColumn(name = "user_id")
private user user;

private String title;
private String description;
private LocalDate dateEarned;
private String type; // BADGE, CERTIFICATE
}