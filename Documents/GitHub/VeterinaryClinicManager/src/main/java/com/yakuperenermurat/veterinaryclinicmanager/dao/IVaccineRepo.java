package com.yakuperenermurat.veterinaryclinicmanager.dao;

import com.yakuperenermurat.veterinaryclinicmanager.entities.Vaccine;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface IVaccineRepo extends JpaRepository<Vaccine, Long> {
    // Belirli bir hayvan ID'sine sahip aşıları bul
    List<Vaccine> findByAnimalId(Long animalId);
    List<Vaccine> findByProtectionFinishDateBetween(LocalDate startDate, LocalDate endDate);
    boolean existsByAnimal_IdAndCode(Long animalId, String code);
}