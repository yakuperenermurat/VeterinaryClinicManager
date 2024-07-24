package com.yakuperenermurat.veterinaryclinicmanager.business.abstracts;

import com.yakuperenermurat.veterinaryclinicmanager.dto.request.availableDate.AvailableDateSaveRequest;
import com.yakuperenermurat.veterinaryclinicmanager.dto.response.availableDate.AvailableDateResponse;
import com.yakuperenermurat.veterinaryclinicmanager.dto.request.availableDate.AvailableDateUpdateRequest;
import java.util.List;

public interface IAvailableDateService {
    AvailableDateResponse save(AvailableDateSaveRequest availableDateSaveRequest); // Müsait tarih kaydetme
    AvailableDateResponse get(Long id); // Müsait tarih bilgilerini getirme
    AvailableDateResponse update(AvailableDateUpdateRequest availableDateUpdateRequest); // Müsait tarih güncelleme
    boolean delete(Long id); // Müsait tarih silme
    List<AvailableDateResponse> getAll(); // Tüm müsait tarihleri getirme
    List<AvailableDateResponse> getByDoctorId(Long doctorId); // Doktor ID'sine göre müsait tarihleri getirme
}