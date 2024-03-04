package com.esrasen.vetclinicapi.business.concretes;

import com.esrasen.vetclinicapi.business.abstracts.IAppointmentService;
import com.esrasen.vetclinicapi.core.config.modelMapper.IModelMapperService;
import com.esrasen.vetclinicapi.core.exception.DublicateEntityException;
import com.esrasen.vetclinicapi.core.exception.appointment.DoctorNotAvailableException;
import com.esrasen.vetclinicapi.core.exception.NotFoundException;
import com.esrasen.vetclinicapi.core.exception.appointment.AppointmentSlotNotAvailableException;
import com.esrasen.vetclinicapi.core.exception.appointment.InvalidAppointmentTimeException;
import com.esrasen.vetclinicapi.core.utilies.Msg;
import com.esrasen.vetclinicapi.dao.IAnimalRepo;
import com.esrasen.vetclinicapi.dao.IAppointmentRepo;
import com.esrasen.vetclinicapi.dao.IAvailableDateRepo;
import com.esrasen.vetclinicapi.dao.IDoctorRepo;
import com.esrasen.vetclinicapi.dto.request.appointment.AppointmentSaveRequest;
import com.esrasen.vetclinicapi.dto.request.appointment.AppointmentUpdateRequest;
import com.esrasen.vetclinicapi.dto.response.CursorResponse;
import com.esrasen.vetclinicapi.dto.response.animal.AnimalResponse;
import com.esrasen.vetclinicapi.dto.response.appointment.AppointmentResponse;
import com.esrasen.vetclinicapi.entities.Animal;
import com.esrasen.vetclinicapi.entities.Appointment;
import com.esrasen.vetclinicapi.entities.Customer;
import com.esrasen.vetclinicapi.entities.Doctor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AppointmentManager implements IAppointmentService {

    private final IAppointmentRepo appointmentRepo;
    private final IAvailableDateRepo availableDateRepo;
    private final IModelMapperService modelMapper;
    private final IDoctorRepo doctorRepo;
    private final IAnimalRepo animalRepo;


    @Override
    public AppointmentResponse getAppointmentById(Long id) {
        Appointment appointment = appointmentRepo.findById(id).orElseThrow(() -> new NotFoundException(Msg.NOT_FOUND));
        return modelMapper.forResponse().map(appointment, AppointmentResponse.class);
    }

    @Override
    public List<AppointmentResponse> getAppointmentByDoctorAndDateRange(Long doctorId, LocalDateTime startDate, LocalDateTime finishDate) {

        List<Appointment> appointments = appointmentRepo.findByDoctorIdAndAppointmentDateBetween(doctorId, startDate, finishDate);
        if (appointments.isEmpty())
            throw new NotFoundException(Msg.NOT_FOUND);
        return modelMapper.mapList(appointments, AppointmentResponse.class);
    }

    @Override
    public List<AppointmentResponse> getAppointmentByAnimalAndDateRange(Long animalId, LocalDateTime startDate, LocalDateTime finishDate) {

        List<Appointment> appointments = appointmentRepo.findByAnimalIdAndAppointmentDateBetween(animalId, startDate, finishDate);
        if(appointments.isEmpty())
            throw new NotFoundException(Msg.NOT_FOUND);
        return modelMapper.mapList(appointments, AppointmentResponse.class);
    }

    @Override
    public CursorResponse<AppointmentResponse> cursor(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<Appointment> appointments = appointmentRepo.findAll(pageable);
        Page<AppointmentResponse> appointmentResponsePage = appointments
                .map(appointment -> this.modelMapper.forResponse().map(appointment, AppointmentResponse.class));

        return new CursorResponse<>(appointments.getNumber(), appointments.getSize(), appointments.getTotalElements(), appointmentResponsePage.getContent());

    }

    @Override
    public AppointmentResponse save(AppointmentSaveRequest appointmentSaveRequest) {
        if (isAppointmentAlreadyExist(appointmentSaveRequest)) {
            throw new DublicateEntityException(Msg.ALREADY_EXISTS);
        }
        Appointment appointment = modelMapper.forRequest().map(appointmentSaveRequest, Appointment.class);

        if (appointment.getAppointmentDate().getMinute() != 0) {
            throw new InvalidAppointmentTimeException(Msg.INVALID_APPOINTMENT_TIME);
        }
        LocalDate appointmentDay = appointment.getAppointmentDate().toLocalDate();
        boolean doctorAvailable = this.availableDateRepo.findByDoctorIdAndAvailableDate(appointment.getDoctor().getId(), appointmentDay).isPresent();
        if (!doctorAvailable) {
            throw new DoctorNotAvailableException(Msg.DOCTOR_NOT_AVAILABLE);
        }
        LocalDateTime startDateTime = appointment.getAppointmentDate();
        LocalDateTime finishDateTime = startDateTime.plusHours(1);
        boolean doctorOrAnimalUnavailable = !appointmentRepo.findByDoctorIdAndAppointmentDateBetween(appointment.getDoctor().getId(), startDateTime, finishDateTime).isEmpty() ||
                !appointmentRepo.findByAnimalIdAndAppointmentDateBetween(appointment.getAnimal().getId(), startDateTime, finishDateTime).isEmpty();
        if (doctorOrAnimalUnavailable) {
            throw new AppointmentSlotNotAvailableException(Msg.APPOINTMENT_SLOT_NOT_AVAILABLE);
        }

        Doctor doctor = doctorRepo.findById(appointmentSaveRequest.getDoctorId()).orElseThrow(() -> new NotFoundException(Msg.NOT_FOUND));
        appointment.setDoctor(doctor);
        Animal animal = animalRepo.findById(appointmentSaveRequest.getAnimalId()).orElseThrow(() -> new NotFoundException(Msg.NOT_FOUND));
        appointment.setAnimal(animal);
        return modelMapper.forResponse().map(appointmentRepo.save(appointment), AppointmentResponse.class);
    }

    @Override
    public AppointmentResponse update(AppointmentUpdateRequest appointmentUpdateRequest) {
        Appointment appointment = appointmentRepo.findById(appointmentUpdateRequest.getId()).orElseThrow(() -> new NotFoundException(Msg.NOT_FOUND));

        Doctor doctor = doctorRepo.findById(appointmentUpdateRequest.getDoctorId()).orElseThrow(() -> new NotFoundException(Msg.NOT_FOUND));
        appointment.setDoctor(doctor);

        Animal animal = animalRepo.findById(appointmentUpdateRequest.getAnimalId()).orElseThrow(() -> new NotFoundException(Msg.NOT_FOUND));
        appointment.setAnimal(animal);

        modelMapper.forRequest().map(appointmentUpdateRequest, appointment);

        Appointment updateAppointment = appointmentRepo.save(appointment);
        return modelMapper.forResponse().map(updateAppointment, AppointmentResponse.class);
    }

    @Override
    public AppointmentResponse delete(Long id) {
        Appointment appointment = appointmentRepo.findById(id).orElseThrow(() -> new NotFoundException(Msg.NOT_FOUND));
        appointmentRepo.delete(appointment);

        return modelMapper.forResponse().map(appointment, AppointmentResponse.class);
    }

    private boolean isAppointmentAlreadyExist(AppointmentSaveRequest appointmentSaveRequest) {
        return appointmentRepo.findByAppointmentDateAndDoctorIdAndAnimalId(
                        appointmentSaveRequest.getAppointmentDate(),
                        appointmentSaveRequest.getDoctorId(),
                        appointmentSaveRequest.getAnimalId())
                .isPresent();
    }
}