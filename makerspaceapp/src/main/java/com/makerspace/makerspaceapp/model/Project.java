package com.makerspace.makerspaceapp.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long projectId;

    @ManyToOne(fetch = FetchType.LAZY) // Avoid EAGER fetching unless necessary
    @JoinColumn(name = "creator_id", nullable = false)
    private user creator;

    @Column(nullable = false)
    private String title;

    @Column(length = 2000)
    private String description;

    private String category;

    private LocalDate startDate;
    private LocalDate endDate;

    @Column(nullable = false)
    private String status; // maybe will consider using Enum for status ie; ACTIVE, COMPLETED
}