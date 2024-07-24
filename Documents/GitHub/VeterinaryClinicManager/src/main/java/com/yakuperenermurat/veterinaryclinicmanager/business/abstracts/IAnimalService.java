package com.yakuperenermurat.veterinaryclinicmanager.business.abstracts;

import com.yakuperenermurat.veterinaryclinicmanager.dto.request.animal.AnimalSaveRequest;
import com.yakuperenermurat.veterinaryclinicmanager.dto.request.animal.AnimalUpdateRequest;
import com.yakuperenermurat.veterinaryclinicmanager.dto.response.animal.AnimalResponse;

import java.util.List;

public interface IAnimalService {
    AnimalResponse save(AnimalSaveRequest animalSaveRequest); // Hayvan kaydetme
    AnimalResponse get(Long id); // Hayvan bilgilerini getirme
    AnimalResponse update(AnimalUpdateRequest animalUpdateRequest); // Hayvan güncelleme
    boolean delete(Long id); // Hayvan silme
    List<AnimalResponse> getAll(); // Tüm hayvanları getirme
    List<AnimalResponse> getByCustomerId(Long customerId); // Müşteri ID'sine göre hayvanları getirme
    List<AnimalResponse> getByName(String name); // İsme göre hayvanları getirme
}