package com.yakuperenermurat.veterinaryclinicmanager.dao;

import com.yakuperenermurat.veterinaryclinicmanager.entities.AvailableDate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface IAvailableDateRepo extends JpaRepository<AvailableDate, Long> {
    // Belirli bir doktor ID'sine sahip müsait tarihleri bul
    List<AvailableDate> findByDoctorId(Long doctorId);
    boolean existsByDoctorIdAndAvailableDate(Long doctorId, LocalDate availableDate);
}