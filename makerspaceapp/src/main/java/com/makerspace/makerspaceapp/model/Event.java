package com.makerspace.makerspaceapp.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "event")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "event_seq")
    @SequenceGenerator(name = "event_seq", sequenceName = "EVENT_SEQ", allocationSize = 1)
    private Long eventId;

    @Column(nullable = false)
    private String title;
    
    @Column(length = 2000)
    private String description;
    
    private String type; // workshop, training, competition
    
    @Column(name = "event_date", nullable = false)
    private LocalDate eventDate;

    /* @Column(name = "event_date")
     * Column in database: "event_date"
     * Field in Java: eventDate
     * 
     * Why different?
     * Database convention: snake_case
     * Java convention: camelCase
     */

    private String location;
    
    @Column(nullable = false)
    private int capacity; 

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "makerspace_id")
    private Makerspace makerspace;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by")
    private User createdBy;
    /* EXPLANATION:
     * Event is held at a makerspace
     * Event is created by a user (usually ADMIN/STAFF)
     * 
     * Example:
     * Event: "3D Printing Workshop"
     * - makerspace: "Tech Innovation Hub"
     * - createdBy: Admin user
     * - capacity: 20 people
     * - eventDate: Dec 20, 2024
     */
    
    // Constructors
    public Event() {}

    // Getters and Setters
    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public LocalDate getEventDate() {
        return eventDate;
    }

    public void setEventDate(LocalDate eventDate) {
        this.eventDate = eventDate;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public Makerspace getMakerspace() {
        return makerspace;
    }

    public void setMakerspace(Makerspace makerspace) {
        this.makerspace = makerspace;
    }

    public User getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }
}