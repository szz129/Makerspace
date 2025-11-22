package com.makerspace.makerspaceapp.repository;

import com.makerspace.makerspaceapp.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    List<Payment> findByUser_UserId(Long userId);
    List<Payment> findByStatus(String status);
}