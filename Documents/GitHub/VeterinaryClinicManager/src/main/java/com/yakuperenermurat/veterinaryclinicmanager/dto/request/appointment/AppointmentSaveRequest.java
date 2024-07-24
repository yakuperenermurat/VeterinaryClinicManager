package com.yakuperenermurat.veterinaryclinicmanager.dto.request.appointment;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AppointmentSaveRequest {

    @NotNull
    private LocalDateTime appointmentDate;

    @NotNull
    private Long animalId;

    @NotNull
    private Long doctorId;
}
