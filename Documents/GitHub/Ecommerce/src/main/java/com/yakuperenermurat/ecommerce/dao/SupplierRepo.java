package com.yakuperenermurat.ecommerce.dao;

import com.yakuperenermurat.ecommerce.entities.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplierRepo extends JpaRepository<Supplier, Integer> {
}
