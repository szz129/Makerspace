package com.makerspace.makerspaceapp.dto;

public class MakerspaceResponse {

    private Long makerspaceId;
    private String name;
    private String location;
    private String description;
    private Integer capacity;
    private String contactEmail;

    public MakerspaceResponse() {}

    public MakerspaceResponse(Long makerspaceId, String name, String location, String description,
                              Integer capacity, String contactEmail) {
        this.makerspaceId = makerspaceId;
        this.name = name;
        this.location = location;
        this.description = description;
        this.capacity = capacity;
        this.contactEmail = contactEmail;
    }

    // Getters and setters

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
