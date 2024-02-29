package com.esrasen.vetclinicapi.business.abstracts;

import com.esrasen.vetclinicapi.entities.Appointment;
import com.esrasen.vetclinicapi.entities.Doctor;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
import java.util.List;

public interface IAppointmentService {

    Appointment save(Appointment appointment);
    Appointment get(Long id);
    Page<Appointment> cursor(int page, int pageSize);
    Appointment update(Appointment appointment);
    boolean delete(Long id);
    List<Appointment> getAppointmentByDoctorAndDateRange(Long doctorId, LocalDateTime startDate, LocalDateTime finishDate);
    List<Appointment> getAppointmentByAnimalAndDateRange(Long animalId, LocalDateTime startDate, LocalDateTime finishDate);
}
