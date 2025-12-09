package com.makerspace.makerspaceapp.model;


import jakarta.persistence.*;


@Entity
@Table(name = "ROLES")
public class Role {


@Id
@SequenceGenerator(name = "role_seq_gen", sequenceName = "ROLE_SEQ", allocationSize = 1)
@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "role_seq_gen")
@Column(name = "ID")
private Long id;


@Column(name = "NAME", nullable = false)
private String name; // ADMIN, USER, STAFF


public Role() {}


public Long getId() {
return id;
}


public void setId(Long id) {
this.id = id;
}


public String getName() {
return name;
}


public void setName(String name) {
this.name = name;
}
}