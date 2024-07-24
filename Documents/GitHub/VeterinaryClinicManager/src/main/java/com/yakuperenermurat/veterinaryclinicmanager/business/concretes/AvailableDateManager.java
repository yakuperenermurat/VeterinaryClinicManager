package com.yakuperenermurat.veterinaryclinicmanager.business.concretes;
import com.yakuperenermurat.veterinaryclinicmanager.core.exception.AlreadyExistsException;
import com.yakuperenermurat.veterinaryclinicmanager.business.abstracts.IAvailableDateService;
import com.yakuperenermurat.veterinaryclinicmanager.core.exception.NotFoundException;
import com.yakuperenermurat.veterinaryclinicmanager.core.utilies.Msg;
import com.yakuperenermurat.veterinaryclinicmanager.dao.IAvailableDateRepo;
import com.yakuperenermurat.veterinaryclinicmanager.dao.IDoctorRepo;
import com.yakuperenermurat.veterinaryclinicmanager.dto.request.availableDate.AvailableDateSaveRequest;
import com.yakuperenermurat.veterinaryclinicmanager.dto.request.availableDate.AvailableDateUpdateRequest;
import com.yakuperenermurat.veterinaryclinicmanager.dto.response.availableDate.AvailableDateResponse;
import com.yakuperenermurat.veterinaryclinicmanager.entities.AvailableDate;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AvailableDateManager implements IAvailableDateService {

    private final IAvailableDateRepo availableDateRepository;
    private final IDoctorRepo doctorRepository;
    private final ModelMapper modelMapper;

    public AvailableDateManager(IAvailableDateRepo availableDateRepository, IDoctorRepo doctorRepository, ModelMapper modelMapper) {
        this.availableDateRepository = availableDateRepository;
        this.doctorRepository = doctorRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public AvailableDateResponse save(AvailableDateSaveRequest availableDateSaveRequest) {
        // Belirtilen doktor için aynı tarih var mı kontrol et
        if (availableDateRepository.existsByDoctorIdAndAvailableDate(availableDateSaveRequest.getDoctorId(), availableDateSaveRequest.getAvailableDate())) {
            throw new AlreadyExistsException(Msg.ALREADY_EXISTS);
        }

        // Yeni bir AvailableDate nesnesi oluştur
        AvailableDate availableDate = new AvailableDate();
        availableDate.setAvailableDate(availableDateSaveRequest.getAvailableDate());
        availableDate.setDoctor(doctorRepository.findById(availableDateSaveRequest.getDoctorId())
                .orElseThrow(() -> new NotFoundException(Msg.NOT_FOUND)));
        AvailableDate savedAvailableDate = availableDateRepository.save(availableDate);
        return modelMapper.map(savedAvailableDate, AvailableDateResponse.class);
    }

    @Override
    public AvailableDateResponse get(Long id) {
        // Belirtilen ID'ye sahip müsait tarihi getir
        AvailableDate availableDate = availableDateRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(Msg.NOT_FOUND));
        return modelMapper.map(availableDate, AvailableDateResponse.class);
    }

    @Override
    public AvailableDateResponse update(AvailableDateUpdateRequest availableDateUpdateRequest) {
        // Belirtilen ID'ye sahip müsait tarihi güncelle
        AvailableDate availableDate = availableDateRepository.findById(availableDateUpdateRequest.getId())
                .orElseThrow(() -> new NotFoundException(Msg.NOT_FOUND));

        // Güncelleme sırasında aynı doktora aynı tarih atanmış mı kontrol et
        if (availableDateRepository.existsByDoctorIdAndAvailableDate(availableDateUpdateRequest.getDoctorId(), availableDateUpdateRequest.getAvailableDate()) && !availableDate.getId().equals(availableDateUpdateRequest.getId())) {
            throw new AlreadyExistsException(Msg.ALREADY_EXISTS);
        }

        modelMapper.map(availableDateUpdateRequest, availableDate);
        AvailableDate updatedAvailableDate = availableDateRepository.save(availableDate);
        return modelMapper.map(updatedAvailableDate, AvailableDateResponse.class);
    }

    @Override
    public boolean delete(Long id) {
        // Belirtilen ID'ye sahip müsait tarihi sil
        AvailableDate availableDate = availableDateRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(Msg.NOT_FOUND));
        availableDateRepository.delete(availableDate);
        return true;
    }

    @Override
    public List<AvailableDateResponse> getAll() {
        // Tüm müsait tarihleri getir ve yanıt olarak döndür
        return availableDateRepository.findAll().stream()
                .map(availableDate -> modelMapper.map(availableDate, AvailableDateResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<AvailableDateResponse> getByDoctorId(Long doctorId) {
        // Belirtilen doktor ID'sine sahip müsait tarihleri getir
        List<AvailableDate> availableDates = availableDateRepository.findByDoctorId(doctorId);
        return availableDates.stream()
                .map(availableDate -> modelMapper.map(availableDate, AvailableDateResponse.class))
                .collect(Collectors.toList());
    }
}