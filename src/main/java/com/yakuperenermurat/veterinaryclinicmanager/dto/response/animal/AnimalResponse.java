package com.yakuperenermurat.veterinaryclinicmanager.dto.response.animal;

import lombok.Data;
import java.time.LocalDate;

@Data
public class AnimalResponse {
    private Long id;
    private String name;
    private String species;
    private String breed;
    private String gender;
    private String colour;
    private LocalDate dateOfBirth;
    private Long customerId;
}
