package com.yakuperenermurat.veterinaryclinicmanager.dto.response.appointment;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AppointmentResponse {
    private Long id;
    private LocalDateTime appointmentDate;
    private Long animalId;
    private Long doctorId;
    private String message;
}