package com.yakuperenermurat.veterinaryclinicmanager.dto.request.customer;


import jakarta.validation.constraints.NotNull;
import lombok.Data;


@Data
public class CustomerSaveRequest {
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
