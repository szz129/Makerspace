package com.makerspace.makerspaceapp.dto;

public class UserProfileRequest {
    
    private Long userId;
    private String bio;
    private String profilePicture;
    private String address;
    private String socialLinks;  // JSON string

    // Constructors
    public UserProfileRequest() {}

    public UserProfileRequest(Long userId, String bio, String profilePicture, 
                             String address, String socialLinks) {
        this.userId = userId;
        this.bio = bio;
        this.profilePicture = profilePicture;
        this.address = address;
        this.socialLinks = socialLinks;
    }

    // Getters and Setters
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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
