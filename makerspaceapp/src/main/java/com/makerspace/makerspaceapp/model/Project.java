package com.makerspace.makerspaceapp.model;


import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;


@Entity
@Table(name = "PROJECT")
public class Project {


@Id
@SequenceGenerator(name = "project_seq_gen", sequenceName = "PROJECT_SEQ", allocationSize = 1)
@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "project_seq_gen")
@Column(name = "PROJECT_ID")
private Long projectId;


@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "CREATOR_ID", nullable = false)
private User creator;


@Column(name = "TITLE", nullable = false)
private String title;


@Column(name = "DESCRIPTION", length = 2000)
private String description;


@Column(name = "CATEGORY")
private String category;


@Column(name = "START_DATE")
private LocalDate startDate;


@Column(name = "END_DATE")
private LocalDate endDate;


@Column(name = "STATUS", nullable = false)
private String status;


// Optional: map members for convenience
@OneToMany(mappedBy = "project", fetch = FetchType.LAZY)
private List<ProjectMember> members;


public Project() {}


public Long getProjectId() { return projectId; }
public void setProjectId(Long projectId) { this.projectId = projectId; }
public User getCreator() { return creator; }
public void setCreator(User creator) { this.creator = creator; }
public String getTitle() { return title; }
public void setTitle(String title) { this.title = title; }
public String getDescription() { return description; }
public void setDescription(String description) { this.description = description; }
public String getCategory() { return category; }
public void setCategory(String category) { this.category = category; }
public LocalDate getStartDate() { return startDate; }
public void setStartDate(LocalDate startDate) { this.startDate = startDate; }
public LocalDate getEndDate() { return endDate; }
public void setEndDate(LocalDate endDate) { this.endDate = endDate; }
public String getStatus() { return status; }
public void setStatus(String status) { this.status = status; }
public List<ProjectMember> getMembers() { return members; }
public void setMembers(List<ProjectMember> members) { this.members = members; }
}