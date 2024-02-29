package com.esrasen.vetclinicapi.business.concretes;

import com.esrasen.vetclinicapi.business.abstracts.IAppointmentService;
import com.esrasen.vetclinicapi.core.exception.appointment.DoctorNotAvailableException;
import com.esrasen.vetclinicapi.core.exception.NotFoundException;
import com.esrasen.vetclinicapi.core.exception.appointment.AppointmentSlotNotAvailableException;
import com.esrasen.vetclinicapi.core.exception.appointment.InvalidAppointmentTimeException;
import com.esrasen.vetclinicapi.core.utilies.Msg;
import com.esrasen.vetclinicapi.dao.IAppointmentRepo;
import com.esrasen.vetclinicapi.dao.IAvailableDateRepo;
import com.esrasen.vetclinicapi.entities.Appointment;
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


    @Override
    public Appointment save(Appointment appointment) {

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


        return this.appointmentRepo.save(appointment);
    }

    @Override
    public Appointment get(Long id) {
        return this.appointmentRepo.findById(id).orElseThrow(() -> new NotFoundException(Msg.NOT_FOUND));
    }

    @Override
    public Page<Appointment> cursor(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        return this.appointmentRepo.findAll(pageable);
    }

    @Override
    public Appointment update(Appointment appointment) {
        this.get(appointment.getId());
        return this.appointmentRepo.save(appointment);
    }

    @Override
    public boolean delete(Long id) {
        Appointment appointment = this.get(id);
        this.appointmentRepo.delete(appointment);
        return true;
    }

    @Override
    public List<Appointment> getAppointmentByDoctorAndDateRange(Long doctorId, LocalDateTime startDate, LocalDateTime finishDate) {
        return appointmentRepo.findByDoctorIdAndAppointmentDateBetween(doctorId, startDate, finishDate);
    }

    @Override
    public List<Appointment> getAppointmentByAnimalAndDateRange(Long animalId, LocalDateTime startDate, LocalDateTime finishDate) {
        return appointmentRepo.findByAnimalIdAndAppointmentDateBetween(animalId, startDate, finishDate);
    }


}
