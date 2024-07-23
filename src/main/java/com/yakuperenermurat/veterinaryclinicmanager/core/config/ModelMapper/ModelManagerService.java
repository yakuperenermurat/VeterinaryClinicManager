package com.yakuperenermurat.veterinaryclinicmanager.core.config.ModelMapper;

import com.yakuperenermurat.veterinaryclinicmanager.dto.request.appointment.AppointmentSaveRequest;
import com.yakuperenermurat.veterinaryclinicmanager.entities.Animal;
import com.yakuperenermurat.veterinaryclinicmanager.entities.Appointment;
import com.yakuperenermurat.veterinaryclinicmanager.entities.Doctor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ModelManagerService implements IModelMapperService {
    private final ModelMapper modelMapper;

    @Autowired
    public ModelManagerService(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public ModelMapper forRequest() {
        this.modelMapper.getConfiguration().setAmbiguityIgnored(true).setMatchingStrategy(MatchingStrategies.STANDARD);
        return this.modelMapper;
    }

    @Override
    public ModelMapper forResponse() {
        this.modelMapper.getConfiguration().setAmbiguityIgnored(true).setMatchingStrategy(MatchingStrategies.LOOSE);
        return this.modelMapper;
    }
}
