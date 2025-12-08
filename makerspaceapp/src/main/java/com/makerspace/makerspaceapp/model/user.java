package com.makerspace.makerspaceapp.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
    @SequenceGenerator(name = "user_seq", sequenceName = "USER_SEQ", allocationSize = 1)
    private Long userId;
    /* EXPLANATION:
     * @GeneratedValue - Auto-generate ID values
     * GenerationType.SEQUENCE - Use Oracle sequence for ID generation
     * @SequenceGenerator - Configure the sequence
     *   - name: Reference name used in @GeneratedValue
     *   - sequenceName: Actual sequence name in Oracle database
     *   - allocationSize: Increment by 1 each time
     * 
     * When you save a User, Oracle automatically generates:
     * User 1 → userId = 1
     * User 2 → userId = 2
     * User 3 → userId = 3, etc.
     */
    private String name;

    @Column(unique = true, nullable = false)
    private String email;
    /* EXPLANATION:
     * @Column - Customize column properties
     * unique = true - No two users can have same email (Oracle creates unique index)
     * nullable = false - Email is REQUIRED (Oracle: NOT NULL constraint)
     */
    private String password;

    @ManyToOne
    @JoinColumn(name = "role_id")  // foreign key column
    private Role role;
/* EXPLANATION:
     * @ManyToOne - Defines relationship type
     * Multiple users can have the SAME role
     * Example:
     *   User 1 → Role: ADMIN
     *   User 2 → Role: USER
     *   User 3 → Role: USER (same role as User 2)
     * 
     * @JoinColumn - Specifies foreign key column
     * Creates column "role_id" in users table
     * Stores the ID of the Role entity
     * 
     * Database structure:
     * USERS table:
     * | user_id | name      | email       | role_id |
     * |---------|-----------|-------------|---------|
     * | 1       | John Doe  | john@...    | 1       | ← Points to Role with id=1
     * | 2       | Jane Doe  | jane@...    | 3       | ← Points to Role with id=3
     */

    private String skills;
    private String phone;
    private LocalDateTime createdAt;

    //lifecycle callback
    @PrePersist // ← Runs BEFORE saving to database
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
    /* EXPLANATION:
     * Automatically sets timestamp when creating new user
     * Called by Hibernate before INSERT statement
     * 
     * Timeline:
     * 1. Create User object: User user = new User();
     * 2. Set properties: user.setName("John");
     * 3. Call save: userRepository.save(user);
     * 4. → @PrePersist triggers → createdAt is set
     * 5. → INSERT INTO users executes
     */

    
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
    // JPA uses reflection to access fields
    // Without getters/setters, Hibernate can't:
    // 1. Read data from database into object
    // 2. Write data from object to database
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
