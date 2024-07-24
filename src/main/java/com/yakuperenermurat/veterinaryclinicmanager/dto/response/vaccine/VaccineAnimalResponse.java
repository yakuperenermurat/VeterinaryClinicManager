package com.yakuperenermurat.veterinaryclinicmanager.dto.response.vaccine;

import lombok.Data;

import java.time.LocalDate;
@Data
public class VaccineAnimalResponse {
    private Long id;
    private String name;
    private String code;
    private LocalDate protectionStartDate;
    private LocalDate protectionFinishDate;
    private Long animalId;
    private String animalName;
    private String species;
    private String breed;
    private String gender;
    private String colour;
    private LocalDate dateOfBirth;
}
