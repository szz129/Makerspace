package com.makerspace.makerspaceapp.dto;

public class InventoryResponse {
    
    private Long itemId;
    private Long makerspaceId;
    private String makerspaceName;
    private String itemName;
    private Integer quantity;
    private String unit;
    private Integer restockLevel;
    private Long supplierId;
    private String supplierName;
    private boolean needsRestock;  // Calculated field

    // Constructors
    public InventoryResponse() {}

    public InventoryResponse(Long itemId, Long makerspaceId, String makerspaceName, 
                            String itemName, Integer quantity, String unit, 
                            Integer restockLevel, Long supplierId, String supplierName) {
        this.itemId = itemId;
        this.makerspaceId = makerspaceId;
        this.makerspaceName = makerspaceName;
        this.itemName = itemName;
        this.quantity = quantity;
        this.unit = unit;
        this.restockLevel = restockLevel;
        this.supplierId = supplierId;
        this.supplierName = supplierName;
        this.needsRestock = (quantity != null && restockLevel != null) 
                           ? quantity <= restockLevel 
                           : false;
    }

    // Getters and Setters
    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
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
        updateNeedsRestock();
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
        updateNeedsRestock();
    }

    public Long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public boolean isNeedsRestock() {
        return needsRestock;
    }

    public void setNeedsRestock(boolean needsRestock) {
        this.needsRestock = needsRestock;
    }

    private void updateNeedsRestock() {
        this.needsRestock = (quantity != null && restockLevel != null) 
                           ? quantity <= restockLevel 
                           : false;
    }
}