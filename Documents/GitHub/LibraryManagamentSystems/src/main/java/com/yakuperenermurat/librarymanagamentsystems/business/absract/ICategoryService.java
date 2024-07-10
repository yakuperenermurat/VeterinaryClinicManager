package com.yakuperenermurat.librarymanagamentsystems.business.absract;

import com.yakuperenermurat.librarymanagamentsystems.entities.Category;
import org.springframework.data.domain.Page;

public interface ICategoryService {
    Category save(Category category);
    Category get(int id);
    Category update(Category category);
    String delete(int id);
}
