package com.ertanAlabay.etkinlikApp.service;


import org.springframework.stereotype.Service;

import com.ertanAlabay.etkinlikApp.exception.ResourceNotFoundException;
import com.ertanAlabay.etkinlikApp.model.Event;
import com.ertanAlabay.etkinlikApp.repository.EventRepository;


import java.util.List;


@Service
public class EventService {

    private final EventRepository eventRepository;

    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public Event createEvent(Event event) {
        return eventRepository.save(event);
    }

    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    public Event getEventById(Long id) {
        return eventRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Event not found with id: " + id));
    }

    public Event updateEvent(Long id, Event updatedEvent) {
        Event event = getEventById(id);
        event.setName(updatedEvent.getName());
        event.setDescription(updatedEvent.getDescription());
        event.setDate(updatedEvent.getDate());
        event.setTime(updatedEvent.getTime());
        event.setLocation(updatedEvent.getLocation());
        event.setCategory(updatedEvent.getCategory());
        event.setOrganizer(updatedEvent.getOrganizer());
        return eventRepository.save(event);
    }

    public void deleteEvent(Long id) {
        Event event = getEventById(id);
        eventRepository.delete(event);
    }
}