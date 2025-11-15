package com.makerspace.makerspaceapp.model;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Supplier {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long supplierId;


private String name;
private String contact;
private String email;
private String address;
private Double rating;
}