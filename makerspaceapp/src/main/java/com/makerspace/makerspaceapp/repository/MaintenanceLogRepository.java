package com.makerspace.makerspaceapp.repository;

import com.makerspace.makerspaceapp.model.MaintenanceLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MaintenanceLogRepository extends JpaRepository<MaintenanceLog, Long> {
    List<MaintenanceLog> findByTool_ToolId(Long toolId);
    List<MaintenanceLog> findByStatus(String status);
}