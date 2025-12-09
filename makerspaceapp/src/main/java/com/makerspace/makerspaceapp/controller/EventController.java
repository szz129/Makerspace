package com.makerspace.makerspaceapp.controller;

import com.makerspace.makerspaceapp.model.Event;
import com.makerspace.makerspaceapp.service.EventService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/events")
@CrossOrigin("*")
public class EventController {

    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    // =========================
    // CREATE EVENT
    // =========================
    @PostMapping
    public Event createEvent(@RequestBody Event event) {
        return eventService.createEvent(event);
    }

    // =========================
    // GET EVENT BY ID
    // =========================
    @GetMapping("/{id}")
    public Event getEvent(@PathVariable Long id) {
        return eventService.getEventById(id);
    }

    // =========================
    // GET ALL EVENTS
    // =========================
    @GetMapping
    public List<Event> getAllEvents() {
        return eventService.getAllEvents();
    }

    // =========================
    // GET EVENTS BY TYPE
    // =========================
    @GetMapping("/type/{type}")
    public List<Event> getByType(@PathVariable String type) {
        return eventService.filterByType(type);
    }

    // =========================
    // GET UPCOMING
    // =========================
    @GetMapping("/upcoming")
    public List<Event> getUpcoming() {
        return eventService.getUpcomingEvents();
    }

    // =========================
    // GET BY MAKERSPACE
    // =========================
    @GetMapping("/makerspace/{id}")
    public List<Event> getByMakerspace(@PathVariable Long id) {
        return eventService.getEventsByMakerspace(id);
    }

    // =========================
    // UPDATE EVENT
    // =========================
    @PutMapping("/{id}")
    public Event updateEvent(@PathVariable Long id, @RequestBody Event event) {
        return eventService.updateEvent(id, event);
    }

    // =========================
    // DELETE EVENT
    // =========================
    @DeleteMapping("/{id}")
    public String deleteEvent(@PathVariable Long id) {
        eventService.deleteEvent(id);
        return "Event deleted successfully.";
    }
}
