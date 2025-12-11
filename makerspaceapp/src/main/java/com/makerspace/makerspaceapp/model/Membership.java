package com.makerspace.makerspaceapp.model;


import jakarta.persistence.*;
import java.time.LocalDate;


@Entity
@Table(name = "MEMBERSHIP")
public class Membership {


@Id
@SequenceGenerator(name = "membership_seq_gen", sequenceName = "MEMBERSHIP_SEQ", allocationSize = 1)
@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "membership_seq_gen")
@Column(name = "MEMBERSHIP_ID")
private Long membershipId;


@ManyToOne
@JoinColumn(name = "USER_ID")
private User user;


@Column(name = "PLAN_TYPE")
private String planType;


@Column(name = "START_DATE")
private LocalDate startDate;


@Column(name = "END_DATE")
private LocalDate endDate;


@Column(name = "STATUS")
private String status;


@Column(name = "PRICE")
private Double price;


public Membership() {}


public Long getMembershipId() { return membershipId; }
public void setMembershipId(Long membershipId) { this.membershipId = membershipId; }
public User getUser() { return user; }
public void setUser(User user) { this.user = user; }
public String getPlanType() { return planType; }
public void setPlanType(String planType) { this.planType = planType; }
public LocalDate getStartDate() { return startDate; }
public void setStartDate(LocalDate startDate) { this.startDate = startDate; }
public LocalDate getEndDate() { return endDate; }
public void setEndDate(LocalDate endDate) { this.endDate = endDate; }
public String getStatus() { return status; }
public void setStatus(String status) { this.status = status; }
public Double getPrice() { return price; }
public void setPrice(Double price) { this.price = price; }
}

