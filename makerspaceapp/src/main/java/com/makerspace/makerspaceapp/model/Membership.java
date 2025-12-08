package com.makerspace.makerspaceapp.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;

@Entity
public class Membership {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "membership_seq")
    @SequenceGenerator(name = "membership_seq", sequenceName = "MEMBERSHIP_SEQ", allocationSize = 1)
    private Long membershipId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String planType;  // "MONTHLY", "QUARTERLY", "ANNUAL"
    private LocalDate startDate;
    private LocalDate endDate;
    private String status;  // "ACTIVE", "EXPIRED", "CANCELLED"
    private Double price;

    /* EXPLANATION:
     * Tracks user subscriptions
     * 
     * Business rules (implemented in service):
     * 1. User can have multiple memberships over time
     * 2. Only ONE can be ACTIVE at a time
     * 3. When endDate passes, status should become EXPIRED
     * 
     * Example:
     * User "John" memberships:
     * | id | plan_type | start_date | end_date   | status    |
     * |----|-----------|------------|------------|-----------|
     * | 1  | MONTHLY   | Jan 1      | Jan 31     | EXPIRED   |
     * | 2  | QUARTERLY | Feb 1      | Apr 30     | EXPIRED   |
     * | 3  | ANNUAL    | May 1      | Apr 30+1yr | ACTIVE    |
     */

    // Constructors
    public Membership() {}

    // Getters and Setters
    public Long getMembershipId() {
        return membershipId;
    }

    public void setMembershipId(Long membershipId) {
        this.membershipId = membershipId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getPlanType() {
        return planType;
    }

    public void setPlanType(String planType) {
        this.planType = planType;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}