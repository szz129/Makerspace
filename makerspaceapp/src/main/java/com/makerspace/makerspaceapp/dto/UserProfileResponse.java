package com.makerspace.makerspaceapp.dto;

public class UserProfileResponse {
    
    private Long profileId;
    private Long userId;
    private String userName;
    private String email;
    private String bio;
    private String profilePicture;
    private String address;
    private String socialLinks;

    // Constructors
    public UserProfileResponse() {}

    public UserProfileResponse(Long profileId, Long userId, String userName, String email,
                              String bio, String profilePicture, String address, String socialLinks) {
        this.profileId = profileId;
        this.userId = userId;
        this.userName = userName;
        this.email = email;
        this.bio = bio;
        this.profilePicture = profilePicture;
        this.address = address;
        this.socialLinks = socialLinks;
    }

    // Getters and Setters
    public Long getProfileId() {
        return profileId;
    }

    public void setProfileId(Long profileId) {
        this.profileId = profileId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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