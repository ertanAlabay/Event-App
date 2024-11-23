package com.ertanAlabay.etkinlikApp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ertanAlabay.etkinlikApp.model.Participant;
import com.ertanAlabay.etkinlikApp.model.ParticipantId;

@Repository
public interface ParticipantRepository extends JpaRepository<Participant, ParticipantId> {
    List<Participant> findByIdEventId(Long eventId);
}