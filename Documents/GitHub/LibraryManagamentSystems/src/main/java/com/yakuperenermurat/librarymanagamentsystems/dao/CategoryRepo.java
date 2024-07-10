package com.yakuperenermurat.librarymanagamentsystems.dao;

import com.yakuperenermurat.librarymanagamentsystems.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepo extends JpaRepository<Category, Integer> {
}
