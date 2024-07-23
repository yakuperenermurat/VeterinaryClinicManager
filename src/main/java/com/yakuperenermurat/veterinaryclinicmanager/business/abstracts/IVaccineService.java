package com.yakuperenermurat.veterinaryclinicmanager.business.abstracts;

import com.yakuperenermurat.veterinaryclinicmanager.dto.request.vaccine.VaccineSaveRequest;
import com.yakuperenermurat.veterinaryclinicmanager.dto.request.vaccine.VaccineUpdateRequest;
import com.yakuperenermurat.veterinaryclinicmanager.dto.response.vaccine.VaccineResponse;

import java.util.List;

public interface IVaccineService {
    VaccineResponse save(VaccineSaveRequest vaccineSaveRequest); // Aşı kaydetme
    VaccineResponse get(Long id); // Aşı bilgilerini getirme
    VaccineResponse update(VaccineUpdateRequest vaccineUpdateRequest); // Aşı güncelleme
    boolean delete(Long id); // Aşı silme
    List<VaccineResponse> getAll(); // Tüm aşıları getirme
    List<VaccineResponse> getByAnimalId(Long animalId); // Hayvan ID'sine göre aşıları getirme
}