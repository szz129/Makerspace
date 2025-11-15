package com.makerspace.makerspaceapp.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Tool {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long toolId;

    @ManyToOne
    @JoinColumn(name = "makerspace_id", referencedColumnName = "makerspaceId")
    private Makerspace makerspace;

    private String name;

    private String category;

    private String condition;

    private String availabilityStatus; // AVAILABLE, BOOKED, MAINTENANCE

    private String imageUrl;
}