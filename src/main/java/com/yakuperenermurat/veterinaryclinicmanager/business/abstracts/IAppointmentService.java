package com.yakuperenermurat.veterinaryclinicmanager.business.abstracts;

import com.yakuperenermurat.veterinaryclinicmanager.dto.request.appointment.AppointmentSaveRequest;
import com.yakuperenermurat.veterinaryclinicmanager.dto.request.appointment.AppointmentUpdateRequest;
import com.yakuperenermurat.veterinaryclinicmanager.dto.response.appointment.AppointmentResponse;

import java.time.LocalDateTime;
import java.util.List;

public interface IAppointmentService {
    AppointmentResponse save(AppointmentSaveRequest appointmentSaveRequest); // Randevu kaydetme
    AppointmentResponse get(Long id); // Randevu bilgilerini getirme
    AppointmentResponse update(AppointmentUpdateRequest appointmentUpdateRequest); // Randevu güncelleme
    boolean delete(Long id); // Randevu silme
    List<AppointmentResponse> getAll(); // Tüm randevuları getirme
    List<AppointmentResponse> getByAnimalId(Long animal); // Hayvan ID'sine göre randevuları getirme
    List<AppointmentResponse> getByDoctorId(Long doctor); // Doktor ID'sine göre randevuları getirme
    List<AppointmentResponse> getByDateRange(LocalDateTime startDate, LocalDateTime endDate); // Tarih aralığına göre randevuları getirme

    List<AppointmentResponse> getByAnimalIdAndDateRange(Long animalId, LocalDateTime startDate, LocalDateTime endDate);

    List<AppointmentResponse> getByDoctorIdAndDateRange(Long doctorId, LocalDateTime startDate, LocalDateTime endDate);
}