package com.yakuperenermurat.veterinaryclinicmanager.dao;

import com.yakuperenermurat.veterinaryclinicmanager.entities.AvailableDate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IAvailableDateRepo extends JpaRepository<AvailableDate, Long> {
    List<AvailableDate> findByDoctorId(Long doctorId);
}
