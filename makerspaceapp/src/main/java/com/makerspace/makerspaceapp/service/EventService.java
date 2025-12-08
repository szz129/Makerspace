package com.makerspace.makerspaceapp.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.makerspace.makerspaceapp.model.Event;
import com.makerspace.makerspaceapp.repository.EventRepository;

@Service
public class EventService {

    private final EventRepository eventRepository;

    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    // =========================
    // CREATE EVENT (SAFE)
    // =========================
    @Transactional
    public Event createEvent(Event event) {
        return eventRepository.save(event);
    }

    // =========================
    // GET EVENT BY ID
    // =========================
    @Transactional(readOnly = true)
    public Event getEventById(Long id) {
        return eventRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Event not found with ID: " + id));
    }

    // =========================
    // GET ALL EVENTS
    // =========================
    @Transactional(readOnly = true)
    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    // =========================
    // UPDATE EVENT (SAFE)
    // =========================
    @Transactional
    public Event updateEvent(Long id, Event updatedEvent) {
        Event existing = getEventById(id);

        existing.setTitle(updatedEvent.getTitle());
        existing.setDescription(updatedEvent.getDescription());
        existing.setType(updatedEvent.getType());
        existing.setEventDate(updatedEvent.getEventDate());
        existing.setLocation(updatedEvent.getLocation());
        existing.setCapacity(updatedEvent.getCapacity());
        existing.setMakerspace(updatedEvent.getMakerspace());
        existing.setCreatedBy(updatedEvent.getCreatedBy());

        return eventRepository.save(existing);
    }

    // =========================
    // DELETE EVENT (SAFE)
    // =========================
    @Transactional
    public void deleteEvent(Long id) {
        eventRepository.deleteById(id);
    }

    // =========================
    // FILTER METHODS (READ ONLY)
    // =========================

    @Transactional(readOnly = true)
    public List<Event> getEventsByMakerspace(Long makerspaceId) {
        return eventRepository.findByMakerspace_MakerspaceId(makerspaceId);
    }

    @Transactional(readOnly = true)
    public List<Event> searchEventsByTitle(String title) {
        return eventRepository.findByTitleContainingIgnoreCase(title);
    }

    @Transactional(readOnly = true)
    public List<Event> filterByType(String type) {
        return eventRepository.findByType(type);
    }

    @Transactional(readOnly = true)
    public List<Event> getUpcomingEvents() {
        return eventRepository.findByEventDateAfter(LocalDate.now());
    }

    @Transactional(readOnly = true)
    public List<Event> getPastEvents() {
        return eventRepository.findByEventDateBefore(LocalDate.now());
    }

    @Transactional(readOnly = true)
    public List<Event> getEventsByCreator(Long userId) {
        return eventRepository.findByCreatedBy_UserId(userId);
    }
}
