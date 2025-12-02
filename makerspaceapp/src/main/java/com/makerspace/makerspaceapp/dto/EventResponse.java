package com.makerspace.makerspaceapp.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class EventResponse { 

    private Long eventId;
    private String title;
    private String description;
    private String type;

    private LocalDate date;
    private LocalDateTime time;

    private Integer capacity;
    private Integer registrations;

    private String makerspaceName;
    private String createdByName;

    public EventResponse() {} 

    // Getters & Setters
    public Long getEventId() { return eventId; }
    public void setEventId(Long eventId) { this.eventId = eventId; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }

    public LocalDateTime getTime() { return time; }
    public void setTime(LocalDateTime time) { this.time = time; }

    public Integer getCapacity() { return capacity; }
    public void setCapacity(Integer capacity) { this.capacity = capacity; }

    public Integer getRegistrations() { return registrations; }
    public void setRegistrations(Integer registrations) { this.registrations = registrations; }

    public String getMakerspaceName() { return makerspaceName; }
    public void setMakerspaceName(String makerspaceName) { this.makerspaceName = makerspaceName; }

    public String getCreatedByName() { return createdByName; }
    public void setCreatedByName(String createdByName) { this.createdByName = createdByName; }
}
