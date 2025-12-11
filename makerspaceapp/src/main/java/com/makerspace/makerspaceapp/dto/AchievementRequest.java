package com.makerspace.makerspaceapp.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public class AchievementRequest {
    
    @NotNull(message = "User ID is required")
    private Long userId;
    
    @NotBlank(message = "Title is required")
    private String title;
    
    private String description;
    
    private LocalDate dateEarned;
    
    private String type;  // BADGE, CERTIFICATE, MILESTONE

    // Constructors
    public AchievementRequest() {}

    public AchievementRequest(Long userId, String title, String description, 
                             LocalDate dateEarned, String type) {
        this.userId = userId;
        this.title = title;
        this.description = description;
        this.dateEarned = dateEarned;
        this.type = type;
    }

    // Getters and Setters
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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
