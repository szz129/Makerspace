package com.makerspace.makerspaceapp.service;

import com.makerspace.makerspaceapp.dto.ToolRequest;
import com.makerspace.makerspaceapp.dto.ToolResponse;
import com.makerspace.makerspaceapp.exception.ResourceNotFoundException;
import com.makerspace.makerspaceapp.model.Makerspace;
import com.makerspace.makerspaceapp.model.Tool;
import com.makerspace.makerspaceapp.repository.MakerspaceRepository;
import com.makerspace.makerspaceapp.repository.ToolRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ToolService {
    
    private final ToolRepository toolRepository;
    private final MakerspaceRepository makerspaceRepository;
    
    public ToolService(ToolRepository toolRepository, MakerspaceRepository makerspaceRepository) {
        this.toolRepository = toolRepository;
        this.makerspaceRepository = makerspaceRepository;
    }
    
    @Transactional
    public ToolResponse createTool(ToolRequest request) {
        Long makerspaceId = request.getMakerspaceId();
        if (makerspaceId == null) {
            throw new IllegalArgumentException("Makerspace ID cannot be null");
        }
        
        Makerspace makerspace = makerspaceRepository.findById(makerspaceId)
                .orElseThrow(() -> new ResourceNotFoundException("Makerspace not found with id: " + makerspaceId));
        
        Tool tool = new Tool();
        tool.setMakerspace(makerspace);
        tool.setName(request.getName());
        tool.setCategory(request.getCategory());
        tool.setToolCondition(request.getToolCondition());
        tool.setAvailabilityStatus(request.getAvailabilityStatus() != null ? request.getAvailabilityStatus() : "AVAILABLE");
        tool.setImageUrl(request.getImageUrl());
        
        Tool savedTool = toolRepository.save(tool);
        return mapToResponse(savedTool);
    }
    
    public List<ToolResponse> getAllTools() {
        return toolRepository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }
    
    public ToolResponse getToolById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Tool ID cannot be null");
        }
        
        Tool tool = toolRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tool not found with id: " + id));
        return mapToResponse(tool);
    }
    
    public List<ToolResponse> getToolsByMakerspace(Long makerspaceId) {
        if (makerspaceId == null) {
            throw new IllegalArgumentException("Makerspace ID cannot be null");
        }
        
        return toolRepository.findByMakerspace_MakerspaceId(makerspaceId).stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }
    
    public List<ToolResponse> getToolsByCategory(String category) {
        if (category == null || category.trim().isEmpty()) {
            throw new IllegalArgumentException("Category cannot be null or empty");
        }
        
        return toolRepository.findByCategory(category).stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }
    
    public List<ToolResponse> getAvailableTools(Long makerspaceId) {
        if (makerspaceId == null) {
            throw new IllegalArgumentException("Makerspace ID cannot be null");
        }
        
        return toolRepository.findAvailableToolsByMakerspace(makerspaceId).stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }
    
    @Transactional
    public ToolResponse updateTool(Long id, ToolRequest request) {
        if (id == null) {
            throw new IllegalArgumentException("Tool ID cannot be null");
        }
        
        Tool tool = toolRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tool not found with id: " + id));
        
        Long makerspaceId = request.getMakerspaceId();
        if (makerspaceId != null) {
            Makerspace makerspace = makerspaceRepository.findById(makerspaceId)
                    .orElseThrow(() -> new ResourceNotFoundException("Makerspace not found with id: " + makerspaceId));
            tool.setMakerspace(makerspace);
        }
        
        if (request.getName() != null) tool.setName(request.getName());
        if (request.getCategory() != null) tool.setCategory(request.getCategory());
        if (request.getToolCondition() != null) tool.setToolCondition(request.getToolCondition());
        if (request.getAvailabilityStatus() != null) tool.setAvailabilityStatus(request.getAvailabilityStatus());
        if (request.getImageUrl() != null) tool.setImageUrl(request.getImageUrl());
        
        Tool updatedTool = toolRepository.save(tool);
        return mapToResponse(updatedTool);
    }
    
    @Transactional
    public void deleteTool(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Tool ID cannot be null");
        }
        
        if (!toolRepository.existsById(id)) {
            throw new ResourceNotFoundException("Tool not found with id: " + id);
        }
        toolRepository.deleteById(id);
    }
    
    @Transactional
    public void updateToolStatus(Long toolId, String status) {
        if (toolId == null) {
            throw new IllegalArgumentException("Tool ID cannot be null");
        }
        if (status == null || status.trim().isEmpty()) {
            throw new IllegalArgumentException("Status cannot be null or empty");
        }
        
        Tool tool = toolRepository.findById(toolId)
                .orElseThrow(() -> new ResourceNotFoundException("Tool not found with id: " + toolId));
        tool.setAvailabilityStatus(status);
        toolRepository.save(tool);
    }
    
    private ToolResponse mapToResponse(Tool tool) {
        ToolResponse response = new ToolResponse();
        
        Long toolId = tool.getToolId();
        if (toolId != null) {
            response.setToolId(toolId);
        }
        
        Makerspace makerspace = tool.getMakerspace();
        if (makerspace != null) {
            Long makerspaceId = makerspace.getMakerspaceId();
            if (makerspaceId != null) {
                response.setMakerspaceId(makerspaceId);
            }
            response.setMakerspaceName(makerspace.getName());
        }
        
        response.setName(tool.getName());
        response.setCategory(tool.getCategory());
        response.setToolCondition(tool.getToolCondition());
        response.setAvailabilityStatus(tool.getAvailabilityStatus());
        response.setImageUrl(tool.getImageUrl());
        
        return response;
    }
}