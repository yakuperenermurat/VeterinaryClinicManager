package com.yakuperenermurat.librarymanagamentsystems.business.absract;

import com.yakuperenermurat.librarymanagamentsystems.entities.Publisher;
import org.springframework.data.domain.Page;

public interface IPublisherService {
    Publisher save(Publisher publisher);
    Publisher get(int id);
    Publisher update(Publisher publisher);
    boolean delete(int id);
}
