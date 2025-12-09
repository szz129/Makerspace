package com.makerspace.makerspaceapp.model;


import jakarta.persistence.*;
import java.time.LocalDate;


@Entity
@Table(name = "ACHIEVEMENT")
public class Achievement {


@Id
@SequenceGenerator(name = "achievement_seq_gen", sequenceName = "ACHIEVEMENT_SEQ", allocationSize = 1)
@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "achievement_seq_gen")
@Column(name = "ACHIEVEMENT_ID")
private Long achievementId;


@ManyToOne
@JoinColumn(name = "USER_ID")
private User user;


@Column(name = "TITLE")
private String title;


@Column(name = "DESCRIPTION")
private String description;


@Column(name = "DATE_EARNED")
private LocalDate dateEarned;


@Column(name = "TYPE")
private String type;


public Achievement() {}


public Long getAchievementId() { return achievementId; }
public void setAchievementId(Long achievementId) { this.achievementId = achievementId; }
public User getUser() { return user; }
public void setUser(User user) { this.user = user; }
public String getTitle() { return title; }
public void setTitle(String title) { this.title = title; }
public String getDescription() { return description; }
public void setDescription(String description) { this.description = description; }
public LocalDate getDateEarned() { return dateEarned; }
public void setDateEarned(LocalDate dateEarned) { this.dateEarned = dateEarned; }
public String getType() { return type; }
public void setType(String type) { this.type = type; }
}