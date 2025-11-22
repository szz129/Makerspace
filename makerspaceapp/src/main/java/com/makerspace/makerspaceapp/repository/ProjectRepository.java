package com.makerspace.makerspaceapp.repository;

import com.makerspace.makerspaceapp.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    List<Project> findByCreator_UserId(Long creatorId);
    List<Project> findByStatus(String status);
    List<Project> findByCategory(String category);
}