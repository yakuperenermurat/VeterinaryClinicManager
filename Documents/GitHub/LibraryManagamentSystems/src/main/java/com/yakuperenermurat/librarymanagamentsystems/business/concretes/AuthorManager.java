package com.yakuperenermurat.librarymanagamentsystems.business.concretes;

import com.yakuperenermurat.librarymanagamentsystems.business.absract.IAuthorService;
import com.yakuperenermurat.librarymanagamentsystems.core.exception.NotFoundException;
import com.yakuperenermurat.librarymanagamentsystems.core.utilies.Msg;
import com.yakuperenermurat.librarymanagamentsystems.dao.AuthorRepo;
import com.yakuperenermurat.librarymanagamentsystems.entities.Author;
import org.springframework.stereotype.Service;

@Service
public class AuthorManager implements IAuthorService {

    private final AuthorRepo authorRepo;

    public AuthorManager(AuthorRepo authorRepo) {
        this.authorRepo = authorRepo;
    }

    @Override
    public Author save(Author author) {
        return this.authorRepo.save(author);
    }

    @Override
    public Author get(int id) {
        return this.authorRepo.findById(id).orElseThrow(() -> new NotFoundException(Msg.NOT_FOUND));
    }

    @Override
    public Author update(Author author) {
        return this.authorRepo.save(author);
    }

    @Override
    public boolean delete(int id) {
        Author author = this.get(id);
        this.authorRepo.delete(author);
        return true;
    }
}
