package com.makerspace.makerspaceapp.model;

import java.io.Serializable;
import java.util.Objects;
//Composite Primary Key Class for ProjectMember
public class ProjectMemberID implements Serializable {
    private Long project;
    private Long user;
    /* EXPLANATION:
     * Composite key = Multiple columns form the primary key
     * 
     * Example:
     * | project_id | user_id | ← Together form unique key
     * |------------|---------|
     * | 1          | 5       | ← Valid
     * | 1          | 6       | ← Valid (different user)
     * | 1          | 5       | ← INVALID! Duplicate
     * 
     * What is serializable?
     * Serializable is a marker interface in Java that allows objects 
     * to be converted into a byte stream. JPA requires composite 
     * key classes (ID classes) to implement Serializable so they can 
     * be cached, transferred, and compared properly by Hibernate.
     * 
     * Why Serializable?
     * JPA requirement for composite keys
     * Allows keys to be stored in cache/session
     */


    // Constructors
    public ProjectMemberID() {}

    public ProjectMemberID(Long project, Long user) {
        this.project = project;
        this.user = user;
    }

    // Getters and Setters
    public Long getProject() {
        return project;
    }

    public void setProject(Long project) {
        this.project = project;
    }

    public Long getUser() {
        return user;
    }

    public void setUser(Long user) {
        this.user = user;
    }

    // equals and hashCode
    @Override 
    public boolean equals(Object o) { // Two keys are equal if both project AND user match
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProjectMemberID that = (ProjectMemberID) o;
        return Objects.equals(project, that.project) && Objects.equals(user, that.user);
    }

    @Override
    public int hashCode() {// Required for using objects as Map keys
        return Objects.hash(project, user);
    }
}
