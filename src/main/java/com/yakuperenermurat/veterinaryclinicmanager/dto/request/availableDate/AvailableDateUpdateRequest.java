package com.yakuperenermurat.veterinaryclinicmanager.dto.request.availableDate;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class AvailableDateUpdateRequest {
    @NotNull
    private Long id;
    @NotNull
    private LocalDate availableDate;
    @NotNull
    private Long doctorId;
}