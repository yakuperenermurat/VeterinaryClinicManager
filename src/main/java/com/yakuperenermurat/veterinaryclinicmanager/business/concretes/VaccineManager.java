package com.yakuperenermurat.veterinaryclinicmanager.business.concretes;

import com.yakuperenermurat.veterinaryclinicmanager.business.abstracts.IVaccineService;
import com.yakuperenermurat.veterinaryclinicmanager.core.exception.NotFoundException;
import com.yakuperenermurat.veterinaryclinicmanager.core.utilies.Msg;
import com.yakuperenermurat.veterinaryclinicmanager.dao.IAnimalRepo;
import com.yakuperenermurat.veterinaryclinicmanager.dao.IVaccineRepo;
import com.yakuperenermurat.veterinaryclinicmanager.dto.request.vaccine.VaccineSaveRequest;
import com.yakuperenermurat.veterinaryclinicmanager.dto.request.vaccine.VaccineUpdateRequest;
import com.yakuperenermurat.veterinaryclinicmanager.dto.response.vaccine.VaccineAnimalResponse;
import com.yakuperenermurat.veterinaryclinicmanager.dto.response.vaccine.VaccineResponse;
import com.yakuperenermurat.veterinaryclinicmanager.entities.Vaccine;
import com.yakuperenermurat.veterinaryclinicmanager.entities.Animal;
import jakarta.validation.ValidationException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class VaccineManager implements IVaccineService {
    private final IVaccineRepo vaccineRepository;
    private final IAnimalRepo animalRepository;
    private final ModelMapper modelMapper;

    public VaccineManager(IVaccineRepo vaccineRepository, IAnimalRepo animalRepository, ModelMapper modelMapper) {
        this.vaccineRepository = vaccineRepository;
        this.animalRepository = animalRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public VaccineResponse save(VaccineSaveRequest vaccineSaveRequest) {
        // Belirtilen hayvanı getir
        Animal animal = animalRepository.findById(vaccineSaveRequest.getAnimalId())
                .orElseThrow(() -> new NotFoundException(Msg.NOT_FOUND));

        // Koruyuculuk bitiş tarihini kontrol et
        if (vaccineSaveRequest.getProtectionFinishDate().isBefore(LocalDate.now())) {
            throw new ValidationException("Aşı koruyuculuk bitiş tarihi geçmiş.");
        }

        // Hayvanın aynı aşıya sahip olup olmadığını kontrol et
        boolean exists = vaccineRepository.existsByAnimal_IdAndCode(animal.getId(), vaccineSaveRequest.getCode());
        if (exists) {
            throw new ValidationException("Bu hayvan için bu aşı zaten kayıtlı.");
        }

        // Yeni bir Vaccine nesnesi oluştur ve kaydet
        Vaccine vaccine = new Vaccine();
        vaccine.setName(vaccineSaveRequest.getName());
        vaccine.setCode(vaccineSaveRequest.getCode());
        vaccine.setProtectionStartDate(vaccineSaveRequest.getProtectionStartDate());
        vaccine.setProtectionFinishDate(vaccineSaveRequest.getProtectionFinishDate());
        vaccine.setAnimal(animal);

        Vaccine savedVaccine = vaccineRepository.save(vaccine);
        return modelMapper.map(savedVaccine, VaccineResponse.class);
    }

    @Override
    public VaccineResponse get(Long id) {
        // Belirtilen ID'ye sahip aşıyı getir
        Vaccine vaccine = vaccineRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(Msg.NOT_FOUND));
        return modelMapper.map(vaccine, VaccineResponse.class);
    }

    @Override
    public VaccineResponse update(VaccineUpdateRequest vaccineUpdateRequest) {
        // Belirtilen ID'ye sahip aşıyı güncelle
        Vaccine vaccine = vaccineRepository.findById(vaccineUpdateRequest.getId())
                .orElseThrow(() -> new NotFoundException(Msg.NOT_FOUND));
        Animal animal = animalRepository.findById(vaccineUpdateRequest.getAnimalId())
                .orElseThrow(() -> new NotFoundException(Msg.NOT_FOUND));

        vaccine.setName(vaccineUpdateRequest.getName());
        vaccine.setCode(vaccineUpdateRequest.getCode());
        vaccine.setProtectionStartDate(vaccineUpdateRequest.getProtectionStartDate());
        vaccine.setProtectionFinishDate(vaccineUpdateRequest.getProtectionFinishDate());
        vaccine.setAnimal(animal);

        Vaccine updatedVaccine = vaccineRepository.save(vaccine);
        return modelMapper.map(updatedVaccine, VaccineResponse.class);
    }

    @Override
    public boolean delete(Long id) {
        // Belirtilen ID'ye sahip aşıyı sil
        Vaccine vaccine = vaccineRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(Msg.NOT_FOUND));
        vaccineRepository.delete(vaccine);
        return true;
    }

    @Override
    public List<VaccineResponse> getAll() {
        // Tüm aşıları getir ve yanıt olarak döndür
        return vaccineRepository.findAll().stream()
                .map(vaccine -> modelMapper.map(vaccine, VaccineResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<VaccineResponse> getByAnimalId(Long animalId) {
        // Belirtilen hayvan ID'sine sahip aşıları getir
        List<Vaccine> vaccines = vaccineRepository.findByAnimalId(animalId);
        return vaccines.stream()
                .map(vaccine -> modelMapper.map(vaccine, VaccineResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<VaccineAnimalResponse> getByProtectionFinishDateBetween(LocalDate startDate, LocalDate endDate) {
        List<Vaccine> vaccines = vaccineRepository.findByProtectionFinishDateBetween(startDate, endDate);
        return vaccines.stream()
                .map(vaccine -> {
                    VaccineAnimalResponse response = modelMapper.map(vaccine, VaccineAnimalResponse.class);
                    response.setAnimalId(vaccine.getAnimal().getId());
                    response.setAnimalName(vaccine.getAnimal().getName());
                    response.setSpecies(vaccine.getAnimal().getSpecies());
                    response.setBreed(vaccine.getAnimal().getBreed());
                    response.setGender(vaccine.getAnimal().getGender());
                    response.setColour(vaccine.getAnimal().getColour());
                    response.setDateOfBirth(vaccine.getAnimal().getDateOfBirth());
                    return response;
                })
                .collect(Collectors.toList());
    }
}