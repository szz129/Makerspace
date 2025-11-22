package com.makerspace.makerspaceapp.repository;

import com.makerspace.makerspaceapp.model.Achievement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AchievementRepository extends JpaRepository<Achievement, Long> {
    List<Achievement> findByUser_UserId(Long userId);
    List<Achievement> findByType(String type);
}