package com.makerspace.makerspaceapp.controller;

import com.makerspace.makerspaceapp.dto.ApiResponse;
import com.makerspace.makerspaceapp.model.Membership;
import com.makerspace.makerspaceapp.service.MembershipService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/memberships")
@CrossOrigin(origins = "*")
public class MembershipController {

    private final MembershipService membershipService;

    public MembershipController(MembershipService membershipService) {
        this.membershipService = membershipService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Membership>> createMembership(@RequestBody Membership membership) {
        Membership created = membershipService.createMembership(membership);
        ApiResponse<Membership> response = new ApiResponse<>(true, "Membership created successfully", created);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Membership>>> getAllMemberships() {
        List<Membership> memberships = membershipService.getAllMemberships();
        ApiResponse<List<Membership>> response = new ApiResponse<>(true, "Memberships retrieved successfully", memberships);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Membership>> getMembershipById(@PathVariable Long id) {
        Membership membership = membershipService.getMembershipById(id);
        ApiResponse<Membership> response = new ApiResponse<>(true, "Membership retrieved successfully", membership);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<ApiResponse<List<Membership>>> getMembershipsByUser(@PathVariable Long userId) {
        List<Membership> memberships = membershipService.getMembershipsByUser(userId);
        ApiResponse<List<Membership>> response = new ApiResponse<>(true, "User memberships retrieved successfully", memberships);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/user/{userId}/active")
    public ResponseEntity<ApiResponse<Membership>> getActiveMembershipByUser(@PathVariable Long userId) {
        Membership membership = membershipService.getActiveMembershipByUser(userId);
        ApiResponse<Membership> response = new ApiResponse<>(true, "Active membership retrieved successfully", membership);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<ApiResponse<List<Membership>>> getMembershipsByStatus(@PathVariable String status) {
        List<Membership> memberships = membershipService.getMembershipsByStatus(status);
        ApiResponse<List<Membership>> response = new ApiResponse<>(true, "Memberships retrieved successfully", memberships);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Membership>> updateMembership(
            @PathVariable Long id,
            @RequestBody Membership membership) {
        Membership updated = membershipService.updateMembership(id, membership);
        ApiResponse<Membership> response = new ApiResponse<>(true, "Membership updated successfully", updated);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<ApiResponse<Membership>> updateMembershipStatus(
            @PathVariable Long id,
            @RequestParam String status) {
        Membership membership = membershipService.updateMembershipStatus(id, status);
        ApiResponse<Membership> response = new ApiResponse<>(true, "Membership status updated successfully", membership);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{id}/renew")
    public ResponseEntity<ApiResponse<Membership>> renewMembership(
            @PathVariable Long id,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate newEndDate) {
        Membership membership = membershipService.renewMembership(id, newEndDate);
        ApiResponse<Membership> response = new ApiResponse<>(true, "Membership renewed successfully", membership);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteMembership(@PathVariable Long id) {
        membershipService.deleteMembership(id);
        ApiResponse<Void> response = new ApiResponse<>(true, "Membership deleted successfully", null);
        return ResponseEntity.ok(response);
    }
}
