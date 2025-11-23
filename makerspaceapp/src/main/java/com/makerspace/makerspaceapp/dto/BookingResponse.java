package com.makerspace.makerspaceapp.dto;

import java.time.LocalDateTime;

public class BookingResponse {
    private Long bookingId;
    private Long userId;
    private String userName;
    private Long toolId;
    private String toolName;
    private Long makerspaceId;
    private String makerspaceName;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String status;
    private Long paymentId;

    // Constructors
    public BookingResponse() {}

    public BookingResponse(Long bookingId, Long userId, String userName, Long toolId, String toolName, 
                          Long makerspaceId, String makerspaceName, LocalDateTime startTime, 
                          LocalDateTime endTime, String status, Long paymentId) {
        this.bookingId = bookingId;
        this.userId = userId;
        this.userName = userName;
        this.toolId = toolId;
        this.toolName = toolName;
        this.makerspaceId = makerspaceId;
        this.makerspaceName = makerspaceName;
        this.startTime = startTime;
        this.endTime = endTime;
        this.status = status;
        this.paymentId = paymentId;
    }

    // Getters and Setters
    public Long getBookingId() {
        return bookingId;
    }

    public void setBookingId(Long bookingId) {
        this.bookingId = bookingId;
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

    public Long getToolId() {
        return toolId;
    }

    public void setToolId(Long toolId) {
        this.toolId = toolId;
    }

    public String getToolName() {
        return toolName;
    }

    public void setToolName(String toolName) {
        this.toolName = toolName;
    }

    public Long getMakerspaceId() {
        return makerspaceId;
    }

    public void setMakerspaceId(Long makerspaceId) {
        this.makerspaceId = makerspaceId;
    }

    public String getMakerspaceName() {
        return makerspaceName;
    }

    public void setMakerspaceName(String makerspaceName) {
        this.makerspaceName = makerspaceName;
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

    public Long getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(Long paymentId) {
        this.paymentId = paymentId;
    }
}