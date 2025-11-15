package com.makerspace.makerspaceapp.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Membership {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long membershipId;


@ManyToOne
@JoinColumn(name = "user_id")
private user user;


private String planType; // MONTHLY, YEARLY
private LocalDate startDate;
private LocalDate endDate;
private String status; // ACTIVE, EXPIRED
private Double price;
}