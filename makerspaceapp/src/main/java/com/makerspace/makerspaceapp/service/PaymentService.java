package com.makerspace.makerspaceapp.service;

import com.makerspace.makerspaceapp.exception.ResourceNotFoundException;
import com.makerspace.makerspaceapp.model.Payment;
import com.makerspace.makerspaceapp.model.User;
import com.makerspace.makerspaceapp.repository.PaymentRepository;
import com.makerspace.makerspaceapp.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final UserRepository userRepository;

    public PaymentService(PaymentRepository paymentRepository, UserRepository userRepository) {
        this.paymentRepository = paymentRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public Payment createPayment(Payment payment) {
        if (payment.getUser() == null || payment.getUser().getUserId() == null) {
            throw new IllegalArgumentException("User ID cannot be null");
        }

        User user = userRepository.findById(payment.getUser().getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + payment.getUser().getUserId()));

        payment.setUser(user);
        
        // Set default status if not provided
        if (payment.getStatus() == null || payment.getStatus().trim().isEmpty()) {
            payment.setStatus("PENDING");
        }

        // Payment date is auto-set by @PrePersist in Payment entity
        return paymentRepository.save(payment);
    }

    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    public Payment getPaymentById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Payment ID cannot be null");
        }
        return paymentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Payment not found with id: " + id));
    }

    public List<Payment> getPaymentsByUser(Long userId) {
        if (userId == null) {
            throw new IllegalArgumentException("User ID cannot be null");
        }
        return paymentRepository.findByUser_UserId(userId);
    }

    public List<Payment> getPaymentsByStatus(String status) {
        if (status == null || status.trim().isEmpty()) {
            throw new IllegalArgumentException("Status cannot be null or empty");
        }
        return paymentRepository.findByStatus(status);
    }

    @Transactional
    public Payment updatePaymentStatus(Long id, String status) {
        if (id == null) {
            throw new IllegalArgumentException("Payment ID cannot be null");
        }
        if (status == null || status.trim().isEmpty()) {
            throw new IllegalArgumentException("Status cannot be null or empty");
        }

        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Payment not found with id: " + id));

        payment.setStatus(status);
        return paymentRepository.save(payment);
    }

    @Transactional
    public Payment updatePayment(Long id, Payment updatedPayment) {
        if (id == null) {
            throw new IllegalArgumentException("Payment ID cannot be null");
        }

        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Payment not found with id: " + id));

        if (updatedPayment.getAmount() != null) {
            payment.setAmount(updatedPayment.getAmount());
        }
        if (updatedPayment.getPaymentMethod() != null) {
            payment.setPaymentMethod(updatedPayment.getPaymentMethod());
        }
        if (updatedPayment.getStatus() != null) {
            payment.setStatus(updatedPayment.getStatus());
        }

        return paymentRepository.save(payment);
    }

    @Transactional
    public void deletePayment(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Payment ID cannot be null");
        }
        if (!paymentRepository.existsById(id)) {
            throw new ResourceNotFoundException("Payment not found with id: " + id);
        }
        paymentRepository.deleteById(id);
    }
}