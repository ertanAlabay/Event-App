package com.ertanAlabay.etkinlikApp.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ertanAlabay.etkinlikApp.dto.EventDTO;
import com.ertanAlabay.etkinlikApp.mapper.EventMapper;
import com.ertanAlabay.etkinlikApp.model.Event;
import com.ertanAlabay.etkinlikApp.model.User;
import com.ertanAlabay.etkinlikApp.service.EventService;
import com.ertanAlabay.etkinlikApp.service.UserService;

@RestController
@RequestMapping("/api/events")
public class EventController {

    private final EventService eventService;
    private final UserService userService;

    public EventController(EventService eventService, UserService userService) {
        this.eventService = eventService;
        this.userService = userService;
    }

    @PostMapping("/add")
    public ResponseEntity<EventDTO> createEvent(@RequestBody EventDTO eventDTO) {
        // Organizer bilgisi DTO'dan alınır
        User organizer = userService.getUserById(eventDTO.getOrganizerId());

        // DTO'dan Entity'ye dönüşüm yapılır
        Event event = EventMapper.toEntity(eventDTO, organizer);

        // Etkinlik oluşturulur
        Event createdEvent = eventService.createEvent(event);

        // Yanıt olarak DTO döndürülür
        return new ResponseEntity<>(EventMapper.toDTO(createdEvent), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventDTO> getEventById(@PathVariable Long id) {
        Event event = eventService.getEventById(id);
        return new ResponseEntity<>(EventMapper.toDTO(event), HttpStatus.OK);
    }

    @GetMapping
    public List<EventDTO> getAllEvents() {
        List<Event> events = eventService.getAllEvents();
        return events.stream()
                     .map(EventMapper::toDTO)
                     .collect(Collectors.toList());
    }

    @PutMapping("/{id}")
    public ResponseEntity<EventDTO> updateEvent(@PathVariable Long id, @RequestBody EventDTO eventDTO) {
        User organizer = userService.getUserById(eventDTO.getOrganizerId());
        Event event = EventMapper.toEntity(eventDTO, organizer);
        Event updatedEvent = eventService.updateEvent(id, event);
        return new ResponseEntity<>(EventMapper.toDTO(updatedEvent), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvent(@PathVariable Long id) {
        eventService.deleteEvent(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}