package com.yakuperenermurat.librarymanagamentsystems.business.concretes;

import com.yakuperenermurat.librarymanagamentsystems.business.absract.IPublisherService;
import com.yakuperenermurat.librarymanagamentsystems.core.exception.NotFoundException;
import com.yakuperenermurat.librarymanagamentsystems.core.utilies.Msg;
import com.yakuperenermurat.librarymanagamentsystems.dao.PublisherRepo;
import com.yakuperenermurat.librarymanagamentsystems.entities.Publisher;
import org.springframework.stereotype.Service;

@Service
public class PublisherManager implements IPublisherService {
    private final PublisherRepo publisherRepo;

    public PublisherManager(PublisherRepo publisherRepo) {
        this.publisherRepo = publisherRepo;
    }

    @Override
    public Publisher save(Publisher publisher) {
        return this.publisherRepo.save(publisher);
    }

    @Override
    public Publisher get(int id) {
        return this.publisherRepo.findById(id).orElseThrow(()-> new NotFoundException(Msg.NOT_FOUND));
    }

    @Override
    public Publisher update(Publisher publisher) {
        Publisher existingPublisher = this.get(publisher.getPublisherId());
        existingPublisher.setPublisherName(publisher.getPublisherName());
        existingPublisher.setPublisherEstablishmentYear(publisher.getPublisherEstablishmentYear());
        existingPublisher.setAddress(publisher.getAddress());
        existingPublisher.setBooks(publisher.getBooks());
        return this.publisherRepo.save(existingPublisher);
    }

    @Override
    public boolean delete(int id) {
        Publisher publisher = this.get(id);
        this.publisherRepo.delete(publisher);
        return true;
    }
}