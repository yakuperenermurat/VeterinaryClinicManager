package com.yakuperenermurat.veterinaryclinicmanager.dto.request.animal;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class AnimalUpdateRequest {
    @NotNull
    private Long id;
    @NotNull
    private String name;
    @NotNull
    private String species;
    @NotNull
    private String breed;
    @NotNull
    private String gender;
    @NotNull
    private String colour;
    @NotNull
    private LocalDate dateOfBirth;
    @NotNull
    private Long customerId;
}