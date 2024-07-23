package com.yakuperenermurat.veterinaryclinicmanager.business.concretes;

import com.yakuperenermurat.veterinaryclinicmanager.business.abstracts.IDoctorService;
import com.yakuperenermurat.veterinaryclinicmanager.core.exception.NotFoundException;
import com.yakuperenermurat.veterinaryclinicmanager.core.utilies.Msg;
import com.yakuperenermurat.veterinaryclinicmanager.dao.IDoctorRepo;
import com.yakuperenermurat.veterinaryclinicmanager.dto.request.doctor.DoctorSaveRequest;
import com.yakuperenermurat.veterinaryclinicmanager.dto.request.doctor.DoctorUpdateRequest;
import com.yakuperenermurat.veterinaryclinicmanager.dto.response.doctor.DoctorResponse;
import com.yakuperenermurat.veterinaryclinicmanager.entities.Doctor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DoctorManager implements IDoctorService {

    private final IDoctorRepo doctorRepository;
    private final ModelMapper modelMapper;

    public DoctorManager(IDoctorRepo doctorRepository, ModelMapper modelMapper) {
        this.doctorRepository = doctorRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public DoctorResponse save(DoctorSaveRequest doctorSaveRequest) {
        // Yeni bir Doctor nesnesi oluştur ve kaydet
        Doctor doctor = modelMapper.map(doctorSaveRequest, Doctor.class);
        Doctor savedDoctor = doctorRepository.save(doctor);
        return modelMapper.map(savedDoctor, DoctorResponse.class);
    }

    @Override
    public DoctorResponse get(Long id) {
        // Belirtilen ID'ye sahip doktoru getir
        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(Msg.NOT_FOUND));
        return modelMapper.map(doctor, DoctorResponse.class);
    }

    @Override
    public DoctorResponse update(DoctorUpdateRequest doctorUpdateRequest) {
        // Belirtilen ID'ye sahip doktoru güncelle
        Doctor doctor = doctorRepository.findById(doctorUpdateRequest.getId())
                .orElseThrow(() -> new NotFoundException(Msg.NOT_FOUND));
        modelMapper.map(doctorUpdateRequest, doctor);
        Doctor updatedDoctor = doctorRepository.save(doctor);
        return modelMapper.map(updatedDoctor, DoctorResponse.class);
    }

    @Override
    public boolean delete(Long id) {
        // Belirtilen ID'ye sahip doktoru sil
        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(Msg.NOT_FOUND));
        doctorRepository.delete(doctor);
        return true;
    }

    @Override
    public List<DoctorResponse> getAll() {
        // Tüm doktorları getir ve yanıt olarak döndür
        return doctorRepository.findAll().stream()
                .map(doctor -> modelMapper.map(doctor, DoctorResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<DoctorResponse> getByName(String name) {
        // Belirtilen isme sahip doktorları getir
        List<Doctor> doctors = doctorRepository.findByName(name);
        return doctors.stream()
                .map(doctor -> modelMapper.map(doctor, DoctorResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public boolean isDoctorAvailable(long doctor, LocalDate date) {
        // Doktorun belirtilen tarihte müsait olup olmadığını kontrol et (şimdilik her zaman müsait)
        return true;
    }
}