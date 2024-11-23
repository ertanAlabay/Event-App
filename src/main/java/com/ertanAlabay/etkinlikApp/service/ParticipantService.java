package com.ertanAlabay.etkinlikApp.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ertanAlabay.etkinlikApp.model.Participant;

import com.ertanAlabay.etkinlikApp.repository.ParticipantRepository;


@Service
public class ParticipantService {

    private final ParticipantRepository participantRepository;

    public ParticipantService(ParticipantRepository participantRepository) {
        this.participantRepository = participantRepository;
    }

    public Participant addParticipant(Participant participant) {
        return participantRepository.save(participant);
    }

    public List<Participant> getParticipantsByEvent(Long eventId) {
        return participantRepository.findByIdEventId(eventId);
    }
}