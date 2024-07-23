package com.yakuperenermurat.veterinaryclinicmanager.api;

import com.yakuperenermurat.veterinaryclinicmanager.business.abstracts.IVaccineService;
import com.yakuperenermurat.veterinaryclinicmanager.dto.request.vaccine.VaccineSaveRequest;
import com.yakuperenermurat.veterinaryclinicmanager.dto.request.vaccine.VaccineUpdateRequest;
import com.yakuperenermurat.veterinaryclinicmanager.dto.response.vaccine.VaccineResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vaccines")
public class VaccineController {

    private final IVaccineService vaccineService;

    @Autowired
    public VaccineController(IVaccineService vaccineService) {
        this.vaccineService = vaccineService;
    }

    @PostMapping // Yeni aşı oluşturma
    public ResponseEntity<VaccineResponse> createVaccine(@RequestBody @Valid VaccineSaveRequest vaccineSaveRequest) {
        VaccineResponse vaccineResponse = vaccineService.save(vaccineSaveRequest);
        return new ResponseEntity<>(vaccineResponse, HttpStatus.CREATED); // 201 Created status kodu döner
    }

    @PutMapping // Aşı güncelleme
    public ResponseEntity<VaccineResponse> updateVaccine(@RequestBody @Valid VaccineUpdateRequest vaccineUpdateRequest) {
        VaccineResponse vaccineResponse = vaccineService.update(vaccineUpdateRequest);
        return new ResponseEntity<>(vaccineResponse, HttpStatus.OK); // 200 OK status kodu döner
    }

    @GetMapping("/{id}") // ID ile aşı bilgilerini getirir
    public ResponseEntity<VaccineResponse> getVaccineById(@PathVariable Long id) {
        VaccineResponse vaccineResponse = vaccineService.get(id);
        return new ResponseEntity<>(vaccineResponse, HttpStatus.OK); // 200 OK status kodu döner
    }

    @GetMapping // Tüm aşıların listesini getirir
    public ResponseEntity<List<VaccineResponse>> getAllVaccines() {
        List<VaccineResponse> vaccineResponses = vaccineService.getAll();
        return new ResponseEntity<>(vaccineResponses, HttpStatus.OK); // 200 OK status kodu döner
    }

    @DeleteMapping("/{id}") // ID ile aşıyı siler
    public ResponseEntity<String> deleteVaccine(@PathVariable Long id) {
        vaccineService.delete(id);
        return ResponseEntity.ok("Vaccine deleted successfully."); // Başarılı silme mesajı döner
    }

    @GetMapping("/animal/{animalId}") // Hayvan ID'sine göre aşıları getirir
    public ResponseEntity<List<VaccineResponse>> getVaccinesByAnimalId(@PathVariable Long animalId) {
        List<VaccineResponse> vaccineResponses = vaccineService.getByAnimalId(animalId);
        return new ResponseEntity<>(vaccineResponses, HttpStatus.OK); // 200 OK status kodu döner
    }
}