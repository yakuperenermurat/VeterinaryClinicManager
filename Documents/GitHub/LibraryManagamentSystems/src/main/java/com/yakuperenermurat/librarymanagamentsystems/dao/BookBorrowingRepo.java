package com.yakuperenermurat.librarymanagamentsystems.dao;

import com.yakuperenermurat.librarymanagamentsystems.entities.BookBorrowing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookBorrowingRepo extends JpaRepository<BookBorrowing, Integer> {
}
