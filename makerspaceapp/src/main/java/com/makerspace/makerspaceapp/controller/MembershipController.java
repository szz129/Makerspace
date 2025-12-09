package com.makerspace.makerspaceapp.controller;

import com.makerspace.makerspaceapp.dto.ApiResponse;
import com.makerspace.makerspaceapp.dto.MembershipRequest;
import com.makerspace.makerspaceapp.model.Membership;
import com.makerspace.makerspaceapp.service.MembershipService;

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
    public ResponseEntity<ApiResponse<Membership>> createMembership(@RequestBody MembershipRequest request) {
        Membership created = membershipService.createMembership(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(true, "Membership created successfully", created));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Membership>>> getAllMemberships() {
        return ResponseEntity.ok(new ApiResponse<>(true, "Memberships retrieved", membershipService.getAllMemberships()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Membership>> getMembershipById(@PathVariable Long id) {
        return ResponseEntity.ok(new ApiResponse<>(true, "Membership retrieved", membershipService.getMembershipById(id)));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<ApiResponse<List<Membership>>> getByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(new ApiResponse<>(true, "User memberships retrieved", membershipService.getMembershipsByUser(userId)));
    }

    @GetMapping("/user/{userId}/active")
    public ResponseEntity<ApiResponse<Membership>> getActiveMembershipByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(new ApiResponse<>(true, "Active membership retrieved", membershipService.getActiveMembershipByUser(userId)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Membership>> updateMembership(
            @PathVariable Long id,
            @RequestBody MembershipRequest request) {

        Membership updated = membershipService.updateMembership(id, request);
        return ResponseEntity.ok(new ApiResponse<>(true, "Membership updated", updated));
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<ApiResponse<Membership>> updateStatus(@PathVariable Long id, @RequestParam String status) {
        return ResponseEntity.ok(new ApiResponse<>(true, "Status updated",
                membershipService.updateMembershipStatus(id, status)));
    }

    @PatchMapping("/{id}/renew")
    public ResponseEntity<ApiResponse<Membership>> renew(
            @PathVariable Long id,
            @RequestParam LocalDate newEndDate) {

        return ResponseEntity.ok(new ApiResponse<>(true, "Membership renewed",
                membershipService.renewMembership(id, newEndDate)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteMembership(@PathVariable Long id) {
        membershipService.deleteMembership(id);
        return ResponseEntity.ok(new ApiResponse<>(true, "Membership deleted", null));
    }
}