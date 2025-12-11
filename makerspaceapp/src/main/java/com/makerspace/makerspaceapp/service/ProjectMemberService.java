package com.makerspace.makerspaceapp.service;

import com.makerspace.makerspaceapp.dto.ProjectMemberRequest;
import com.makerspace.makerspaceapp.dto.ProjectMemberResponse;
import com.makerspace.makerspaceapp.exception.ConflictException;
import com.makerspace.makerspaceapp.exception.ResourceNotFoundException;
import com.makerspace.makerspaceapp.model.Project;
import com.makerspace.makerspaceapp.model.ProjectMember;
import com.makerspace.makerspaceapp.model.ProjectMemberID;
import com.makerspace.makerspaceapp.model.User;
import com.makerspace.makerspaceapp.repository.ProjectMemberRepository;
import com.makerspace.makerspaceapp.repository.ProjectRepository;
import com.makerspace.makerspaceapp.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectMemberService {

    private final ProjectMemberRepository projectMemberRepository;
    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;

    public ProjectMemberService(ProjectMemberRepository projectMemberRepository,
                               ProjectRepository projectRepository,
                               UserRepository userRepository) {
        this.projectMemberRepository = projectMemberRepository;
        this.projectRepository = projectRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public ProjectMemberResponse addMemberToProject(ProjectMemberRequest request) {
        // Validate project exists
        Project project = projectRepository.findById(request.getProjectId())
                .orElseThrow(() -> new ResourceNotFoundException(
                    "Project", "id", request.getProjectId()));

        // Validate user exists
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException(
                    "User", "id", request.getUserId()));

        // Check if member already exists in project
        ProjectMemberID id = new ProjectMemberID(request.getProjectId(), request.getUserId());
        if (projectMemberRepository.existsById(id)) {
            throw new ConflictException("User " + user.getName() + 
                " is already a member of project " + project.getTitle());
        }

        ProjectMember member = new ProjectMember();
        member.setProject(project);
        member.setUser(user);
        
        // Set role, default to "Member" if not provided
        if (request.getRoleInProject() != null && !request.getRoleInProject().trim().isEmpty()) {
            member.setRoleInProject(request.getRoleInProject());
        } else {
            member.setRoleInProject("Member");
        }

        ProjectMember saved = projectMemberRepository.save(member);
        return mapToResponse(saved);
    }

    public List<ProjectMemberResponse> getAllProjectMembers() {
        return projectMemberRepository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public List<ProjectMemberResponse> getMembersByProject(Long projectId) {
        return projectMemberRepository.findByProject_ProjectId(projectId).stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public List<ProjectMemberResponse> getProjectsByUser(Long userId) {
        return projectMemberRepository.findByUser_UserId(userId).stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Transactional
    public ProjectMemberResponse updateMemberRole(Long projectId, Long userId, String newRole) {
        ProjectMemberID id = new ProjectMemberID(projectId, userId);
        
        ProjectMember member = projectMemberRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                    "Project member not found for project ID: " + projectId + " and user ID: " + userId));

        member.setRoleInProject(newRole);
        ProjectMember updated = projectMemberRepository.save(member);
        return mapToResponse(updated);
    }

    @Transactional
    public void removeMemberFromProject(Long projectId, Long userId) {
        ProjectMemberID id = new ProjectMemberID(projectId, userId);
        
        if (!projectMemberRepository.existsById(id)) {
            throw new ResourceNotFoundException(
                "Project member not found for project ID: " + projectId + " and user ID: " + userId);
        }
        
        projectMemberRepository.deleteById(id);
    }

    private ProjectMemberResponse mapToResponse(ProjectMember member) {
        ProjectMemberResponse response = new ProjectMemberResponse();
        
        if (member.getProject() != null) {
            response.setProjectId(member.getProject().getProjectId());
            response.setProjectTitle(member.getProject().getTitle());
        }
        
        if (member.getUser() != null) {
            response.setUserId(member.getUser().getUserId());
            response.setUserName(member.getUser().getName());
            response.setUserEmail(member.getUser().getEmail());
        }
        
        response.setRoleInProject(member.getRoleInProject());
        response.setJoinedAt(member.getJoinedAt());
        
        return response;
    }
}