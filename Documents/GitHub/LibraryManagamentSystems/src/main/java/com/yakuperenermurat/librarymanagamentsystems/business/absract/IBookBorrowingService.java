package com.yakuperenermurat.librarymanagamentsystems.business.absract;

import com.yakuperenermurat.librarymanagamentsystems.entities.Book;
import com.yakuperenermurat.librarymanagamentsystems.entities.BookBorrowing;
import org.springframework.data.domain.Page;

public interface IBookBorrowingService {
    BookBorrowing save(BookBorrowing bookBorrowing);
    BookBorrowing get(int id);
    BookBorrowing update(BookBorrowing bookBorrowing);
    boolean delete(int id);
}
