package com.yakuperenermurat.veterinaryclinicmanager.api;

import com.yakuperenermurat.veterinaryclinicmanager.business.abstracts.IAppointmentService;
import com.yakuperenermurat.veterinaryclinicmanager.dto.request.appointment.AppointmentSaveRequest;
import com.yakuperenermurat.veterinaryclinicmanager.dto.request.appointment.AppointmentUpdateRequest;
import com.yakuperenermurat.veterinaryclinicmanager.dto.response.appointment.AppointmentResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/appointments") // API endpointlerinin base URL'sini tanımlar
public class AppointmentController {

    private final IAppointmentService appointmentService;

    @Autowired
    public AppointmentController(IAppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @PostMapping // Yeni randevu oluşturur
    public AppointmentResponse createAppointment(@RequestBody @Valid AppointmentSaveRequest request) {
        return appointmentService.save(request);
    }

    @PutMapping // Mevcut randevuyu günceller
    public ResponseEntity<AppointmentResponse> updateAppointment(@RequestBody @Valid AppointmentUpdateRequest appointmentUpdateRequest) {
        AppointmentResponse appointmentResponse = appointmentService.update(appointmentUpdateRequest);
        return new ResponseEntity<>(appointmentResponse, HttpStatus.OK); // 200 OK status kodu döner
    }

    @GetMapping("/{id}") // ID ile randevu bilgilerini getirir
    public ResponseEntity<AppointmentResponse> getAppointmentById(@PathVariable Long id) {
        AppointmentResponse appointmentResponse = appointmentService.get(id);
        return new ResponseEntity<>(appointmentResponse, HttpStatus.OK); // 200 OK status kodu döner
    }

    @GetMapping // Tüm randevuların listesini getirir
    public ResponseEntity<List<AppointmentResponse>> getAllAppointments() {
        List<AppointmentResponse> appointmentResponses = appointmentService.getAll();
        return new ResponseEntity<>(appointmentResponses, HttpStatus.OK); // 200 OK status kodu döner
    }

    @DeleteMapping("/{id}") // ID ile randevuyu siler
    public ResponseEntity<String> deleteAppointment(@PathVariable Long id) {
        appointmentService.delete(id);
        return ResponseEntity.ok("Appointment deleted successfully."); // Başarılı silme mesajı döner
    }

    @GetMapping("/animal/{animalId}") // Hayvan ID'sine göre randevuları getirir
    public ResponseEntity<List<AppointmentResponse>> getAppointmentsByAnimalId(@PathVariable Long animalId) {
        List<AppointmentResponse> appointmentResponses = appointmentService.getByAnimalId(animalId);
        return new ResponseEntity<>(appointmentResponses, HttpStatus.OK); // 200 OK status kodu döner
    }

    @GetMapping("/doctor/{doctorId}") // Doktor ID'sine göre randevuları getirir
    public ResponseEntity<List<AppointmentResponse>> getAppointmentsByDoctorId(@PathVariable Long doctorId) {
        List<AppointmentResponse> appointmentResponses = appointmentService.getByDoctorId(doctorId);
        return new ResponseEntity<>(appointmentResponses, HttpStatus.OK); // 200 OK status kodu döner
    }

    @GetMapping("/date-range") // Tarih aralığına göre randevuları getirir
    public ResponseEntity<List<AppointmentResponse>> getAppointmentsByDateRange(
            @RequestParam LocalDateTime startDate, @RequestParam LocalDateTime endDate) {
        List<AppointmentResponse> appointmentResponses = appointmentService.getByDateRange(startDate, endDate);
        return new ResponseEntity<>(appointmentResponses, HttpStatus.OK); // 200 OK status kodu döner
    }
    @GetMapping("/filter-by-animal-and-date")
    public ResponseEntity<List<AppointmentResponse>> getAppointmentsByAnimalIdAndDateRange(
            @RequestParam Long animalId,
            @RequestParam LocalDateTime startDate,
            @RequestParam LocalDateTime endDate) {
        List<AppointmentResponse> responses = appointmentService.getByAnimalIdAndDateRange(animalId, startDate, endDate);
        return new ResponseEntity<>(responses, HttpStatus.OK);
    }
    @GetMapping("/filter-by-doctor-and-date")
    public ResponseEntity<List<AppointmentResponse>> getAppointmentsByDoctorIdAndDateRange(
            @RequestParam Long doctorId,
            @RequestParam LocalDateTime startDate,
            @RequestParam LocalDateTime endDate) {
        List<AppointmentResponse> responses = appointmentService.getByDoctorIdAndDateRange(doctorId, startDate, endDate);
        return new ResponseEntity<>(responses, HttpStatus.OK);
    }
}