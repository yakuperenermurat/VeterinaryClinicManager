package com.yakuperenermurat.veterinaryclinicmanager.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "doctors")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "name")
    private String name;

    @NotNull
    @Column(name = "phone")
    private String phone;

    @NotNull
    @Column(name = "mail")
    private String mail;

    @NotNull
    @Column(name = "address")
    private String address;

    @NotNull
    @Column(name = "city")
    private String city;

    @OneToMany(mappedBy = "doctor", cascade = CascadeType.REMOVE)
    private List<Appointment> appointmentList;

}
