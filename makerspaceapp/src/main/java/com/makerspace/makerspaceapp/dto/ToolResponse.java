package com.makerspace.makerspaceapp.dto;

public class ToolResponse {
    private Long toolId;
    private Long makerspaceId;
    private String makerspaceName;
    private String name;
    private String category;
    private String condition;
    private String availabilityStatus;
    private String imageUrl;

    // Constructors
    public ToolResponse() {}

    public ToolResponse(Long toolId, Long makerspaceId, String makerspaceName, String name, 
                       String category, String condition, String availabilityStatus, String imageUrl) {
        this.toolId = toolId;
        this.makerspaceId = makerspaceId;
        this.makerspaceName = makerspaceName;
        this.name = name;
        this.category = category;
        this.condition = condition;
        this.availabilityStatus = availabilityStatus;
        this.imageUrl = imageUrl;
    }

    // Getters and Setters
    public Long getToolId() {
        return toolId;
    }

    public void setToolId(Long toolId) {
        this.toolId = toolId;
    }

    public Long getMakerspaceId() {
        return makerspaceId;
    }

    public void setMakerspaceId(Long makerspaceId) {
        this.makerspaceId = makerspaceId;
    }

    public String getMakerspaceName() {
        return makerspaceName;
    }

    public void setMakerspaceName(String makerspaceName) {
        this.makerspaceName = makerspaceName;
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

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
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