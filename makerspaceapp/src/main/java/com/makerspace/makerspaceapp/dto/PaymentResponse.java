package com.makerspace.makerspaceapp.dto;

import java.time.LocalDateTime;

public class PaymentResponse {
    
    private Long paymentId;
    private Long userId;
    private String userName;
    private Double amount;
    private String paymentMethod;
    private String status;
    private LocalDateTime paymentDate;

    // Constructors
    public PaymentResponse() {}

    public PaymentResponse(Long paymentId, Long userId, String userName, Double amount, 
                          String paymentMethod, String status, LocalDateTime paymentDate) {
        this.paymentId = paymentId;
        this.userId = userId;
        this.userName = userName;
        this.amount = amount;
        this.paymentMethod = paymentMethod;
        this.status = status;
        this.paymentDate = paymentDate;
    }

    // Getters and Setters
    public Long getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(Long paymentId) {
        this.paymentId = paymentId;
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

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDateTime paymentDate) {
        this.paymentDate = paymentDate;
    }
}