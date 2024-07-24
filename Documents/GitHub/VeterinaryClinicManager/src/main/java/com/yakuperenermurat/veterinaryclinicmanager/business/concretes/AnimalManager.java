package com.yakuperenermurat.veterinaryclinicmanager.business.concretes;

import com.yakuperenermurat.veterinaryclinicmanager.business.abstracts.IAnimalService;
import com.yakuperenermurat.veterinaryclinicmanager.core.exception.NotFoundException;
import com.yakuperenermurat.veterinaryclinicmanager.core.utilies.Msg;
import com.yakuperenermurat.veterinaryclinicmanager.dao.IAnimalRepo;
import com.yakuperenermurat.veterinaryclinicmanager.dao.ICustomerRepo;
import com.yakuperenermurat.veterinaryclinicmanager.dto.request.animal.AnimalSaveRequest;
import com.yakuperenermurat.veterinaryclinicmanager.dto.request.animal.AnimalUpdateRequest;
import com.yakuperenermurat.veterinaryclinicmanager.dto.response.animal.AnimalResponse;
import com.yakuperenermurat.veterinaryclinicmanager.entities.Animal;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AnimalManager implements IAnimalService {

    private final IAnimalRepo animalRepository;
    private final ICustomerRepo customerRepository;
    private final ModelMapper modelMapper;

    public AnimalManager(IAnimalRepo animalRepository, ICustomerRepo customerRepository, ModelMapper modelMapper) {
        this.animalRepository = animalRepository;
        this.customerRepository = customerRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public AnimalResponse save(AnimalSaveRequest animalSaveRequest) {
        // Yeni bir Animal nesnesi oluştur
        Animal animal = new Animal();
        animal.setName(animalSaveRequest.getName());
        animal.setSpecies(animalSaveRequest.getSpecies());
        animal.setBreed(animalSaveRequest.getBreed());
        animal.setGender(animalSaveRequest.getGender());
        animal.setColour(animalSaveRequest.getColour());
        animal.setDateOfBirth(animalSaveRequest.getDateOfBirth());
        animal.setCustomer(customerRepository.findById(animalSaveRequest.getCustomerId())
                .orElseThrow(() -> new NotFoundException(Msg.NOT_FOUND)));

        // Animal nesnesini kaydet ve yanıt olarak döndür
        Animal savedAnimal = animalRepository.save(animal);
        return modelMapper.map(savedAnimal, AnimalResponse.class);
    }

    @Override
    public AnimalResponse get(Long id) {
        // Belirtilen ID'ye sahip hayvanı getir
        Animal animal = animalRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(Msg.NOT_FOUND));
        return modelMapper.map(animal, AnimalResponse.class);
    }

    @Override
    public AnimalResponse update(AnimalUpdateRequest animalUpdateRequest) {
        // Belirtilen ID'ye sahip hayvanı güncelle
        Animal animal = animalRepository.findById(animalUpdateRequest.getId())
                .orElseThrow(() -> new NotFoundException(Msg.NOT_FOUND));
        modelMapper.map(animalUpdateRequest, animal);
        Animal updatedAnimal = animalRepository.save(animal);
        return modelMapper.map(updatedAnimal, AnimalResponse.class);
    }

    @Override
    public boolean delete(Long id) {
        // Belirtilen ID'ye sahip hayvanı sil
        Animal animal = animalRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(Msg.NOT_FOUND));
        animalRepository.delete(animal);
        return true;
    }

    @Override
    public List<AnimalResponse> getAll() {
        // Tüm hayvanları getir ve yanıt olarak döndür
        return animalRepository.findAll().stream()
                .map(animal -> modelMapper.map(animal, AnimalResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<AnimalResponse> getByCustomerId(Long customerId) {
        // Belirtilen müşteri ID'sine sahip hayvanları getir
        List<Animal> animals = animalRepository.findByCustomerId(customerId);
        return animals.stream()
                .map(animal -> modelMapper.map(animal, AnimalResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<AnimalResponse> getByName(String name) {
        // Belirtilen isme sahip hayvanları getir
        List<Animal> animals = animalRepository.findByName(name);
        return animals.stream()
                .map(animal -> modelMapper.map(animal, AnimalResponse.class))
                .collect(Collectors.toList());
    }
}