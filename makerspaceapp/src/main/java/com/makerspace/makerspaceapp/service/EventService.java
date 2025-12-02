package com.makerspace.makerspaceapp.service;

import com.makerspace.makerspaceapp.model.Event;
import com.makerspace.makerspaceapp.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    // Create new event
    public Event createEvent(Event event) {
        return eventRepository.save(event);
    }

    // Get event by ID
    public Event getEventById(Long id) {
        return eventRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Event not found with ID: " + id));
    }

    // Get all events
    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    // Update event
    public Event updateEvent(Long id, Event updatedEvent) {
        Event existing = getEventById(id);

        existing.setTitle(updatedEvent.getTitle());
        existing.setDescription(updatedEvent.getDescription());
        existing.setType(updatedEvent.getType());
        existing.setDate(updatedEvent.getDate());
        existing.setLocation(updatedEvent.getLocation());
        existing.setCapacity(updatedEvent.getCapacity());
        existing.setMakerspace(updatedEvent.getMakerspace());
        existing.setCreatedBy(updatedEvent.getCreatedBy());

        return eventRepository.save(existing);
    }

    // Delete event
    public void deleteEvent(Long id) {
        eventRepository.deleteById(id);
    }

    // List events by makerspace
    public List<Event> getEventsByMakerspace(Long makerspaceId) {
        return eventRepository.findByMakerspace_MakerspaceId(makerspaceId);
    }

    // Search events by title
    public List<Event> searchEventsByTitle(String title) {
        return eventRepository.findByTitleContainingIgnoreCase(title);
    }

    // Filter by event type (workshop, training, competition)
    public List<Event> filterByType(String type) {
        return eventRepository.findByType(type);
    }

    // Upcoming events
    public List<Event> getUpcomingEvents() {
        return eventRepository.findByDateAfter(LocalDate.now());
    }

    // Past events
    public List<Event> getPastEvents() {
        return eventRepository.findByDateBefore(LocalDate.now());
    }

    // Events created by a specific user/admin
    public List<Event> getEventsByCreator(Long userId) {
        return eventRepository.findByCreatedBy_UserId(userId);
    }
}
