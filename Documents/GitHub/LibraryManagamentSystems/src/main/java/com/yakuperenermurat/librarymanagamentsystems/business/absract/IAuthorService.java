package com.yakuperenermurat.librarymanagamentsystems.business.absract;

import com.yakuperenermurat.librarymanagamentsystems.entities.Author;
import org.springframework.data.domain.Page;

public interface IAuthorService {
    Author save(Author author);
    Author get(int id);
    Author update(Author author);
    boolean delete(int id);
}
