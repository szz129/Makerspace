package com.makerspace.makerspaceapp.dto;

import java.time.LocalDate;

public class MembershipResponse {
    
    private Long membershipId;
    private Long userId;
    private String userName;
    private String planType;
    private LocalDate startDate;
    private LocalDate endDate;
    private String status;
    private Double price;

    // Constructors
    public MembershipResponse() {}

    public MembershipResponse(Long membershipId, Long userId, String userName, String planType, 
                             LocalDate startDate, LocalDate endDate, String status, Double price) {
        this.membershipId = membershipId;
        this.userId = userId;
        this.userName = userName;
        this.planType = planType;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
        this.price = price;
    }

    // Getters and Setters
    public Long getMembershipId() {
        return membershipId;
    }

    public void setMembershipId(Long membershipId) {
        this.membershipId = membershipId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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