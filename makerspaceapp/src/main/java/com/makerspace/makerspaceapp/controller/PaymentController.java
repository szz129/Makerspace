package com.makerspace.makerspaceapp.controller;

import com.makerspace.makerspaceapp.dto.ApiResponse;
import com.makerspace.makerspaceapp.model.Payment;
import com.makerspace.makerspaceapp.service.PaymentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payments")
@CrossOrigin(origins = "*")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Payment>> createPayment(@RequestBody Payment payment) {
        Payment created = paymentService.createPayment(payment);
        ApiResponse<Payment> response = new ApiResponse<>(true, "Payment created successfully", created);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Payment>>> getAllPayments() {
        List<Payment> payments = paymentService.getAllPayments();
        ApiResponse<List<Payment>> response = new ApiResponse<>(true, "Payments retrieved successfully", payments);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Payment>> getPaymentById(@PathVariable Long id) {
        Payment payment = paymentService.getPaymentById(id);
        ApiResponse<Payment> response = new ApiResponse<>(true, "Payment retrieved successfully", payment);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<ApiResponse<List<Payment>>> getPaymentsByUser(@PathVariable Long userId) {
        List<Payment> payments = paymentService.getPaymentsByUser(userId);
        ApiResponse<List<Payment>> response = new ApiResponse<>(true, "User payments retrieved successfully", payments);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<ApiResponse<List<Payment>>> getPaymentsByStatus(@PathVariable String status) {
        List<Payment> payments = paymentService.getPaymentsByStatus(status);
        ApiResponse<List<Payment>> response = new ApiResponse<>(true, "Payments retrieved successfully", payments);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<ApiResponse<Payment>> updatePaymentStatus(
            @PathVariable Long id,
            @RequestParam String status) {
        Payment payment = paymentService.updatePaymentStatus(id, status);
        ApiResponse<Payment> response = new ApiResponse<>(true, "Payment status updated successfully", payment);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Payment>> updatePayment(
            @PathVariable Long id,
            @RequestBody Payment payment) {
        Payment updated = paymentService.updatePayment(id, payment);
        ApiResponse<Payment> response = new ApiResponse<>(true, "Payment updated successfully", updated);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deletePayment(@PathVariable Long id) {
        paymentService.deletePayment(id);
        ApiResponse<Void> response = new ApiResponse<>(true, "Payment deleted successfully", null);
        return ResponseEntity.ok(response);
    }
}