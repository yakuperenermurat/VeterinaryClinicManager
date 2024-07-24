package com.yakuperenermurat.veterinaryclinicmanager.dto.request.vaccine;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.time.LocalDate;

@Data
public class VaccineSaveRequest {
    @NotNull
    private String name;
    @NotNull
    private String code;
    @NotNull
    private LocalDate protectionStartDate;
    @NotNull
    private LocalDate protectionFinishDate;
    @NotNull
    private Long animalId;
}