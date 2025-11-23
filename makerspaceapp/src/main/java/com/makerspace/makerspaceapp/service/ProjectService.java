package com.makerspace.makerspaceapp.service;

import com.makerspace.makerspaceapp.dto.ProjectRequest;
import com.makerspace.makerspaceapp.dto.ProjectResponse;
import com.makerspace.makerspaceapp.exception.ResourceNotFoundException;
import com.makerspace.makerspaceapp.model.Project;
import com.makerspace.makerspaceapp.model.User;
import com.makerspace.makerspaceapp.repository.ProjectRepository;
import com.makerspace.makerspaceapp.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectService {
    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;
    
    public ProjectService(ProjectRepository projectRepository, UserRepository userRepository) {
        this.projectRepository = projectRepository;
        this.userRepository = userRepository;
    }
    
    @Transactional
    public ProjectResponse create(ProjectRequest request) {
        Long creatorId = request.getCreatorId();
        if (creatorId == null) {
            throw new IllegalArgumentException("Creator ID cannot be null");
        }
        
        User creator = userRepository.findById(creatorId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + creatorId));
        
        Project project = new Project();
        project.setCreator(creator);
        project.setTitle(request.getTitle());
        project.setDescription(request.getDescription());
        project.setCategory(request.getCategory());
        project.setStartDate(request.getStartDate());
        project.setEndDate(request.getEndDate());
        project.setStatus(request.getStatus() != null ? request.getStatus() : "ACTIVE");
        
        Project saved = projectRepository.save(project);
        return mapToResponse(saved);
    }
    
    public List<ProjectResponse> getAll() {
        return projectRepository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }
    
    public ProjectResponse getById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Project ID cannot be null");
        }
        
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Project not found with id: " + id));
        return mapToResponse(project);
    }
    
    public List<ProjectResponse> getByCreator(Long creatorId) {
        if (creatorId == null) {
            throw new IllegalArgumentException("Creator ID cannot be null");
        }
        
        return projectRepository.findByCreator_UserId(creatorId).stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }
    
    public List<ProjectResponse> getByStatus(String status) {
        if (status == null || status.trim().isEmpty()) {
            throw new IllegalArgumentException("Status cannot be null or empty");
        }
        
        return projectRepository.findByStatus(status).stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }
    
    @Transactional
    public ProjectResponse update(Long id, ProjectRequest request) {
        if (id == null) {
            throw new IllegalArgumentException("Project ID cannot be null");
        }
        
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Project not found with id: " + id));
        
        if (request.getTitle() != null) project.setTitle(request.getTitle());
        if (request.getDescription() != null) project.setDescription(request.getDescription());
        if (request.getCategory() != null) project.setCategory(request.getCategory());
        if (request.getStartDate() != null) project.setStartDate(request.getStartDate());
        if (request.getEndDate() != null) project.setEndDate(request.getEndDate());
        if (request.getStatus() != null) project.setStatus(request.getStatus());
        
        Project updated = projectRepository.save(project);
        return mapToResponse(updated);
    }
    
    @Transactional
    public void delete(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Project ID cannot be null");
        }
        
        if (!projectRepository.existsById(id)) {
            throw new ResourceNotFoundException("Project not found with id: " + id);
        }
        projectRepository.deleteById(id);
    }
    
    private ProjectResponse mapToResponse(Project project) {
        ProjectResponse response = new ProjectResponse();
        
        Long projectId = project.getProjectId();
        if (projectId != null) {
            response.setProjectId(projectId);
        }
        
        User creator = project.getCreator();
        if (creator != null) {
            Long creatorId = creator.getUserId();
            if (creatorId != null) {
                response.setCreatorId(creatorId);
            }
            response.setCreatorName(creator.getName());
        }
        
        response.setTitle(project.getTitle());
        response.setDescription(project.getDescription());
        response.setCategory(project.getCategory());
        response.setStartDate(project.getStartDate());
        response.setEndDate(project.getEndDate());
        response.setStatus(project.getStatus());
        
        return response;
    }
}