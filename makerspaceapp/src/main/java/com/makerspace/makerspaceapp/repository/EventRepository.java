package com.makerspace.makerspaceapp.repository;

import com.makerspace.makerspaceapp.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

    // Get events by makerspace (optional but useful)
    List<Event> findByMakerspace_MakerspaceId(Long makerspaceId);

    // Search event by title (case-insensitive)
    List<Event> findByTitleContainingIgnoreCase(String title);

    // Filter by event type (workshop, training, competition)
    List<Event> findByType(String type);

    // List only upcoming events
    List<Event> findByDateAfter(LocalDate date);

    // List only past events
    List<Event> findByDateBefore(LocalDate date);

    // Get events created by a specific admin/user
    List<Event> findByCreatedBy_UserId(Long userId);
}
