package com.makerspace.makerspaceapp.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Makerspace {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long makerspaceId;

    private String name;

    private String location;

    @Column(length = 1000)
    private String description;

    private Integer capacity;

    @Column(unique = true)
    private String contactEmail;
}
