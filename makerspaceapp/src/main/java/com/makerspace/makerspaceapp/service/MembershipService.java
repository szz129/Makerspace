package com.makerspace.makerspaceapp.service;

import com.makerspace.makerspaceapp.exception.ResourceNotFoundException;
import com.makerspace.makerspaceapp.model.Membership;
import com.makerspace.makerspaceapp.model.User;
import com.makerspace.makerspaceapp.repository.MembershipRepository;
import com.makerspace.makerspaceapp.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class MembershipService {

    private final MembershipRepository membershipRepository;
    private final UserRepository userRepository;

    public MembershipService(MembershipRepository membershipRepository, UserRepository userRepository) {
        this.membershipRepository = membershipRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public Membership createMembership(Membership membership) {
        if (membership.getUser() == null || membership.getUser().getUserId() == null) {
            throw new IllegalArgumentException("User ID cannot be null");
        }

        User user = userRepository.findById(membership.getUser().getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + membership.getUser().getUserId()));

        membership.setUser(user);

        // Set default status if not provided
        if (membership.getStatus() == null || membership.getStatus().trim().isEmpty()) {
            membership.setStatus("ACTIVE");
        }

        // Set start date to today if not provided
        if (membership.getStartDate() == null) {
            membership.setStartDate(LocalDate.now());
        }

        return membershipRepository.save(membership);
    }

    public List<Membership> getAllMemberships() {
        return membershipRepository.findAll();
    }

    public Membership getMembershipById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Membership ID cannot be null");
        }
        return membershipRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Membership not found with id: " + id));
    }

    public List<Membership> getMembershipsByUser(Long userId) {
        if (userId == null) {
            throw new IllegalArgumentException("User ID cannot be null");
        }
        return membershipRepository.findByUser_UserId(userId);
    }

    public Membership getActiveMembershipByUser(Long userId) {
        if (userId == null) {
            throw new IllegalArgumentException("User ID cannot be null");
        }
        return membershipRepository.findActiveByUserId(userId);
    }

    public List<Membership> getMembershipsByStatus(String status) {
        if (status == null || status.trim().isEmpty()) {
            throw new IllegalArgumentException("Status cannot be null or empty");
        }
        return membershipRepository.findByStatus(status);
    }

    @Transactional
    public Membership updateMembership(Long id, Membership updatedMembership) {
        if (id == null) {
            throw new IllegalArgumentException("Membership ID cannot be null");
        }

        Membership membership = membershipRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Membership not found with id: " + id));

        if (updatedMembership.getPlanType() != null) {
            membership.setPlanType(updatedMembership.getPlanType());
        }
        if (updatedMembership.getStartDate() != null) {
            membership.setStartDate(updatedMembership.getStartDate());
        }
        if (updatedMembership.getEndDate() != null) {
            membership.setEndDate(updatedMembership.getEndDate());
        }
        if (updatedMembership.getStatus() != null) {
            membership.setStatus(updatedMembership.getStatus());
        }
        if (updatedMembership.getPrice() != null) {
            membership.setPrice(updatedMembership.getPrice());
        }

        return membershipRepository.save(membership);
    }

    @Transactional
    public Membership updateMembershipStatus(Long id, String status) {
        if (id == null) {
            throw new IllegalArgumentException("Membership ID cannot be null");
        }
        if (status == null || status.trim().isEmpty()) {
            throw new IllegalArgumentException("Status cannot be null or empty");
        }

        Membership membership = membershipRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Membership not found with id: " + id));

        membership.setStatus(status);
        return membershipRepository.save(membership);
    }

    @Transactional
    public Membership renewMembership(Long id, LocalDate newEndDate) {
        if (id == null) {
            throw new IllegalArgumentException("Membership ID cannot be null");
        }
        if (newEndDate == null) {
            throw new IllegalArgumentException("New end date cannot be null");
        }

        Membership membership = membershipRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Membership not found with id: " + id));

        membership.setEndDate(newEndDate);
        membership.setStatus("ACTIVE");

        return membershipRepository.save(membership);
    }

    @Transactional
    public void deleteMembership(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Membership ID cannot be null");
        }
        if (!membershipRepository.existsById(id)) {
            throw new ResourceNotFoundException("Membership not found with id: " + id);
        }
        membershipRepository.deleteById(id);
    }
}