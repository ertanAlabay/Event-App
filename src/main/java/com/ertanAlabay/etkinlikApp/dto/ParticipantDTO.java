package com.ertanAlabay.etkinlikApp.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ParticipantDTO {

    private Long userId;
    private Long eventId;

    // Getter ve Setter metodları
}
