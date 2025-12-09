package com.makerspace.makerspaceapp.model;


import jakarta.persistence.*;


@Entity
@Table(name = "SUPPLIER")
public class Supplier {


@Id
@SequenceGenerator(name = "supplier_seq_gen", sequenceName = "SUPPLIER_SEQ", allocationSize = 1)
@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "supplier_seq_gen")
@Column(name = "SUPPLIER_ID")
private Long supplierId;


@Column(name = "NAME")
private String name;


@Column(name = "CONTACT")
private String contact;


@Column(name = "EMAIL")
private String email;


@Column(name = "ADDRESS")
private String address;


@Column(name = "RATING")
private Double rating;


public Supplier() {}


public Long getSupplierId() { return supplierId; }
public void setSupplierId(Long supplierId) { this.supplierId = supplierId; }
public String getName() { return name; }
public void setName(String name) { this.name = name; }
public String getContact() { return contact; }
public void setContact(String contact) { this.contact = contact; }
public String getEmail() { return email; }
public void setEmail(String email) { this.email = email; }
public String getAddress() { return address; }
public void setAddress(String address) { this.address = address; }
public Double getRating() { return rating; }
public void setRating(Double rating) { this.rating = rating; }
}