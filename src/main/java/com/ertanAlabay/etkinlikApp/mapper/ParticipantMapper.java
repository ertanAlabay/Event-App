package com.ertanAlabay.etkinlikApp.mapper;

import com.ertanAlabay.etkinlikApp.dto.ParticipantDTO;
import com.ertanAlabay.etkinlikApp.model.Event;
import com.ertanAlabay.etkinlikApp.model.Participant;
import com.ertanAlabay.etkinlikApp.model.ParticipantId;
import com.ertanAlabay.etkinlikApp.model.User;

public class ParticipantMapper {

    public static ParticipantDTO toDTO(Participant participant) {
        ParticipantDTO dto = new ParticipantDTO();
        dto.setUserId(participant.getUser().getId());
        dto.setEventId(participant.getEvent().getId());
        return dto;
    }

    public static Participant toEntity(ParticipantDTO dto, User user, Event event) {
        Participant participant = new Participant();
        ParticipantId id = new ParticipantId();
        id.setUserId(dto.getUserId());
        id.setEventId(dto.getEventId());
        participant.setId(id);
        participant.setUser(user);
        participant.setEvent(event);
        return participant;
    }
}