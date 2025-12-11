package com.makerspace.makerspaceapp.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class InventoryRequest {
    
    @NotNull(message = "Makerspace ID is required")
    private Long makerspaceId;
    
    @NotBlank(message = "Item name is required")
    private String itemName;
    
    @NotNull(message = "Quantity is required")
    @Positive(message = "Quantity must be positive")
    private Integer quantity;
    
    private String unit;
    
    @NotNull(message = "Restock level is required")
    @Positive(message = "Restock level must be positive")
    private Integer restockLevel;
    
    private Long supplierId;  // Optional - links to Supplier

    // Constructors
    public InventoryRequest() {}

    public InventoryRequest(Long makerspaceId, String itemName, Integer quantity, 
                           String unit, Integer restockLevel, Long supplierId) {
        this.makerspaceId = makerspaceId;
        this.itemName = itemName;
        this.quantity = quantity;
        this.unit = unit;
        this.restockLevel = restockLevel;
        this.supplierId = supplierId;
    }

    // Getters and Setters
    public Long getMakerspaceId() {
        return makerspaceId;
    }

    public void setMakerspaceId(Long makerspaceId) {
        this.makerspaceId = makerspaceId;
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

    public Long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
    }
}