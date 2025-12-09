package com.makerspace.makerspaceapp.model;


import jakarta.persistence.*;


@Entity
@Table(name = "MAKERSPACE")
public class Makerspace {


@Id
@SequenceGenerator(name = "makerspace_seq_gen", sequenceName = "MAKERSPACE_SEQ", allocationSize = 1)
@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "makerspace_seq_gen")
@Column(name = "MAKERSPACE_ID")
private Long makerspaceId;


@Column(name = "NAME")
private String name;


@Column(name = "LOCATION")
private String location;


@Column(name = "DESCRIPTION", length = 1000)
private String description;


@Column(name = "CAPACITY")
private Integer capacity;


@Column(name = "CONTACT_EMAIL", unique = true)
private String contactEmail;


public Makerspace() {}


public Long getMakerspaceId() { return makerspaceId; }
public void setMakerspaceId(Long makerspaceId) { this.makerspaceId = makerspaceId; }
public String getName() { return name; }
public void setName(String name) { this.name = name; }
public String getLocation() { return location; }
public void setLocation(String location) { this.location = location; }
public String getDescription() { return description; }
public void setDescription(String description) { this.description = description; }
public Integer getCapacity() { return capacity; }
public void setCapacity(Integer capacity) { this.capacity = capacity; }
public String getContactEmail() { return contactEmail; }
public void setContactEmail(String contactEmail) { this.contactEmail = contactEmail; }
}