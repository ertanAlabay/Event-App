package com.ertanAlabay.etkinlikApp.model;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@SuppressWarnings("serial")
@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ParticipantId implements Serializable {

    private Long userId;
    private Long eventId;

    // Getter, Setter, hashCode ve equals metodları
}
