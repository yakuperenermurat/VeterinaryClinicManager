package com.yakuperenermurat.ecommerce.business.abstracts;

import com.yakuperenermurat.ecommerce.entities.Category;
import com.yakuperenermurat.ecommerce.entities.Supplier;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;


public interface ISupplierService {
    Supplier save(Supplier supplier);
    Supplier get(int id);
    Page<Supplier> cursor (int page , int pageSize);
    Supplier update(Supplier supplier);
    boolean delete(int id);
}
