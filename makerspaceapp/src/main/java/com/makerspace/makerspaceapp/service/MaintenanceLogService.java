package com.makerspace.makerspaceapp.service;

import com.makerspace.makerspaceapp.exception.ResourceNotFoundException;
import com.makerspace.makerspaceapp.model.MaintenanceLog;
import com.makerspace.makerspaceapp.model.MaintenanceStatus;
import com.makerspace.makerspaceapp.model.Tool;
import com.makerspace.makerspaceapp.repository.MaintenanceLogRepository;
import com.makerspace.makerspaceapp.repository.ToolRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MaintenanceLogService {

    private final MaintenanceLogRepository maintenanceLogRepository;
    private final ToolRepository toolRepository;

    public MaintenanceLogService(MaintenanceLogRepository maintenanceLogRepository,
                                 ToolRepository toolRepository) {
        this.maintenanceLogRepository = maintenanceLogRepository;
        this.toolRepository = toolRepository;
    }

    @Transactional
    public MaintenanceLog createMaintenanceLog(MaintenanceLog log) {
        if (log.getTool() == null || log.getTool().getToolId() == null) {
            throw new IllegalArgumentException("Tool ID cannot be null");
        }

        Tool tool = toolRepository.findById(log.getTool().getToolId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Tool not found with id: " + log.getTool().getToolId()));

        log.setTool(tool);

        // DEFAULT STATUS
        if (log.getStatus() == null) {
            log.setStatus(MaintenanceStatus.OPEN);
        }

        return maintenanceLogRepository.save(log);
    }

    public List<MaintenanceLog> getAllMaintenanceLogs() {
        return maintenanceLogRepository.findAll();
    }

    public MaintenanceLog getMaintenanceLogById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Maintenance log ID cannot be null");
        }
        return maintenanceLogRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Maintenance log not found with id: " + id));
    }

    public List<MaintenanceLog> getMaintenanceLogsByTool(Long toolId) {
        if (toolId == null) {
            throw new IllegalArgumentException("Tool ID cannot be null");
        }
        return maintenanceLogRepository.findByTool_ToolId(toolId);
    }

    public List<MaintenanceLog> getMaintenanceLogsByStatus(String status) {
        if (status == null || status.trim().isEmpty()) {
            throw new IllegalArgumentException("Status cannot be null or empty");
        }

        // Convert string → ENUM safely
        MaintenanceStatus statusEnum;
        try {
            statusEnum = MaintenanceStatus.valueOf(status.trim().toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid status. Allowed: OPEN, IN_PROGRESS, DONE");
        }

        return maintenanceLogRepository.findByStatus(statusEnum);
    }

    @Transactional
    public MaintenanceLog updateMaintenanceLog(Long id, MaintenanceLog updatedLog) {
        if (id == null) {
            throw new IllegalArgumentException("Maintenance log ID cannot be null");
        }

        MaintenanceLog log = maintenanceLogRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Maintenance log not found with id: " + id));

        if (updatedLog.getTechnicianName() != null) {
            log.setTechnicianName(updatedLog.getTechnicianName());
        }
        if (updatedLog.getDescription() != null) {
            log.setDescription(updatedLog.getDescription());
        }
        if (updatedLog.getMaintenanceDate() != null) {
            log.setMaintenanceDate(updatedLog.getMaintenanceDate());
        }

        // STATUS update
        if (updatedLog.getStatus() != null) {
            log.setStatus(updatedLog.getStatus());
        }

        return maintenanceLogRepository.save(log);
    }

    @Transactional
    public void deleteMaintenanceLog(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Maintenance log ID cannot be null");
        }
        if (!maintenanceLogRepository.existsById(id)) {
            throw new ResourceNotFoundException("Maintenance log not found with id: " + id);
        }
        maintenanceLogRepository.deleteById(id);
    }
}
