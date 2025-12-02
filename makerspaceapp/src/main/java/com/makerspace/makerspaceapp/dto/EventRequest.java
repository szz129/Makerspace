package com.makerspace.makerspaceapp.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class EventRequest {

    private String title;
    private String description;
    private String type;
    private LocalDate date;
    private LocalDateTime time;
    private Integer capacity;

    private Long makerspaceId;
    private Long createdById;

    public EventRequest() {}

    // Getters & Setters
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

    public Long getMakerspaceId() { return makerspaceId; }
    public void setMakerspaceId(Long makerspaceId) { this.makerspaceId = makerspaceId; }

    public Long getCreatedById() { return createdById; }
    public void setCreatedById(Long createdById) { this.createdById = createdById; }
}

