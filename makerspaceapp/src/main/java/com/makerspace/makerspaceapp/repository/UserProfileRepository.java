package com.makerspace.makerspaceapp.repository;

import com.makerspace.makerspaceapp.model.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {
    // findByUser_UserId --> user.userId in the UserProfile entity
    // SQL generated: SELECT * FROM user_profile WHERE user_id = ?
    UserProfile findByUser_UserId(Long userId);
}
