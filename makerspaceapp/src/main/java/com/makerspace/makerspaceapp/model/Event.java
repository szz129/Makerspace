package com.makerspace.makerspaceapp.model;


import jakarta.persistence.*;
import java.time.LocalDate;


@Entity
@Table(name = "EVENT")
public class Event {


@Id
@SequenceGenerator(name = "event_seq_gen", sequenceName = "EVENT_SEQ", allocationSize = 1)
@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "event_seq_gen")
@Column(name = "EVENT_ID")
private Long eventId;


@Column(name = "TITLE", nullable = false)
private String title;


@Column(name = "DESCRIPTION", length = 2000)
private String description;


@Column(name = "TYPE")
private String type;


@Column(name = "EVENT_DATE", nullable = false)
private LocalDate eventDate;


@Column(name = "LOCATION")
private String location;


@Column(name = "CAPACITY", nullable = false)
private int capacity;


@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "MAKERSPACE_ID")
private Makerspace makerspace;


@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "CREATED_BY")
private User createdBy;


public Event() {}


public Long getEventId() { return eventId; }
public void setEventId(Long eventId) { this.eventId = eventId; }
public String getTitle() { return title; }
public void setTitle(String title) { this.title = title; }
public String getDescription() { return description; }
public void setDescription(String description) { this.description = description; }
public String getType() { return type; }
public void setType(String type) { this.type = type; }
public LocalDate getEventDate() { return eventDate; }
public void setEventDate(LocalDate eventDate) { this.eventDate = eventDate; }
public String getLocation() { return location; }
public void setLocation(String location) { this.location = location; }
public int getCapacity() { return capacity; }
public void setCapacity(int capacity) { this.capacity = capacity; }
public Makerspace getMakerspace() { return makerspace; }
public void setMakerspace(Makerspace makerspace) { this.makerspace = makerspace; }
public User getCreatedBy() { return createdBy; }
public void setCreatedBy(User createdBy) { this.createdBy = createdBy; }
}