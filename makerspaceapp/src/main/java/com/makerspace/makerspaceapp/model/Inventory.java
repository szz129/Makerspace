package com.makerspace.makerspaceapp.model;


import jakarta.persistence.*;


@Entity
@Table(name = "INVENTORY")
public class Inventory {


@Id
@SequenceGenerator(name = "inventory_seq_gen", sequenceName = "INVENTORY_SEQ", allocationSize = 1)
@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "inventory_seq_gen")
@Column(name = "ITEM_ID")
private Long itemId;


@ManyToOne
@JoinColumn(name = "MAKERSPACE_ID")
private Makerspace makerspace;


@Column(name = "ITEM_NAME")
private String itemName;


@Column(name = "QUANTITY")
private Integer quantity;


@Column(name = "UNIT")
private String unit;


@Column(name = "RESTOCK_LEVEL")
private Integer restockLevel;


@Column(name = "SUPPLIER")
private String supplier;


public Inventory() {}


public Long getItemId() { return itemId; }
public void setItemId(Long itemId) { this.itemId = itemId; }
public Makerspace getMakerspace() { return makerspace; }
public void setMakerspace(Makerspace makerspace) { this.makerspace = makerspace; }
public String getItemName() { return itemName; }
public void setItemName(String itemName) { this.itemName = itemName; }
public Integer getQuantity() { return quantity; }
public void setQuantity(Integer quantity) { this.quantity = quantity; }
public String getUnit() { return unit; }
public void setUnit(String unit) { this.unit = unit; }
public Integer getRestockLevel() { return restockLevel; }
public void setRestockLevel(Integer restockLevel) { this.restockLevel = restockLevel; }
public String getSupplier() { return supplier; }
public void setSupplier(String supplier) { this.supplier = supplier; }
}