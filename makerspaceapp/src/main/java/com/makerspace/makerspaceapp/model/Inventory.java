package com.makerspace.makerspaceapp.model;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Inventory {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long itemId;


@ManyToOne
@JoinColumn(name = "makerspace_id")
private Makerspace makerspace;


private String itemName;
private Integer quantity;
private String unit;
private Integer restockLevel;
private String supplier;
}