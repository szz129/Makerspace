package com.makerspace.makerspaceapp.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class MakerspaceRequest {

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Location is required")
    private String location;

    private String description;

    @NotNull(message = "Capacity is required")
    private Integer capacity;

    @Email(message = "Invalid email format")
    private String contactEmail;

    public MakerspaceRequest() {}

    public MakerspaceRequest(String name, String location, String description, Integer capacity, String contactEmail) {
        this.name = name;
        this.location = location;
        this.description = description;
        this.capacity = capacity;
        this.contactEmail = contactEmail;
    }

    // Getters and setters

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
