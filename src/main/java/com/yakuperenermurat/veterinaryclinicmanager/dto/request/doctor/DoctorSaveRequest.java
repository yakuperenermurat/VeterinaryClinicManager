package com.yakuperenermurat.veterinaryclinicmanager.dto.request.doctor;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class DoctorSaveRequest {
    @NotNull
    private String name;
    @NotNull
    private String phone;
    @NotNull
    private String mail;
    @NotNull
    private String address;
    @NotNull
    private String city;
}