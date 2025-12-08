package com.makerspace.makerspaceapp.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;

@Entity
public class Tool {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TOOLID")
    private Long toolId;
    
    @ManyToOne // ← Many tools belong to one makerspace
    @JoinColumn(name = "makerspace_id", referencedColumnName = "makerspaceId")
    private Makerspace makerspace;
    /* EXPLANATION:
     * @ManyToOne - Relationship direction
     * Multiple tools can exist in ONE makerspace
     * 
     * Example scenario:
     * Makerspace: "Tech Hub"
     *   ├── Tool 1: 3D Printer
     *   ├── Tool 2: Laser Cutter
     *   └── Tool 3: Soldering Station
     * 
     * @JoinColumn configuration:
     * - name: Column in TOOL table ("makerspace_id")
     * - referencedColumnName: Column in MAKERSPACE table ("makerspaceId")
     * 
     * Database structure:
     * TOOL table:
     * | tool_id | name        | makerspace_id |
     * |---------|-------------|---------------|
     * | 1       | 3D Printer  | 1             | ← All three tools
     * | 2       | Laser Cut   | 1             | ← belong to
     * | 3       | Soldering   | 1             | ← makerspace #1
     */

    private String name;
    private String category;  // e.g., "3D Printing", "Electronics"
    private String toolcondition;  // e.g., "Excellent", "Good", "Fair"
    private String availabilityStatus;  // "AVAILABLE" or "BOOKED"
    private String imageUrl;

    /* WHY NO @Column annotations here?
     * Default behavior is fine:
     * - Column name = field name
     * - Type VARCHAR2(255)
     * - Nullable = true
     */
    
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

    public String gettoolcondition() {
        return toolcondition;
    }

    public void settoolcondition(String toolcondition) {
        this.toolcondition = toolcondition;
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