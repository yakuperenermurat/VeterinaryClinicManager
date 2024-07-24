package com.yakuperenermurat.veterinaryclinicmanager.dto.request.appointment;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AppointmentUpdateRequest {

    private Long id;
    private LocalDateTime appointmentDate;
    private Long animalId;
    private Long doctorId;
}