package com.ertanAlabay.etkinlikApp.controller;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ertanAlabay.etkinlikApp.dto.ParticipantDTO;
import com.ertanAlabay.etkinlikApp.mapper.ParticipantMapper;
import com.ertanAlabay.etkinlikApp.model.Event;
import com.ertanAlabay.etkinlikApp.model.Participant;
import com.ertanAlabay.etkinlikApp.model.User;
import com.ertanAlabay.etkinlikApp.service.EventService;
import com.ertanAlabay.etkinlikApp.service.ParticipantService;
import com.ertanAlabay.etkinlikApp.service.UserService;

@RestController
@RequestMapping("/api/participants")
public class ParticipantController {

    private final ParticipantService participantService;
    private final UserService userService;
    private final EventService eventService;

    public ParticipantController(ParticipantService participantService, UserService userService, EventService eventService) {
        this.participantService = participantService;
        this.userService = userService;
        this.eventService = eventService;
    }

    @PostMapping
    public ResponseEntity<ParticipantDTO> addParticipant(@RequestBody ParticipantDTO participantDTO) {
        // User ve Event bilgilerini DTO'dan al
        User user = userService.getUserById(participantDTO.getUserId());
        Event event = eventService.getEventById(participantDTO.getEventId());

        // Participant oluştur ve kaydet
        Participant participant = ParticipantMapper.toEntity(participantDTO, user, event);
        Participant createdParticipant = participantService.addParticipant(participant);

        // DTO olarak döndür
        return new ResponseEntity<>(ParticipantMapper.toDTO(createdParticipant), HttpStatus.CREATED);
    }
    
    @GetMapping("/event/{eventId}")
    public List<ParticipantDTO> getParticipantsByEvent(@PathVariable Long eventId) {
        List<Participant> participants = participantService.getParticipantsByEvent(eventId);
        return participants.stream()
                .map(ParticipantMapper::toDTO)
                .collect(Collectors.toList());
    }
}
