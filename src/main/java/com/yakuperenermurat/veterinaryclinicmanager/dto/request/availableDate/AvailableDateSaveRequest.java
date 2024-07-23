package com.yakuperenermurat.veterinaryclinicmanager.dto.request.availableDate;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class AvailableDateSaveRequest {
    @NotNull
    private LocalDate availableDate;
    @NotNull
    private Long doctorId;
}
