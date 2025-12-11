package com.makerspace.makerspaceapp.dto;

import java.time.LocalDate;

public class AchievementResponse {
    
    private Long achievementId;
    private Long userId;
    private String userName;
    private String title;
    private String description;
    private LocalDate dateEarned;
    private String type;

    // Constructors
    public AchievementResponse() {}

    public AchievementResponse(Long achievementId, Long userId, String userName, 
                              String title, String description, LocalDate dateEarned, String type) {
        this.achievementId = achievementId;
        this.userId = userId;
        this.userName = userName;
        this.title = title;
        this.description = description;
        this.dateEarned = dateEarned;
        this.type = type;
    }

    // Getters and Setters
    public Long getAchievementId() {
        return achievementId;
    }

    public void setAchievementId(Long achievementId) {
        this.achievementId = achievementId;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDateEarned() {
        return dateEarned;
    }

    public void setDateEarned(LocalDate dateEarned) {
        this.dateEarned = dateEarned;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
