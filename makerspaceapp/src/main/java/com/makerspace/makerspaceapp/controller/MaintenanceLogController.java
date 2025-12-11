package com.makerspace.makerspaceapp.controller;

import com.makerspace.makerspaceapp.dto.ApiResponse;
import com.makerspace.makerspaceapp.model.MaintenanceLog;
import com.makerspace.makerspaceapp.service.MaintenanceLogService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/maintenance")
@CrossOrigin(origins = "*")
public class MaintenanceLogController {

    private final MaintenanceLogService maintenanceLogService;

    public MaintenanceLogController(MaintenanceLogService maintenanceLogService) {
        this.maintenanceLogService = maintenanceLogService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<MaintenanceLog>> createMaintenanceLog(@RequestBody MaintenanceLog log) {
        MaintenanceLog created = maintenanceLogService.createMaintenanceLog(log);
        ApiResponse<MaintenanceLog> response = new ApiResponse<>(true, "Maintenance log created successfully", created);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<MaintenanceLog>>> getAllMaintenanceLogs() {
        List<MaintenanceLog> logs = maintenanceLogService.getAllMaintenanceLogs();
        ApiResponse<List<MaintenanceLog>> response = new ApiResponse<>(true, "Maintenance logs retrieved successfully", logs);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<MaintenanceLog>> getMaintenanceLogById(@PathVariable Long id) {
        MaintenanceLog log = maintenanceLogService.getMaintenanceLogById(id);
        ApiResponse<MaintenanceLog> response = new ApiResponse<>(true, "Maintenance log retrieved successfully", log);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/tool/{toolId}")
    public ResponseEntity<ApiResponse<List<MaintenanceLog>>> getMaintenanceLogsByTool(@PathVariable Long toolId) {
        List<MaintenanceLog> logs = maintenanceLogService.getMaintenanceLogsByTool(toolId);
        ApiResponse<List<MaintenanceLog>> response = new ApiResponse<>(true, "Tool maintenance logs retrieved successfully", logs);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<ApiResponse<List<MaintenanceLog>>> getMaintenanceLogsByStatus(@PathVariable String status) {
        List<MaintenanceLog> logs = maintenanceLogService.getMaintenanceLogsByStatus(status);
        ApiResponse<List<MaintenanceLog>> response = new ApiResponse<>(true, "Maintenance logs retrieved successfully", logs);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<MaintenanceLog>> updateMaintenanceLog(
            @PathVariable Long id,
            @RequestBody MaintenanceLog log) {
        MaintenanceLog updated = maintenanceLogService.updateMaintenanceLog(id, log);
        ApiResponse<MaintenanceLog> response = new ApiResponse<>(true, "Maintenance log updated successfully", updated);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteMaintenanceLog(@PathVariable Long id) {
        maintenanceLogService.deleteMaintenanceLog(id);
        ApiResponse<Void> response = new ApiResponse<>(true, "Maintenance log deleted successfully", null);
        return ResponseEntity.ok(response);
    }
}
