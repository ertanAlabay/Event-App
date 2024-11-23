package com.ertanAlabay.etkinlikApp.mapper;

import com.ertanAlabay.etkinlikApp.dto.EventDTO;
import com.ertanAlabay.etkinlikApp.model.Event;
import com.ertanAlabay.etkinlikApp.model.User;

public class EventMapper {

    public static EventDTO toDTO(Event event) {
        EventDTO dto = new EventDTO();
        dto.setId(event.getId());
        dto.setName(event.getName());
        dto.setDescription(event.getDescription());
        dto.setDate(event.getDate());
        dto.setTime(event.getTime());
        dto.setLocation(event.getLocation());
        dto.setCategory(event.getCategory());
        dto.setOrganizerId(event.getOrganizer().getId());
        return dto;
    }

    public static Event toEntity(EventDTO dto, User organizer) {
        Event event = new Event();
        event.setName(dto.getName());
        event.setDescription(dto.getDescription());
        event.setDate(dto.getDate());
        event.setTime(dto.getTime());
        event.setLocation(dto.getLocation());
        event.setCategory(dto.getCategory());
        event.setOrganizer(organizer);
        return event;
    }
}
