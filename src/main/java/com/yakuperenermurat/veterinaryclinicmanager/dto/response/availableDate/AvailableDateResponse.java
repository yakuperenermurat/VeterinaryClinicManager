package com.yakuperenermurat.veterinaryclinicmanager.dto.response.availableDate;

import lombok.Data;

import java.time.LocalDate;

@Data
public class AvailableDateResponse {
    private Long id;
    private LocalDate availableDate;
    private Long doctorId;
}