package com.makerspace.makerspaceapp.model;

import java.time.LocalDateTime;
import jakarta.persistence.*;

@Entity
@Table(name = "USERS")
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
    @SequenceGenerator(name = "user_seq", sequenceName = "USER_SEQ", allocationSize = 1)
    @Column(name = "USER_ID")
    private Long userId;
    
    @Column(name = "NAME")
    private String name;
    
    @Column(name = "EMAIL", unique = true, nullable = false)
    private String email;
    
    @Column(name = "PASSWORD")
    private String password;
    
    @ManyToOne
    @JoinColumn(name = "ROLE_ID")
    private Role role;
    
    @Column(name = "SKILLS")
    private String skills;
    
    @Column(name = "PHONE")
    private String phone;
    
    @Column(name = "CREATED_AT")
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    // Default Constructor
    public User() {}

    // Updated Constructor
    public User(String name, String email, String password, Role role, String skills, String phone) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
        this.skills = skills;
        this.phone = phone;
    }

    // Getters & Setters
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}