package com.makerspace.makerspaceapp.model;


import jakarta.persistence.*;
import java.time.LocalDateTime;


@Entity
@Table(name = "PAYMENT")
public class Payment {


@Id
@SequenceGenerator(name = "payment_seq_gen", sequenceName = "PAYMENT_SEQ", allocationSize = 1)
@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "payment_seq_gen")
@Column(name = "PAYMENT_ID")
private Long paymentId;


@ManyToOne
@JoinColumn(name = "USER_ID")
private User user;


@Column(name = "AMOUNT")
private Double amount;


@Column(name = "PAYMENT_METHOD")
private String paymentMethod;


@Column(name = "STATUS")
private String status;


@Column(name = "PAYMENT_DATE")
private LocalDateTime paymentDate;


@PrePersist
protected void onCreate() {
if (this.paymentDate == null) this.paymentDate = LocalDateTime.now();
}


public Payment() {}


public Long getPaymentId() { return paymentId; }
public void setPaymentId(Long paymentId) { this.paymentId = paymentId; }
public User getUser() { return user; }
public void setUser(User user) { this.user = user; }
public Double getAmount() { return amount; }
public void setAmount(Double amount) { this.amount = amount; }
public String getPaymentMethod() { return paymentMethod; }
public void setPaymentMethod(String paymentMethod) { this.paymentMethod = paymentMethod; }
public String getStatus() { return status; }
public void setStatus(String status) { this.status = status; }
public LocalDateTime getPaymentDate() { return paymentDate; }
public void setPaymentDate(LocalDateTime paymentDate) { this.paymentDate = paymentDate; }
}