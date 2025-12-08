package com.makerspace.makerspaceapp.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.time.LocalDate;

public class MembershipRequest {
    
    @NotNull(message = "User ID is required")
    private Long userId;
    
    @NotNull(message = "Plan type is required")
    private String planType;
    
    private LocalDate startDate;
    
    private LocalDate endDate;
    
    private String status;
    
    @Positive(message = "Price must be positive")
    private Double price;

    // Constructors
    public MembershipRequest() {}

    public MembershipRequest(Long userId, String planType, LocalDate startDate, 
                            LocalDate endDate, String status, Double price) {
        this.userId = userId;
        this.planType = planType;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
        this.price = price;
    }

    // Getters and Setters
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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
