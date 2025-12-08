package com.makerspace.makerspaceapp.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ToolRequest {
    
    @NotNull(message = "Makerspace ID is required")
    private Long makerspaceId;
    
    @NotBlank(message = "Tool name is required")
    private String name;
    
    private String category;
    private String toolCondition;  // Changed to camelCase
    private String availabilityStatus;
    private String imageUrl;

    // Constructors
    public ToolRequest() {}

    public ToolRequest(Long makerspaceId, String name, String category, String toolCondition, 
                      String availabilityStatus, String imageUrl) {
        this.makerspaceId = makerspaceId;
        this.name = name;
        this.category = category;
        this.toolCondition = toolCondition;
        this.availabilityStatus = availabilityStatus;
        this.imageUrl = imageUrl;
    }

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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    // FIXED: Changed to camelCase
    public String getToolCondition() {
        return toolCondition;
    }

    public void setToolCondition(String toolCondition) {
        this.toolCondition = toolCondition;
    }

    public String getAvailabilityStatus() {
        return availabilityStatus;
    }

    public void setAvailabilityStatus(String availabilityStatus) {
        this.availabilityStatus = availabilityStatus;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}