package com.yakuperenermurat.veterinaryclinicmanager.dao;

import com.yakuperenermurat.veterinaryclinicmanager.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ICustomerRepo extends JpaRepository<Customer, Long> {
    // Belirli bir isme sahip müşterileri bul
    List<Customer> findByName(String name);
}