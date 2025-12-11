package com.makerspace.makerspaceapp.service;

import com.makerspace.makerspaceapp.dto.UserProfileRequest;
import com.makerspace.makerspaceapp.dto.UserProfileResponse;
import com.makerspace.makerspaceapp.exception.ConflictException;
import com.makerspace.makerspaceapp.exception.ResourceNotFoundException;
import com.makerspace.makerspaceapp.model.User;
import com.makerspace.makerspaceapp.model.UserProfile;
import com.makerspace.makerspaceapp.repository.UserProfileRepository;
import com.makerspace.makerspaceapp.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserProfileService {

    private final UserProfileRepository userProfileRepository;
    private final UserRepository userRepository;

    public UserProfileService(UserProfileRepository userProfileRepository,
                             UserRepository userRepository) {
        this.userProfileRepository = userProfileRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public UserProfileResponse createProfile(UserProfileRequest request) {
        // Validate user exists
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException(
                    "User", "id", request.getUserId()));

        // Check if profile already exists for this user
        UserProfile existing = userProfileRepository.findByUser_UserId(request.getUserId());
        if (existing != null) {
            throw new ConflictException("Profile already exists for user ID: " + request.getUserId());
        }

        UserProfile profile = new UserProfile();
        profile.setUser(user);
        profile.setBio(request.getBio());
        profile.setProfilePicture(request.getProfilePicture());
        profile.setAddress(request.getAddress());
        profile.setSocialLinks(request.getSocialLinks());

        UserProfile saved = userProfileRepository.save(profile);
        return mapToResponse(saved);
    }

    public List<UserProfileResponse> getAllProfiles() {
        return userProfileRepository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public UserProfileResponse getProfileById(Long id) {
        UserProfile profile = userProfileRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                    "User Profile", "id", id));
        return mapToResponse(profile);
    }

    public UserProfileResponse getProfileByUserId(Long userId) {
        UserProfile profile = userProfileRepository.findByUser_UserId(userId);
        if (profile == null) {
            throw new ResourceNotFoundException(
                "User Profile", "userId", userId);
        }
        return mapToResponse(profile);
    }

    @Transactional
    public UserProfileResponse updateProfile(Long id, UserProfileRequest request) {
        UserProfile profile = userProfileRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                    "User Profile", "id", id));

        // Update only non-null fields
        if (request.getBio() != null) {
            profile.setBio(request.getBio());
        }
        if (request.getProfilePicture() != null) {
            profile.setProfilePicture(request.getProfilePicture());
        }
        if (request.getAddress() != null) {
            profile.setAddress(request.getAddress());
        }
        if (request.getSocialLinks() != null) {
            profile.setSocialLinks(request.getSocialLinks());
        }

        UserProfile updated = userProfileRepository.save(profile);
        return mapToResponse(updated);
    }

    @Transactional
    public UserProfileResponse updateProfileByUserId(Long userId, UserProfileRequest request) {
        UserProfile profile = userProfileRepository.findByUser_UserId(userId);
        if (profile == null) {
            throw new ResourceNotFoundException(
                "User Profile", "userId", userId);
        }

        if (request.getBio() != null) {
            profile.setBio(request.getBio());
        }
        if (request.getProfilePicture() != null) {
            profile.setProfilePicture(request.getProfilePicture());
        }
        if (request.getAddress() != null) {
            profile.setAddress(request.getAddress());
        }
        if (request.getSocialLinks() != null) {
            profile.setSocialLinks(request.getSocialLinks());
        }

        UserProfile updated = userProfileRepository.save(profile);
        return mapToResponse(updated);
    }

    @Transactional
    public void deleteProfile(Long id) {
        if (!userProfileRepository.existsById(id)) {
            throw new ResourceNotFoundException("User Profile", "id", id);
        }
        userProfileRepository.deleteById(id);
    }

    private UserProfileResponse mapToResponse(UserProfile profile) {
        UserProfileResponse response = new UserProfileResponse();
        response.setProfileId(profile.getProfileId());
        
        if (profile.getUser() != null) {
            response.setUserId(profile.getUser().getUserId());
            response.setUserName(profile.getUser().getName());
            response.setEmail(profile.getUser().getEmail());
        }
        
        response.setBio(profile.getBio());
        response.setProfilePicture(profile.getProfilePicture());
        response.setAddress(profile.getAddress());
        response.setSocialLinks(profile.getSocialLinks());
        
        return response;
    }
}