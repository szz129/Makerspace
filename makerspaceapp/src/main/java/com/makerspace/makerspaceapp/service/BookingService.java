package com.makerspace.makerspaceapp.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.makerspace.makerspaceapp.dto.BookingRequest;
import com.makerspace.makerspaceapp.dto.BookingResponse;
import com.makerspace.makerspaceapp.exception.BadRequestException;
import com.makerspace.makerspaceapp.exception.ResourceNotFoundException;
import com.makerspace.makerspaceapp.model.Booking;
import com.makerspace.makerspaceapp.model.Makerspace;
import com.makerspace.makerspaceapp.model.Payment;
import com.makerspace.makerspaceapp.model.Tool;
import com.makerspace.makerspaceapp.model.User;
import com.makerspace.makerspaceapp.repository.BookingRepository;
import com.makerspace.makerspaceapp.repository.MakerspaceRepository;
import com.makerspace.makerspaceapp.repository.ToolRepository;
import com.makerspace.makerspaceapp.repository.UserRepository;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;
    private final UserRepository userRepository;
    private final ToolRepository toolRepository;
    private final MakerspaceRepository makerspaceRepository;

    public BookingService(BookingRepository bookingRepository,
                         UserRepository userRepository,
                         ToolRepository toolRepository,
                         MakerspaceRepository makerspaceRepository) {
        this.bookingRepository = bookingRepository;
        this.userRepository = userRepository;
        this.toolRepository = toolRepository;
        this.makerspaceRepository = makerspaceRepository;
    }

    @Transactional
    public BookingResponse createBooking(BookingRequest request) {
        // Validate request
        if (request.getUserId() == null) {
            throw new IllegalArgumentException("User ID cannot be null");
        }
        if (request.getToolId() == null) {
            throw new IllegalArgumentException("Tool ID cannot be null");
        }
        if (request.getMakerspaceId() == null) {
            throw new IllegalArgumentException("Makerspace ID cannot be null");
        }
        if (request.getStartTime() == null || request.getEndTime() == null) {
            throw new IllegalArgumentException("Start time and end time are required");
        }
        if (request.getEndTime().isBefore(request.getStartTime())) {
            throw new BadRequestException("End time must be after start time");
        }

        // Fetch entities
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + request.getUserId()));

        Tool tool = toolRepository.findById(request.getToolId())
                .orElseThrow(() -> new ResourceNotFoundException("Tool not found with id: " + request.getToolId()));

        Makerspace makerspace = makerspaceRepository.findById(request.getMakerspaceId())
                .orElseThrow(() -> new ResourceNotFoundException("Makerspace not found with id: " + request.getMakerspaceId()));

        // Check if tool is available
        if (!"AVAILABLE".equalsIgnoreCase(tool.getAvailabilityStatus())) {
            throw new BadRequestException("Tool is not available for booking");
        }

        // Check for conflicting bookings
        List<Booking> conflicts = bookingRepository.findConflictingBookings(
                request.getToolId(),
                request.getStartTime(),
                request.getEndTime()
        );

        if (!conflicts.isEmpty()) {
            throw new BadRequestException("Tool is already booked for the selected time slot");
        }

        // Create booking
        Booking booking = new Booking();
        booking.setUser(user);
        booking.setTool(tool);
        booking.setMakerspace(makerspace);
        booking.setStartTime(request.getStartTime());
        booking.setEndTime(request.getEndTime());
        booking.setStatus("CONFIRMED");

        Booking savedBooking = bookingRepository.save(booking);

        // Update tool status to BOOKED
        tool.setAvailabilityStatus("BOOKED");
        toolRepository.save(tool);

        return mapToResponse(savedBooking);
    }

    public List<BookingResponse> getAllBookings() {
        return bookingRepository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public BookingResponse getBookingById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Booking ID cannot be null");
        }
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Booking not found with id: " + id));
        return mapToResponse(booking);
    }

    public List<BookingResponse> getBookingsByUser(Long userId) {
        if (userId == null) {
            throw new IllegalArgumentException("User ID cannot be null");
        }
        return bookingRepository.findByUser_UserId(userId).stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public List<BookingResponse> getBookingsByTool(Long toolId) {
        if (toolId == null) {
            throw new IllegalArgumentException("Tool ID cannot be null");
        }
        return bookingRepository.findByTool_ToolId(toolId).stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public List<BookingResponse> getBookingsByMakerspace(Long makerspaceId) {
        if (makerspaceId == null) {
            throw new IllegalArgumentException("Makerspace ID cannot be null");
        }
        return bookingRepository.findByMakerspace_MakerspaceId(makerspaceId).stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public List<BookingResponse> getBookingsByStatus(String status) {
        if (status == null || status.trim().isEmpty()) {
            throw new IllegalArgumentException("Status cannot be null or empty");
        }
        return bookingRepository.findByStatus(status).stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Transactional
    public BookingResponse updateBookingStatus(Long id, String status) {
        if (id == null) {
            throw new IllegalArgumentException("Booking ID cannot be null");
        }
        if (status == null || status.trim().isEmpty()) {
            throw new IllegalArgumentException("Status cannot be null or empty");
        }

        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Booking not found with id: " + id));

        String oldStatus = booking.getStatus();
        booking.setStatus(status);

        // If booking is completed or cancelled, make tool available again
        if ("COMPLETED".equalsIgnoreCase(status) || "CANCELLED".equalsIgnoreCase(status)) {
            Tool tool = booking.getTool();
            if (tool != null) {
                tool.setAvailabilityStatus("AVAILABLE");
                toolRepository.save(tool);
            }
        }

        Booking updated = bookingRepository.save(booking);
        return mapToResponse(updated);
    }

    @Transactional
    public void deleteBooking(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Booking ID cannot be null");
        }

        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Booking not found with id: " + id));

        // Make tool available again
        Tool tool = booking.getTool();
        if (tool != null && "BOOKED".equalsIgnoreCase(tool.getAvailabilityStatus())) {
            tool.setAvailabilityStatus("AVAILABLE");
            toolRepository.save(tool);
        }

        bookingRepository.deleteById(id);
    }

    private BookingResponse mapToResponse(Booking booking) {
        BookingResponse response = new BookingResponse();

        if (booking.getBookingId() != null) {
            response.setBookingId(booking.getBookingId());
        }

        User user = booking.getUser();
        if (user != null) {
            response.setUserId(user.getUserId());
            response.setUserName(user.getName());
        }

        Tool tool = booking.getTool();
        if (tool != null) {
            response.setToolId(tool.getToolId());
            response.setToolName(tool.getName());
        }

        Makerspace makerspace = booking.getMakerspace();
        if (makerspace != null) {
            response.setMakerspaceId(makerspace.getMakerspaceId());
            response.setMakerspaceName(makerspace.getName());
        }

        response.setStartTime(booking.getStartTime());
        response.setEndTime(booking.getEndTime());
        response.setStatus(booking.getStatus());

        Payment payment = booking.getPayment();
        if (payment != null) {
            response.setPaymentId(payment.getPaymentId());
        }

        return response;
    }
}