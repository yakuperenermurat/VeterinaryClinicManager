package com.yakuperenermurat.veterinaryclinicmanager.dto.response.doctor;


import lombok.Data;

@Data
public class DoctorResponse {
    private Long id;
    private String name;
    private String phone;
    private String mail;
    private String address;
    private String city;
}