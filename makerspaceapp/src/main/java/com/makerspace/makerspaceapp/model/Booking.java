package com.makerspace.makerspaceapp.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;

@Entity
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "booking_seq")
    @SequenceGenerator(name = "booking_seq", sequenceName = "BOOKING_SEQ", allocationSize = 1)
    private Long bookingId;

    // RELATIONSHIP 1: User who made the booking

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "userId")
    private User user;
    /* EXPLANATION:
     * Many bookings can be made by ONE user
     * 
     * Example:
     * User "John" made 3 bookings:
     *   Booking 1: 3D Printer (Dec 10)
     *   Booking 2: Laser Cutter (Dec 15)
     *   Booking 3: 3D Printer (Dec 20)
     */

    // RELATIONSHIP 2: Makerspace where booking occurs
    @ManyToOne
    @JoinColumn(name = "makerspace_id", referencedColumnName = "makerspaceId")
    private Makerspace makerspace;
    
    // RELATIONSHIP 3: Tool being booked
    @ManyToOne
    @JoinColumn(name = "TOOL_ID", referencedColumnName = "toolId")
    private Tool tool;
    /* EXPLANATION:
     * Same tool can be booked multiple times (different time slots)
     * 
     * Example - 3D Printer bookings:
     * | booking_id | tool_id | start_time     | end_time       |
     * |------------|---------|----------------|----------------|
     * | 1          | 5       | Dec 10 10:00   | Dec 10 12:00  |
     * | 2          | 5       | Dec 10 14:00   | Dec 10 16:00  | ← Same tool
     * | 3          | 5       | Dec 11 10:00   | Dec 11 12:00  | ← Different day
     */

    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String status;// "CONFIRMED", "CANCELLED", "COMPLETED"

    // RELATIONSHIP 4: Optional payment
    @ManyToOne
    @JoinColumn(name = "payment_id", referencedColumnName = "paymentId")
    private Payment payment;
    /* EXPLANATION:
     * A booking CAN have a payment (but might not yet)
     * 
     * Workflow:
     * 1. User creates booking → status="CONFIRMED", payment=null
     * 2. User pays → Payment created and linked to booking
     * 3. After time slot → status="COMPLETED"
     */

    // Constructors
    public Booking() {}

    // Getters and Setters
    public Long getBookingId() {
        return bookingId;
    }

    public void setBookingId(Long bookingId) {
        this.bookingId = bookingId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Makerspace getMakerspace() {
        return makerspace;
    }

    public void setMakerspace(Makerspace makerspace) {
        this.makerspace = makerspace;
    }

    public Tool getTool() {
        return tool;
    }

    public void setTool(Tool tool) {
        this.tool = tool;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }
}