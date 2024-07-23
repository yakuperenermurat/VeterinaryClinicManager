package com.yakuperenermurat.veterinaryclinicmanager.dao;

import com.yakuperenermurat.veterinaryclinicmanager.entities.Animal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IAnimalRepo extends JpaRepository<Animal, Long> {
    List<Animal> findByCustomerId(Long customerId);
    List<Animal> findByName(String name);
}
