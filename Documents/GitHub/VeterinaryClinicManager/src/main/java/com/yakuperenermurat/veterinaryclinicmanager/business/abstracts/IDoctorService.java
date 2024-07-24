package com.yakuperenermurat.veterinaryclinicmanager.business.abstracts;

import com.yakuperenermurat.veterinaryclinicmanager.dto.request.doctor.DoctorSaveRequest;
import com.yakuperenermurat.veterinaryclinicmanager.dto.request.doctor.DoctorUpdateRequest;
import com.yakuperenermurat.veterinaryclinicmanager.dto.response.doctor.DoctorResponse;

import java.time.LocalDate;
import java.util.List;

public interface IDoctorService {
    DoctorResponse save(DoctorSaveRequest doctorSaveRequest); // Doktor kaydetme
    DoctorResponse get(Long id); // Doktor bilgilerini getirme
    DoctorResponse update(DoctorUpdateRequest doctorUpdateRequest); // Doktor güncelleme
    boolean delete(Long id); // Doktor silme
    List<DoctorResponse> getAll(); // Tüm doktorları getirme
    List<DoctorResponse> getByName(String name); // İsme göre doktorları getirme

    boolean isDoctorAvailable(long doctor, LocalDate date); // Doktorun belirli bir tarihte müsait olup olmadığını kontrol etme
}
