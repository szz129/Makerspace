package com.makerspace.makerspaceapp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;

@Entity
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "inventory_seq")
    @SequenceGenerator(name = "inventory_seq", sequenceName = "INVENTORY_SEQ", allocationSize = 1)
    private Long itemId;

    @ManyToOne
    @JoinColumn(name = "makerspace_id")
    private Makerspace makerspace;

    private String itemName;  // "PLA Filament", "Solder Wire"
    private Integer quantity;  // Current stock
    private String unit;  // "kg", "meters", "pieces"
    private Integer restockLevel;  // Alert when quantity drops below this
    private String supplier;
    /* EXPLANATION:
     * Tracks consumable materials
     * 
     * Example:
     * Item: "PLA Filament"
     * - quantity: 5 kg
     * - restockLevel: 2 kg
     * - When quantity ≤ 2 → Alert: "Time to reorder!"
     */
    
    // Constructors
    public Inventory() {}

    // Getters and Setters
    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public Makerspace getMakerspace() {
        return makerspace;
    }

    public void setMakerspace(Makerspace makerspace) {
        this.makerspace = makerspace;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Integer getRestockLevel() {
        return restockLevel;
    }

    public void setRestockLevel(Integer restockLevel) {
        this.restockLevel = restockLevel;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }
}
