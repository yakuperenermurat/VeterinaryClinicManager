package com.yakuperenermurat.veterinaryclinicmanager.dao;

import com.yakuperenermurat.veterinaryclinicmanager.entities.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IDoctorRepo extends JpaRepository<Doctor, Long> {
    // Belirli bir isme sahip doktorları bul
    List<Doctor> findByName(String name);
    boolean existsByMailOrPhone(String mail, String phone);
}