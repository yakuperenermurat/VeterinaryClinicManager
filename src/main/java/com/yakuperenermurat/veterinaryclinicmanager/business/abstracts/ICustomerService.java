package com.yakuperenermurat.veterinaryclinicmanager.business.abstracts;

import com.yakuperenermurat.veterinaryclinicmanager.dto.request.customer.CustomerSaveRequest;
import com.yakuperenermurat.veterinaryclinicmanager.dto.request.customer.CustomerUpdateRequest;
import com.yakuperenermurat.veterinaryclinicmanager.dto.response.customer.CustomerResponse;

import java.util.List;

public interface ICustomerService {
    CustomerResponse save(CustomerSaveRequest customerSaveRequest); // Müşteri kaydetme
    CustomerResponse get(Long id); // Müşteri bilgilerini getirme
    CustomerResponse update(CustomerUpdateRequest customerUpdateRequest); // Müşteri güncelleme
    boolean delete(Long id); // Müşteri silme
    List<CustomerResponse> getAll(); // Tüm müşterileri getirme
    List<CustomerResponse> getByName(String name); // İsme göre müşterileri getirme
}