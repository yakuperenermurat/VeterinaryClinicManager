package com.yakuperenermurat.veterinaryclinicmanager.dao;

import com.yakuperenermurat.veterinaryclinicmanager.entities.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface IAppointmentRepo extends JpaRepository<Appointment, Long> {
    // Belirli bir hayvan ID'sine sahip randevuları bul
    List<Appointment> findByAnimalId(Long animal);

    // Belirli bir doktor ID'sine sahip randevuları bul
    List<Appointment> findByDoctorId(Long doctor);

    // Belirtilen tarih aralığındaki randevuları bul
    List<Appointment> findByAppointmentDateBetween(LocalDateTime startDate, LocalDateTime endDate);

    // Doktor ID'sine ve randevu tarihine göre çakışma olup olmadığını kontrol et
    boolean existsByDoctor_IdAndAppointmentDate(Long doctorId, LocalDateTime appointmentDate);
}