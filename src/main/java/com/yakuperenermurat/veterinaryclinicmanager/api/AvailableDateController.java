package com.yakuperenermurat.veterinaryclinicmanager.api;

import com.yakuperenermurat.veterinaryclinicmanager.business.abstracts.IAvailableDateService;
import com.yakuperenermurat.veterinaryclinicmanager.dto.response.availableDate.AvailableDateResponse;
import com.yakuperenermurat.veterinaryclinicmanager.dto.request.availableDate.AvailableDateSaveRequest;
import com.yakuperenermurat.veterinaryclinicmanager.dto.request.availableDate.AvailableDateUpdateRequest;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/availabledates")
public class AvailableDateController {

    private final IAvailableDateService availableDateService;

    @Autowired
    public AvailableDateController(IAvailableDateService availableDateService) {
        this.availableDateService = availableDateService;
    }

    @PostMapping // Yeni müsait tarih oluşturma
    public ResponseEntity<AvailableDateResponse> createAvailableDate(@RequestBody @Valid AvailableDateSaveRequest availableDateSaveRequest) {
        AvailableDateResponse availableDateResponse = availableDateService.save(availableDateSaveRequest);
        return new ResponseEntity<>(availableDateResponse, HttpStatus.CREATED); // 201 Created status kodu döner
    }

    @PutMapping // Müsait tarihi güncelleme
    public ResponseEntity<AvailableDateResponse> updateAvailableDate(@RequestBody @Valid AvailableDateUpdateRequest availableDateUpdateRequest) {
        AvailableDateResponse availableDateResponse = availableDateService.update(availableDateUpdateRequest);
        return new ResponseEntity<>(availableDateResponse, HttpStatus.OK); // 200 OK status kodu döner
    }

    @GetMapping("/{id}") // ID ile müsait tarihi getirir
    public ResponseEntity<AvailableDateResponse> getAvailableDateById(@PathVariable Long id) {
        AvailableDateResponse availableDateResponse = availableDateService.get(id);
        return new ResponseEntity<>(availableDateResponse, HttpStatus.OK); // 200 OK status kodu döner
    }

    @GetMapping // Tüm müsait tarihlerin listesini getirir
    public ResponseEntity<List<AvailableDateResponse>> getAllAvailableDates() {
        List<AvailableDateResponse> availableDateResponses = availableDateService.getAll();
        return new ResponseEntity<>(availableDateResponses, HttpStatus.OK); // 200 OK status kodu döner
    }

    @DeleteMapping("/{id}") // ID ile müsait tarihi siler
    public ResponseEntity<String> deleteAvailableDate(@PathVariable Long id) {
        availableDateService.delete(id);
        return ResponseEntity.ok("Available date deleted successfully."); // Başarılı silme mesajı döner
    }

    @GetMapping("/doctor/{doctorId}") // Doktor ID'sine göre müsait tarihleri getirir
    public ResponseEntity<List<AvailableDateResponse>> getAvailableDatesByDoctorId(@PathVariable Long doctorId) {
        List<AvailableDateResponse> availableDateResponses = availableDateService.getByDoctorId(doctorId);
        return new ResponseEntity<>(availableDateResponses, HttpStatus.OK); // 200 OK status kodu döner
    }
}