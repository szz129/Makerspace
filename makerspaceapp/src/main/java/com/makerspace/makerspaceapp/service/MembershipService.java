package com.makerspace.makerspaceapp.service;

import com.makerspace.makerspaceapp.dto.MembershipRequest;
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
    public Membership createMembership(MembershipRequest request) {

        if (request.getUserId() == null) {
            throw new IllegalArgumentException("User ID cannot be null");
        }

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + request.getUserId()));

        Membership membership = new Membership();
        membership.setUser(user);
        membership.setPlanType(request.getPlanType());
        membership.setStartDate(request.getStartDate() != null ? request.getStartDate() : LocalDate.now());
        membership.setEndDate(request.getEndDate());
        membership.setStatus(request.getStatus() != null ? request.getStatus() : "ACTIVE");
        membership.setPrice(request.getPrice());

        return membershipRepository.save(membership);
    }

    public List<Membership> getAllMemberships() {
        return membershipRepository.findAll();
    }

    public Membership getMembershipById(Long id) {
        return membershipRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Membership not found with id: " + id));
    }

    public List<Membership> getMembershipsByUser(Long userId) {
        return membershipRepository.findByUser_UserId(userId);
    }

    public Membership getActiveMembershipByUser(Long userId) {
        return membershipRepository.findActiveByUserId(userId);
    }

    @Transactional
    public Membership updateMembership(Long id, MembershipRequest request) {

        Membership membership = membershipRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Membership not found with id: " + id));

        if (request.getPlanType() != null)
            membership.setPlanType(request.getPlanType());

        if (request.getStartDate() != null)
            membership.setStartDate(request.getStartDate());

        if (request.getEndDate() != null)
            membership.setEndDate(request.getEndDate());

        if (request.getStatus() != null)
            membership.setStatus(request.getStatus());

        if (request.getPrice() != null)
            membership.setPrice(request.getPrice());

        return membershipRepository.save(membership);
    }

    @Transactional
    public Membership updateMembershipStatus(Long id, String status) {
        Membership membership = getMembershipById(id);
        membership.setStatus(status);
        return membershipRepository.save(membership);
    }

    @Transactional
    public Membership renewMembership(Long id, LocalDate newEndDate) {
        Membership membership = getMembershipById(id);
        membership.setEndDate(newEndDate);
        membership.setStatus("ACTIVE");
        return membershipRepository.save(membership);
    }

    @Transactional
    public void deleteMembership(Long id) {
        membershipRepository.deleteById(id);
    }
}