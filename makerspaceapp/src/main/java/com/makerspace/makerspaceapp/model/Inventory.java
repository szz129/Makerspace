package com.makerspace.makerspaceapp.model;

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
@Table(name = "INVENTORY")
public class Inventory {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "inventory_seq")
    @SequenceGenerator(name = "inventory_seq", sequenceName = "INVENTORY_SEQ", allocationSize = 1)
    @Column(name = "ITEM_ID")
    private Long itemId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MAKERSPACE_ID", nullable = false)
    private Makerspace makerspace;

    @Column(name = "ITEM_NAME", nullable = false)
    private String itemName;
    
    @Column(name = "QUANTITY", nullable = false)
    private Integer quantity;
    
    @Column(name = "UNIT")
    private String unit;
    
    @Column(name = "RESTOCK_LEVEL")
    private Integer restockLevel;
    
    // CHANGED: From String to Supplier entity
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SUPPLIER_ID")
    private Supplier supplier;

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

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }
}