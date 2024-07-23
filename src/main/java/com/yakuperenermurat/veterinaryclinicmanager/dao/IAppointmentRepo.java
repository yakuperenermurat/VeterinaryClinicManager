package com.yakuperenermurat.veterinaryclinicmanager.dao;

import com.yakuperenermurat.veterinaryclinicmanager.entities.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface IAppointmentRepo extends JpaRepository<Appointment, Long> {
    List<Appointment> findByAnimalId(Long animal);

    List<Appointment> findByDoctorId(Long doctor);

    List<Appointment> findByAppointmentDateBetween(LocalDateTime startDate, LocalDateTime endDate);

    boolean existsByDoctor_IdAndAppointmentDate(Long doctorId, LocalDateTime appointmentDate); // Güncellenmiş sorgu

}
