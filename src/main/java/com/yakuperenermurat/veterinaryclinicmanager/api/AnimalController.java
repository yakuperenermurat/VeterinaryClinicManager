package com.yakuperenermurat.veterinaryclinicmanager.api;

import com.yakuperenermurat.veterinaryclinicmanager.business.abstracts.IAnimalService;
import com.yakuperenermurat.veterinaryclinicmanager.dto.request.animal.AnimalSaveRequest;
import com.yakuperenermurat.veterinaryclinicmanager.dto.request.animal.AnimalUpdateRequest;
import com.yakuperenermurat.veterinaryclinicmanager.dto.response.animal.AnimalResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/animals") // API endpointlerinin base URL'sini tanımlar
public class AnimalController {

    private final IAnimalService animalService;

    @Autowired
    public AnimalController(IAnimalService animalService) {
        this.animalService = animalService;
    }

    @PostMapping // Yeni hayvan kaydı oluşturur
    public ResponseEntity<AnimalResponse> createAnimal(@RequestBody @Valid AnimalSaveRequest animalSaveRequest) {
        AnimalResponse animalResponse = animalService.save(animalSaveRequest);
        return new ResponseEntity<>(animalResponse, HttpStatus.CREATED); // 201 Created status kodu döner
    }

    @PutMapping // Mevcut hayvan kaydını günceller
    public ResponseEntity<AnimalResponse> updateAnimal(@RequestBody @Valid AnimalUpdateRequest animalUpdateRequest) {
        AnimalResponse animalResponse = animalService.update(animalUpdateRequest);
        return new ResponseEntity<>(animalResponse, HttpStatus.OK); // 200 OK status kodu döner
    }

    @GetMapping("/{id}") // ID ile hayvan bilgilerini getirir
    public ResponseEntity<AnimalResponse> getAnimalById(@PathVariable Long id) {
        AnimalResponse animalResponse = animalService.get(id);
        return new ResponseEntity<>(animalResponse, HttpStatus.OK); // 200 OK status kodu döner
    }

    @GetMapping // Tüm hayvanların listesini getirir
    public ResponseEntity<List<AnimalResponse>> getAllAnimals() {
        List<AnimalResponse> animalResponses = animalService.getAll();
        return new ResponseEntity<>(animalResponses, HttpStatus.OK); // 200 OK status kodu döner
    }

    @DeleteMapping("/{id}") // ID ile hayvan kaydını siler
    public ResponseEntity<String> deleteAnimal(@PathVariable Long id) {
        animalService.delete(id);
        return ResponseEntity.ok("Animal deleted successfully."); // Başarılı silme mesajı döner
    }

    @GetMapping("/customer/{customerId}") // Müşteri ID'sine göre hayvanları getirir
    public ResponseEntity<List<AnimalResponse>> getAnimalsByCustomerId(@PathVariable Long customerId) {
        List<AnimalResponse> animalResponses = animalService.getByCustomerId(customerId);
        return new ResponseEntity<>(animalResponses, HttpStatus.OK); // 200 OK status kodu döner
    }

    @GetMapping("/search") // Hayvan adlarına göre arama yapar
    public ResponseEntity<List<AnimalResponse>> getAnimalsByName(@RequestParam String name) {
        List<AnimalResponse> animalResponses = animalService.getByName(name);
        return new ResponseEntity<>(animalResponses, HttpStatus.OK); // 200 OK status kodu döner
    }
}