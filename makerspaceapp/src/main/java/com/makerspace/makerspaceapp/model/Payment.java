package com.makerspace.makerspaceapp.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long paymentId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private user user;

    private Double amount;
    private String paymentMethod; // CARD, CASH
    private String status; // PENDING, SUCCESS, FAILED
    
    @Builder.Default
    private LocalDateTime paymentDate = LocalDateTime.now();
}
