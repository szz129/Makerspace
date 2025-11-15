package com.makerspace.makerspaceapp.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookingId;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "userId")
    private user user;

    @ManyToOne
    @JoinColumn(name = "makerspace_id", referencedColumnName = "makerspaceId")
    private Makerspace makerspace;

    @ManyToOne
    @JoinColumn(name = "tool_id", referencedColumnName = "toolId")
    private Tool tool;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private String status; // PENDING, CONFIRMED, CANCELLED

    @ManyToOne
    @JoinColumn(name = "payment_id", referencedColumnName = "paymentId")
    private Payment payment;
}