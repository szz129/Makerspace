package com.makerspace.makerspaceapp.model;

import jakarta.persistence.*;

@Entity
public class Makerspace {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long makerspaceId;

    private String name;
    private String location;

    @Column(length = 1000)
    private String description;

    private Integer capacity;

    @Column(unique = true)
    private String contactEmail;

    // Constructors
    public Makerspace() {}

    // Getters and Setters
    public Long getMakerspaceId() {
        return makerspaceId;
    }

    public void setMakerspaceId(Long makerspaceId) {
        this.makerspaceId = makerspaceId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }
}