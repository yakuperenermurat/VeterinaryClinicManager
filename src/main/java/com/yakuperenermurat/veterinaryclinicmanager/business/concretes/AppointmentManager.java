package com.yakuperenermurat.veterinaryclinicmanager.business.concretes;

import com.yakuperenermurat.veterinaryclinicmanager.business.abstracts.IAppointmentService;
import com.yakuperenermurat.veterinaryclinicmanager.core.exception.NotFoundException;
import com.yakuperenermurat.veterinaryclinicmanager.core.exception.ScheduleConflictException;
import com.yakuperenermurat.veterinaryclinicmanager.core.utilies.Msg;
import com.yakuperenermurat.veterinaryclinicmanager.dao.IAnimalRepo;
import com.yakuperenermurat.veterinaryclinicmanager.dao.IAppointmentRepo;
import com.yakuperenermurat.veterinaryclinicmanager.dao.IDoctorRepo;
import com.yakuperenermurat.veterinaryclinicmanager.dto.request.appointment.AppointmentSaveRequest;
import com.yakuperenermurat.veterinaryclinicmanager.dto.request.appointment.AppointmentUpdateRequest;
import com.yakuperenermurat.veterinaryclinicmanager.dto.response.appointment.AppointmentResponse;
import com.yakuperenermurat.veterinaryclinicmanager.entities.Animal;
import com.yakuperenermurat.veterinaryclinicmanager.entities.Appointment;
import com.yakuperenermurat.veterinaryclinicmanager.entities.Doctor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AppointmentManager implements IAppointmentService {

    private final IAppointmentRepo appointmentRepository;
    private final IAnimalRepo animalRepository;
    private final IDoctorRepo doctorRepository;
    private final DoctorManager doctorManager;
    private final ModelMapper modelMapper;

    public AppointmentManager(IAppointmentRepo appointmentRepository, IAnimalRepo animalRepository, IDoctorRepo doctorRepository, DoctorManager doctorManager, ModelMapper modelMapper) {
        this.appointmentRepository = appointmentRepository;
        this.animalRepository = animalRepository;
        this.doctorRepository = doctorRepository;
        this.doctorManager = doctorManager;
        this.modelMapper = modelMapper;
    }

    @Override
    @Transactional(rollbackFor = {ScheduleConflictException.class, RuntimeException.class})
    public AppointmentResponse save(AppointmentSaveRequest request) {
        // Belirtilen hayvan ve doktoru getir
        Animal animal = animalRepository.findById(request.getAnimalId())
                .orElseThrow(() -> new NotFoundException(Msg.NOT_FOUND));
        Doctor doctor = doctorRepository.findById(request.getDoctorId())
                .orElseThrow(() -> new NotFoundException(Msg.NOT_FOUND));

        LocalDateTime appointmentDateTime = request.getAppointmentDate();

        // Doktorun belirtilen tarihte müsait olup olmadığını kontrol et
        if (!doctorManager.isDoctorAvailable(doctor.getId(), appointmentDateTime.toLocalDate())) {
            throw new ScheduleConflictException(Msg.DOCTOR_NOT_AVAILABLE);
        }

        // Randevu çakışmasını kontrol et
        if (appointmentRepository.existsByDoctor_IdAndAppointmentDate(doctor.getId(), appointmentDateTime)) {
            throw new ScheduleConflictException(Msg.APPOINTMENT_CONFLICT);
        }

        // Randevu oluştur ve kaydet
        Appointment appointment = new Appointment();
        appointment.setAnimal(animal);
        appointment.setDoctor(doctor);
        appointment.setAppointmentDate(appointmentDateTime);

        appointment = appointmentRepository.save(appointment);

        AppointmentResponse response = modelMapper.map(appointment, AppointmentResponse.class);
        response.setMessage(Msg.APPOINTMENT_CREATED);

        return response;
    }

    @Override
    public AppointmentResponse update(AppointmentUpdateRequest appointmentUpdateRequest) {
        // Belirtilen randevuyu getir ve güncelle
        Appointment appointment = appointmentRepository.findById(appointmentUpdateRequest.getId())
                .orElseThrow(() -> new NotFoundException(Msg.NOT_FOUND));
        Animal animal = animalRepository.findById(appointmentUpdateRequest.getAnimalId())
                .orElseThrow(() -> new NotFoundException(Msg.NOT_FOUND));
        Doctor doctor = doctorRepository.findById(appointmentUpdateRequest.getDoctorId())
                .orElseThrow(() -> new NotFoundException(Msg.NOT_FOUND));

        modelMapper.map(appointmentUpdateRequest, appointment);
        appointment.setAnimal(animal);
        appointment.setDoctor(doctor);

        Appointment updatedAppointment = appointmentRepository.save(appointment);
        AppointmentResponse response = modelMapper.map(updatedAppointment, AppointmentResponse.class);
        response.setMessage(Msg.APPOINTMENT_CREATED);

        return response;
    }

    @Override
    public boolean delete(Long id) {
        // Belirtilen randevuyu sil
        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(Msg.NOT_FOUND));
        appointmentRepository.delete(appointment);
        return true;
    }

    @Override
    public AppointmentResponse get(Long id) {
        // Belirtilen ID'ye sahip randevuyu getir
        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(Msg.NOT_FOUND));
        AppointmentResponse response = modelMapper.map(appointment, AppointmentResponse.class);
        response.setMessage(Msg.OK);
        return response;
    }

    @Override
    public List<AppointmentResponse> getAll() {
        // Tüm randevuları getir ve yanıt olarak döndür
        return appointmentRepository.findAll().stream()
                .map(appointment -> {
                    AppointmentResponse response = modelMapper.map(appointment, AppointmentResponse.class);
                    response.setMessage(Msg.OK);
                    return response;
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<AppointmentResponse> getByAnimalId(Long animalId) {
        // Belirtilen hayvan ID'sine sahip randevuları getir
        List<Appointment> appointments = appointmentRepository.findByAnimalId(animalId);
        return appointments.stream()
                .map(appointment -> {
                    AppointmentResponse response = modelMapper.map(appointment, AppointmentResponse.class);
                    response.setMessage(Msg.OK);
                    return response;
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<AppointmentResponse> getByDoctorId(Long doctorId) {
        // Belirtilen doktor ID'sine sahip randevuları getir
        List<Appointment> appointments = appointmentRepository.findByDoctorId(doctorId);
        return appointments.stream()
                .map(appointment -> {
                    AppointmentResponse response = modelMapper.map(appointment, AppointmentResponse.class);
                    response.setMessage(Msg.OK);
                    return response;
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<AppointmentResponse> getByDateRange(LocalDateTime startDate, LocalDateTime endDate) {
        // Belirtilen tarih aralığındaki randevuları getir
        List<Appointment> appointments = appointmentRepository.findByAppointmentDateBetween(startDate, endDate);
        return appointments.stream()
                .map(appointment -> {
                    AppointmentResponse response = modelMapper.map(appointment, AppointmentResponse.class);
                    response.setMessage(Msg.OK);
                    return response;
                })
                .collect(Collectors.toList());
    }
}