package com.yakuperenermurat.veterinaryclinicmanager.business.concretes;

import com.yakuperenermurat.veterinaryclinicmanager.business.abstracts.ICustomerService;
import com.yakuperenermurat.veterinaryclinicmanager.core.exception.NotFoundException;
import com.yakuperenermurat.veterinaryclinicmanager.core.utilies.Msg;
import com.yakuperenermurat.veterinaryclinicmanager.dao.ICustomerRepo;
import com.yakuperenermurat.veterinaryclinicmanager.dto.request.customer.CustomerSaveRequest;
import com.yakuperenermurat.veterinaryclinicmanager.dto.request.customer.CustomerUpdateRequest;
import com.yakuperenermurat.veterinaryclinicmanager.dto.response.customer.CustomerResponse;
import com.yakuperenermurat.veterinaryclinicmanager.entities.Customer;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerManager implements ICustomerService {

    private final ICustomerRepo customerRepository;
    private final ModelMapper modelMapper;

    public CustomerManager(ICustomerRepo customerRepository, ModelMapper modelMapper) {
        this.customerRepository = customerRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public CustomerResponse save(CustomerSaveRequest customerSaveRequest) {
        // Yeni bir Customer nesnesi oluştur ve kaydet
        Customer customer = modelMapper.map(customerSaveRequest, Customer.class);
        Customer savedCustomer = customerRepository.save(customer);
        return modelMapper.map(savedCustomer, CustomerResponse.class);
    }

    @Override
    public CustomerResponse get(Long id) {
        // Belirtilen ID'ye sahip müşteriyi getir
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(Msg.NOT_FOUND));
        return modelMapper.map(customer, CustomerResponse.class);
    }

    @Override
    public CustomerResponse update(CustomerUpdateRequest customerUpdateRequest) {
        // Belirtilen ID'ye sahip müşteriyi güncelle
        Customer customer = customerRepository.findById(customerUpdateRequest.getId())
                .orElseThrow(() -> new NotFoundException(Msg.NOT_FOUND));
        modelMapper.map(customerUpdateRequest, customer);
        Customer updatedCustomer = customerRepository.save(customer);
        return modelMapper.map(updatedCustomer, CustomerResponse.class);
    }

    @Override
    public boolean delete(Long id) {
        // Belirtilen ID'ye sahip müşteriyi sil
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(Msg.NOT_FOUND));
        customerRepository.delete(customer);
        return true;
    }

    @Override
    public List<CustomerResponse> getAll() {
        // Tüm müşterileri getir ve yanıt olarak döndür
        return customerRepository.findAll().stream()
                .map(customer -> modelMapper.map(customer, CustomerResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<CustomerResponse> getByName(String name) {
        // Belirtilen isme sahip müşterileri getir
        List<Customer> customers = customerRepository.findByName(name);
        return customers.stream()
                .map(customer -> modelMapper.map(customer, CustomerResponse.class))
                .collect(Collectors.toList());
    }
}