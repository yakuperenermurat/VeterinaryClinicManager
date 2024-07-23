package com.yakuperenermurat.veterinaryclinicmanager.dao;

import com.yakuperenermurat.veterinaryclinicmanager.entities.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IDoctorRepo extends JpaRepository<Doctor, Long> {
    List<Doctor> findByName(String name);
}
