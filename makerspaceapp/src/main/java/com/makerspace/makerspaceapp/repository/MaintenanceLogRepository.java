package com.makerspace.makerspaceapp.repository;

import com.makerspace.makerspaceapp.model.MaintenanceLog;
import com.makerspace.makerspaceapp.model.MaintenanceStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface MaintenanceLogRepository extends JpaRepository<MaintenanceLog, Long> {

    List<MaintenanceLog> findByTool_ToolId(Long toolId);
    List<MaintenanceLog> findByStatus(MaintenanceStatus status); 
}
