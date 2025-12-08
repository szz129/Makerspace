package com.makerspace.makerspaceapp.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;

@Entity
public class Achievement {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "achievement_seq")
    @SequenceGenerator(name = "achievement_seq", sequenceName = "ACHIEVEMENT_SEQ", allocationSize = 1)
    private Long achievementId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String title;  // "First Project", "10 Bookings"
    private String description;
    private LocalDate dateEarned;
    private String type;  // "milestone", "skill", "contribution"
    
    /* EXPLANATION:
     * Gamification feature
     * Award badges/achievements to users
     * 
     * Example achievements:
     * - "Made first booking"
     * - "Completed 5 projects"
     * - "Workshop attendee"
     */

    // Constructors
    public Achievement() {}

    // Getters and Setters
    public Long getAchievementId() {
        return achievementId;
    }

    public void setAchievementId(Long achievementId) {
        this.achievementId = achievementId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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