package com.makerspace.makerspaceapp.model;

import java.io.Serializable;
import java.util.Objects;

public class ProjectMemberID implements Serializable {
    private Long project;
    private Long user;

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProjectMemberID that = (ProjectMemberID) o;
        return Objects.equals(project, that.project) && Objects.equals(user, that.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(project, user);
    }
}
