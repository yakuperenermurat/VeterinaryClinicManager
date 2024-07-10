package com.yakuperenermurat.librarymanagamentsystems.dao;

import com.yakuperenermurat.librarymanagamentsystems.entities.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PublisherRepo extends JpaRepository<Publisher, Integer> {
}
