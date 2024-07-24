package com.yakuperenermurat.veterinaryclinicmanager.api;

import com.yakuperenermurat.veterinaryclinicmanager.business.abstracts.IDoctorService;
import com.yakuperenermurat.veterinaryclinicmanager.dto.request.doctor.DoctorSaveRequest;
import com.yakuperenermurat.veterinaryclinicmanager.dto.request.doctor.DoctorUpdateRequest;
import com.yakuperenermurat.veterinaryclinicmanager.dto.response.doctor.DoctorResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/doctors")
public class DoctorController {

    private final IDoctorService doctorService;

    @Autowired
    public DoctorController(IDoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @PostMapping // Yeni doktor oluşturma
    public ResponseEntity<DoctorResponse> createDoctor(@RequestBody @Valid DoctorSaveRequest doctorSaveRequest) {
        DoctorResponse doctorResponse = doctorService.save(doctorSaveRequest);
        return new ResponseEntity<>(doctorResponse, HttpStatus.CREATED); // 201 Created status kodu döner
    }

    @PutMapping // Doktor güncelleme
    public ResponseEntity<DoctorResponse> updateDoctor(@RequestBody @Valid DoctorUpdateRequest doctorUpdateRequest) {
        DoctorResponse doctorResponse = doctorService.update(doctorUpdateRequest);
        return new ResponseEntity<>(doctorResponse, HttpStatus.OK); // 200 OK status kodu döner
    }

    @GetMapping("/{id}") // ID ile doktor bilgilerini getirir
    public ResponseEntity<DoctorResponse> getDoctorById(@PathVariable Long id) {
        DoctorResponse doctorResponse = doctorService.get(id);
        return new ResponseEntity<>(doctorResponse, HttpStatus.OK); // 200 OK status kodu döner
    }

    @GetMapping // Tüm doktorların listesini getirir
    public ResponseEntity<List<DoctorResponse>> getAllDoctors() {
        List<DoctorResponse> doctorResponses = doctorService.getAll();
        return new ResponseEntity<>(doctorResponses, HttpStatus.OK); // 200 OK status kodu döner
    }

    @DeleteMapping("/{id}") // ID ile doktoru siler
    public ResponseEntity<String> deleteDoctor(@PathVariable Long id) {
        doctorService.delete(id);
        return ResponseEntity.ok("Doctor deleted successfully."); // Başarılı silme mesajı döner
    }

    @GetMapping("/search") // İsme göre doktor arar
    public ResponseEntity<List<DoctorResponse>> getDoctorsByName(@RequestParam String name) {
        List<DoctorResponse> doctorResponses = doctorService.getByName(name);
        return new ResponseEntity<>(doctorResponses, HttpStatus.OK); // 200 OK status kodu döner
    }
}