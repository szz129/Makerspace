package com.makerspace.makerspaceapp.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;

@Entity
@IdClass(ProjectMemberID.class) // ← Links to composite key class
public class ProjectMember {

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

        /* EXPLANATION:
     * Two @Id annotations = Composite primary key
     * Both project AND user together identify a unique record
     * 
     * This creates a many-to-many relationship:
     * - One project can have many members
     * - One user can be member of many projects
     */

    @Column(length = 100)
    private String roleInProject; // "Lead", "Developer", "Designer"
    /* EXTRA DATA in join table
     * This is WHY we need a separate entity
     * 
     * Simple many-to-many (no extra data):
     * @ManyToMany
     * List members;
     * 
     * But we want to store WHO has WHAT ROLE
     * So we create explicit join entity: ProjectMember
     */
    private LocalDateTime joinedAt;

    @PrePersist
    protected void onJoin() {
        this.joinedAt = LocalDateTime.now();
    }

    // Constructors
    public ProjectMember() {}

    // Getters and Setters
    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getRoleInProject() {
        return roleInProject;
    }

    public void setRoleInProject(String roleInProject) {
        this.roleInProject = roleInProject;
    }

    public LocalDateTime getJoinedAt() {
        return joinedAt;
    }

    public void setJoinedAt(LocalDateTime joinedAt) {
        this.joinedAt = joinedAt;
    }
}