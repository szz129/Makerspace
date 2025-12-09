package com.makerspace.makerspaceapp.model;


import jakarta.persistence.*;
import java.time.LocalDateTime;


@Entity
@Table(name = "BOOKING")
public class Booking {


@Id
@SequenceGenerator(name = "booking_seq_gen", sequenceName = "BOOKING_SEQ", allocationSize = 1)
@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "booking_seq_gen")
@Column(name = "BOOKING_ID")
private Long bookingId;


@ManyToOne
@JoinColumn(name = "USER_ID")
private User user;


@ManyToOne
@JoinColumn(name = "MAKERSPACE_ID")
private Makerspace makerspace;


@ManyToOne
@JoinColumn(name = "TOOL_ID")
private Tool tool;


@Column(name = "START_TIME")
private LocalDateTime startTime;


@Column(name = "END_TIME")
private LocalDateTime endTime;


@Column(name = "STATUS")
private String status;


@ManyToOne
@JoinColumn(name = "PAYMENT_ID")
private Payment payment;


public Booking() {}


public Long getBookingId() { return bookingId; }
public void setBookingId(Long bookingId) { this.bookingId = bookingId; }
public User getUser() { return user; }
public void setUser(User user) { this.user = user; }
public Makerspace getMakerspace() { return makerspace; }
public void setMakerspace(Makerspace makerspace) { this.makerspace = makerspace; }
public Tool getTool() { return tool; }
public void setTool(Tool tool) { this.tool = tool; }
public LocalDateTime getStartTime() { return startTime; }
public void setStartTime(LocalDateTime startTime) { this.startTime = startTime; }
public LocalDateTime getEndTime() { return endTime; }
public void setEndTime(LocalDateTime endTime) { this.endTime = endTime; }
public String getStatus() { return status; }
public void setStatus(String status) { this.status = status; }
public Payment getPayment() { return payment; }
public void setPayment(Payment payment) { this.payment = payment; }
}