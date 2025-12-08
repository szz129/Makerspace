package com.makerspace.makerspaceapp.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.makerspace.makerspaceapp.dto.ApiResponse;
import com.makerspace.makerspaceapp.dto.BookingRequest;
import com.makerspace.makerspaceapp.dto.BookingResponse;
import com.makerspace.makerspaceapp.service.BookingService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/bookings")
@CrossOrigin(origins = "*")
public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<BookingResponse>> createBooking(@Valid @RequestBody BookingRequest request) {
        BookingResponse booking = bookingService.createBooking(request);
        ApiResponse<BookingResponse> response = new ApiResponse<>(true, "Booking created successfully", booking);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<BookingResponse>>> getAllBookings() {
        List<BookingResponse> bookings = bookingService.getAllBookings();
        ApiResponse<List<BookingResponse>> response = new ApiResponse<>(true, "Bookings retrieved successfully", bookings);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<BookingResponse>> getBookingById(@PathVariable Long id) {
        BookingResponse booking = bookingService.getBookingById(id);
        ApiResponse<BookingResponse> response = new ApiResponse<>(true, "Booking retrieved successfully", booking);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<ApiResponse<List<BookingResponse>>> getBookingsByUser(@PathVariable Long userId) {
        List<BookingResponse> bookings = bookingService.getBookingsByUser(userId);
        ApiResponse<List<BookingResponse>> response = new ApiResponse<>(true, "User bookings retrieved successfully", bookings);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/tool/{toolId}")
    public ResponseEntity<ApiResponse<List<BookingResponse>>> getBookingsByTool(@PathVariable Long toolId) {
        List<BookingResponse> bookings = bookingService.getBookingsByTool(toolId);
        ApiResponse<List<BookingResponse>> response = new ApiResponse<>(true, "Tool bookings retrieved successfully", bookings);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/makerspace/{makerspaceId}")
    public ResponseEntity<ApiResponse<List<BookingResponse>>> getBookingsByMakerspace(@PathVariable Long makerspaceId) {
        List<BookingResponse> bookings = bookingService.getBookingsByMakerspace(makerspaceId);
        ApiResponse<List<BookingResponse>> response = new ApiResponse<>(true, "Makerspace bookings retrieved successfully", bookings);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<ApiResponse<List<BookingResponse>>> getBookingsByStatus(@PathVariable String status) {
        List<BookingResponse> bookings = bookingService.getBookingsByStatus(status);
        ApiResponse<List<BookingResponse>> response = new ApiResponse<>(true, "Bookings retrieved successfully", bookings);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<ApiResponse<BookingResponse>> updateBookingStatus(
            @PathVariable Long id,
            @RequestParam String status) {
        BookingResponse booking = bookingService.updateBookingStatus(id, status);
        ApiResponse<BookingResponse> response = new ApiResponse<>(true, "Booking status updated successfully", booking);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteBooking(@PathVariable Long id) {
        bookingService.deleteBooking(id);
        ApiResponse<Void> response = new ApiResponse<>(true, "Booking deleted successfully", null);
        return ResponseEntity.ok(response);
    }
}
