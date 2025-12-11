package com.makerspace.makerspaceapp.service;

import com.makerspace.makerspaceapp.dto.AchievementRequest;
import com.makerspace.makerspaceapp.dto.AchievementResponse;
import com.makerspace.makerspaceapp.exception.ResourceNotFoundException;
import com.makerspace.makerspaceapp.model.Achievement;
import com.makerspace.makerspaceapp.model.User;
import com.makerspace.makerspaceapp.repository.AchievementRepository;
import com.makerspace.makerspaceapp.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AchievementService {

    private final AchievementRepository achievementRepository;
    private final UserRepository userRepository;

    public AchievementService(AchievementRepository achievementRepository,
                             UserRepository userRepository) {
        this.achievementRepository = achievementRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public AchievementResponse createAchievement(AchievementRequest request) {
        // Validate user exists
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException(
                    "User", "id", request.getUserId()));

        Achievement achievement = new Achievement();
        achievement.setUser(user);
        achievement.setTitle(request.getTitle());
        achievement.setDescription(request.getDescription());
        
        // Set date earned to today if not provided
        if (request.getDateEarned() != null) {
            achievement.setDateEarned(request.getDateEarned());
        } else {
            achievement.setDateEarned(LocalDate.now());
        }
        
        // Default type if not provided
        if (request.getType() != null && !request.getType().trim().isEmpty()) {
            achievement.setType(request.getType());
        } else {
            achievement.setType("BADGE");
        }

        Achievement saved = achievementRepository.save(achievement);
        return mapToResponse(saved);
    }

    public List<AchievementResponse> getAllAchievements() {
        return achievementRepository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public AchievementResponse getAchievementById(Long id) {
        Achievement achievement = achievementRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                    "Achievement", "id", id));
        return mapToResponse(achievement);
    }

    public List<AchievementResponse> getAchievementsByUser(Long userId) {
        return achievementRepository.findByUser_UserId(userId).stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public List<AchievementResponse> getAchievementsByType(String type) {
        return achievementRepository.findByType(type).stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Transactional
    public AchievementResponse updateAchievement(Long id, AchievementRequest request) {
        Achievement achievement = achievementRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                    "Achievement", "id", id));

        // Update user if changed
        if (request.getUserId() != null && 
            !request.getUserId().equals(achievement.getUser().getUserId())) {
            User user = userRepository.findById(request.getUserId())
                    .orElseThrow(() -> new ResourceNotFoundException(
                        "User", "id", request.getUserId()));
            achievement.setUser(user);
        }

        if (request.getTitle() != null) {
            achievement.setTitle(request.getTitle());
        }
        if (request.getDescription() != null) {
            achievement.setDescription(request.getDescription());
        }
        if (request.getDateEarned() != null) {
            achievement.setDateEarned(request.getDateEarned());
        }
        if (request.getType() != null) {
            achievement.setType(request.getType());
        }

        Achievement updated = achievementRepository.save(achievement);
        return mapToResponse(updated);
    }

    @Transactional
    public void deleteAchievement(Long id) {
        if (!achievementRepository.existsById(id)) {
            throw new ResourceNotFoundException("Achievement", "id", id);
        }
        achievementRepository.deleteById(id);
    }

    private AchievementResponse mapToResponse(Achievement achievement) {
        AchievementResponse response = new AchievementResponse();
        response.setAchievementId(achievement.getAchievementId());
        
        if (achievement.getUser() != null) {
            response.setUserId(achievement.getUser().getUserId());
            response.setUserName(achievement.getUser().getName());
        }
        
        response.setTitle(achievement.getTitle());
        response.setDescription(achievement.getDescription());
        response.setDateEarned(achievement.getDateEarned());
        response.setType(achievement.getType());
        
        return response;
    }
}