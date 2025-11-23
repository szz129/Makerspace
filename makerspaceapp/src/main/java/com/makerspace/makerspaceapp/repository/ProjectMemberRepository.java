package com.makerspace.makerspaceapp.repository;

import com.makerspace.makerspaceapp.model.ProjectMember;
import com.makerspace.makerspaceapp.model.ProjectMemberID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProjectMemberRepository extends JpaRepository<ProjectMember, ProjectMemberID> {
    List<ProjectMember> findByProject_ProjectId(Long projectId);  // Changed from findByProjectId
    List<ProjectMember> findByUser_UserId(Long userId);  // Changed from findByUserId
}