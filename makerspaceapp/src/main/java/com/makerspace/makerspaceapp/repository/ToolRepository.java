package com.makerspace.makerspaceapp.repository;

import com.makerspace.makerspaceapp.model.Tool;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ToolRepository extends JpaRepository<Tool, Long> {
    // Find tools where makerspace.makerspaceId = ?
    List<Tool> findByMakerspace_MakerspaceId(Long makerspaceId);
    // Find tools by category column
    List<Tool> findByCategory(String category);
    // Find tools by availabilityStatus column
    List<Tool> findByAvailabilityStatus(String status);
    
    @Query("SELECT t FROM Tool t WHERE t.makerspace.makerspaceId = :makerspaceId AND t.availabilityStatus = 'AVAILABLE'")
    List<Tool> findAvailableToolsByMakerspace(@Param("makerspaceId") Long makerspaceId);
}
