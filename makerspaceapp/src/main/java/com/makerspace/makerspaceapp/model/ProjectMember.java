package com.makerspace.makerspaceapp.model;


import jakarta.persistence.*;
import java.time.LocalDateTime;


@Entity
@Table(name = "PROJECT_MEMBER")
@IdClass(ProjectMemberID.class)
public class ProjectMember {


@Id
@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "PROJECT_ID", nullable = false)
private Project project;


@Id
@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "USER_ID", nullable = false)
private User user;


@Column(name = "ROLE_IN_PROJECT", length = 100)
private String roleInProject;


@Column(name = "JOINED_AT")
private LocalDateTime joinedAt;


@PrePersist
protected void onJoin() {
if (this.joinedAt == null) this.joinedAt = LocalDateTime.now();
}


public ProjectMember() {}


public Project getProject() { return project; }
public void setProject(Project project) { this.project = project; }
public User getUser() { return user; }
public void setUser(User user) { this.user = user; }
public String getRoleInProject() { return roleInProject; }
public void setRoleInProject(String roleInProject) { this.roleInProject = roleInProject; }
public LocalDateTime getJoinedAt() { return joinedAt; }
public void setJoinedAt(LocalDateTime joinedAt) { this.joinedAt = joinedAt; }
}