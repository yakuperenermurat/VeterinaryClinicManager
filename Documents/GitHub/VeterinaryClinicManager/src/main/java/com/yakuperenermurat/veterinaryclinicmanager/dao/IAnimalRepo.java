package com.yakuperenermurat.veterinaryclinicmanager.dao;

import com.yakuperenermurat.veterinaryclinicmanager.entities.Animal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IAnimalRepo extends JpaRepository<Animal, Long> {
    // Belirli bir müşteri ID'sine sahip hayvanları bul
    List<Animal> findByCustomerId(Long customerId);

    // Belirli bir isme sahip hayvanları bul
    List<Animal> findByName(String name);
}