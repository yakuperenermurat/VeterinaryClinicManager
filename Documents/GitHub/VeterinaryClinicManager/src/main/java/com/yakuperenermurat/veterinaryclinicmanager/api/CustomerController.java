package com.yakuperenermurat.veterinaryclinicmanager.api;

import com.yakuperenermurat.veterinaryclinicmanager.business.abstracts.ICustomerService;
import com.yakuperenermurat.veterinaryclinicmanager.dto.request.customer.CustomerSaveRequest;
import com.yakuperenermurat.veterinaryclinicmanager.dto.request.customer.CustomerUpdateRequest;
import com.yakuperenermurat.veterinaryclinicmanager.dto.response.customer.CustomerResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private final ICustomerService customerService;

    @Autowired
    public CustomerController(ICustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping // Yeni müşteri oluşturma
    public ResponseEntity<CustomerResponse> createCustomer(@RequestBody @Valid CustomerSaveRequest customerSaveRequest) {
        CustomerResponse customerResponse = customerService.save(customerSaveRequest);
        return new ResponseEntity<>(customerResponse, HttpStatus.CREATED); // 201 Created status kodu döner
    }

    @PutMapping // Müşteri güncelleme
    public ResponseEntity<CustomerResponse> updateCustomer(@RequestBody @Valid CustomerUpdateRequest customerUpdateRequest) {
        CustomerResponse customerResponse = customerService.update(customerUpdateRequest);
        return new ResponseEntity<>(customerResponse, HttpStatus.OK); // 200 OK status kodu döner
    }

    @GetMapping("/{id}") // ID ile müşteri bilgilerini getirir
    public ResponseEntity<CustomerResponse> getCustomerById(@PathVariable Long id) {
        CustomerResponse customerResponse = customerService.get(id);
        return new ResponseEntity<>(customerResponse, HttpStatus.OK); // 200 OK status kodu döner
    }

    @GetMapping // Tüm müşterilerin listesini getirir
    public ResponseEntity<List<CustomerResponse>> getAllCustomers() {
        List<CustomerResponse> customerResponses = customerService.getAll();
        return new ResponseEntity<>(customerResponses, HttpStatus.OK); // 200 OK status kodu döner
    }

    @DeleteMapping("/{id}") // ID ile müşteriyi siler
    public ResponseEntity<String> deleteCustomer(@PathVariable Long id) {
        customerService.delete(id);
        return ResponseEntity.ok("Customer deleted successfully."); // Başarılı silme mesajı döner
    }

    @GetMapping("/search") // İsme göre müşteri arar
    public ResponseEntity<List<CustomerResponse>> getCustomersByName(@RequestParam String name) {
        List<CustomerResponse> customerResponses = customerService.getByName(name);
        return new ResponseEntity<>(customerResponses, HttpStatus.OK); // 200 OK status kodu döner
    }
}