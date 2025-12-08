package com.makerspace.makerspaceapp.repository;

import com.makerspace.makerspaceapp.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

    // By makerspace
    List<Event> findByMakerspace_MakerspaceId(Long makerspaceId);

    // Search by title
    List<Event> findByTitleContainingIgnoreCase(String title);

    // By type
    List<Event> findByType(String type);

    // Upcoming events
    List<Event> findByEventDateAfter(LocalDate date);

    // Past events
    List<Event> findByEventDateBefore(LocalDate date);

    // By creator
    List<Event> findByCreatedBy_UserId(Long userId);
}
