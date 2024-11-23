package com.ertanAlabay.etkinlikApp.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventDTO {

    private Long id; // Otomatik oluşturulan ID
    private String name;
    private String description;
    private LocalDate date;
    private LocalTime time;
    private String location;
    private String category;
    private Long organizerId; // Organizer bilgisi DTO içinde
}
