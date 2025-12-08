package com.makerspace.makerspaceapp.model;

import jakarta.persistence.*;

@Entity
@Table(name = "TOOL")
public class Tool {

    @Id
    @SequenceGenerator(
        name = "TOOL_SEQ",
        sequenceName = "TOOL_SEQ",
        allocationSize = 1
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "TOOL_SEQ"
    )
    @Column(name = "TOOL_ID")
    private Long toolId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MAKERSPACE_ID")
    private Makerspace makerspace;

    @Column(name = "NAME")
    private String name;

    @Column(name = "CATEGORY")
    private String category;

    @Column(name = "TOOL_CONDITION")   
    private String toolCondition;

    @Column(name = "AVAILABILITY_STATUS")   
    private String availabilityStatus;

    @Column(name = "IMAGE_URL")   
    private String imageUrl;

    // Constructors
    public Tool() {}

    // Getters and Setters
    public Long getToolId() {
        return toolId;
    }

    public void setToolId(Long toolId) {
        this.toolId = toolId;
    }

    public Makerspace getMakerspace() {
        return makerspace;
    }

    public void setMakerspace(Makerspace makerspace) {
        this.makerspace = makerspace;
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
