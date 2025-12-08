package com.makerspace.makerspaceapp.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;

@Entity
public class UserProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_profile_seq")
    @SequenceGenerator(name = "user_profile_seq", sequenceName = "USER_PROFILE_SEQ", allocationSize = 1)
    private Long profileId;

    @OneToOne // ← ONE user has ONE profile
    @JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID")
    private User user;
    /* EXPLANATION:
     * @OneToOne vs @ManyToOne
     * 
     * @OneToOne: Each user has exactly ONE profile
     * User 1 → Profile 1
     * User 2 → Profile 2
     * 
     * If it were @ManyToOne:
     * User 1 → Profile 1
     * User 2 → Profile 1 ← Multiple users could share profile (wrong!)
     */

    @Column(name = "BIO", length = 1000)
    private String bio;

    @Column(name = "PROFILE_PICTURE")
    private String profilePicture;  // URL to image

    @Column(name = "ADDRESS")
    private String address;

    @Column(name = "SOCIAL_LINKS")
    private String socialLinks;// JSON string with social media links

    // Constructors
    public UserProfile() {}

    // Getters and Setters
    public Long getProfileId() {
        return profileId;
    }

    public void setProfileId(Long profileId) {
        this.profileId = profileId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSocialLinks() {
        return socialLinks;
    }

    public void setSocialLinks(String socialLinks) {
        this.socialLinks = socialLinks;
    }
}
