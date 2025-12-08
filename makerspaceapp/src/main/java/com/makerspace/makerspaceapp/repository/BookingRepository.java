package com.makerspace.makerspaceapp.repository;

import com.makerspace.makerspaceapp.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findByUser_UserId(Long userId);
    List<Booking> findByTool_ToolId(Long toolId);
    List<Booking> findByMakerspace_MakerspaceId(Long makerspaceId);
    List<Booking> findByStatus(String status);
    
        // Custom query to check if new booking overlaps with existing ones
    @Query("SELECT b FROM Booking b WHERE b.tool.toolId = :toolId " +
           "AND b.status != 'CANCELLED' " +
           "AND ((b.startTime <= :endTime AND b.endTime >= :startTime))")
    List<Booking> findConflictingBookings(
        @Param("toolId") Long toolId,
        @Param("startTime") LocalDateTime startTime,
        @Param("endTime") LocalDateTime endTime
    );
}
