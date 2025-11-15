package com.makerspace.makerspaceapp.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long profileId;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "userId") 
    private user user;

    @Column(length = 1000)
    private String bio;

    private String profilePicture;

    private String address;

    private String socialLinks;
}
