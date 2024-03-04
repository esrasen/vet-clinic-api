package com.esrasen.vetclinicapi.business.abstracts;

import com.esrasen.vetclinicapi.dto.request.appointment.AppointmentSaveRequest;
import com.esrasen.vetclinicapi.dto.request.appointment.AppointmentUpdateRequest;
import com.esrasen.vetclinicapi.dto.response.CursorResponse;
import com.esrasen.vetclinicapi.dto.response.appointment.AppointmentResponse;
import com.esrasen.vetclinicapi.entities.Appointment;
import com.esrasen.vetclinicapi.entities.Doctor;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
import java.util.List;

public interface IAppointmentService {

    AppointmentResponse getAppointmentById(Long id);

    List<AppointmentResponse> getAppointmentByDoctorAndDateRange(Long doctorId, LocalDateTime startDate, LocalDateTime finishDate);

    List<AppointmentResponse> getAppointmentByAnimalAndDateRange(Long animalId, LocalDateTime startDate, LocalDateTime finishDate);

    CursorResponse<AppointmentResponse> cursor(int page, int pageSize);

    AppointmentResponse save(AppointmentSaveRequest appointmentSaveRequest);

    AppointmentResponse update(AppointmentUpdateRequest appointmentUpdateRequest);

    AppointmentResponse delete(Long id);
}
