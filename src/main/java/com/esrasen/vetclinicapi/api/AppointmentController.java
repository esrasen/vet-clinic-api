package com.esrasen.vetclinicapi.api;

import com.esrasen.vetclinicapi.business.abstracts.IAppointmentService;
import com.esrasen.vetclinicapi.core.result.ResultData;
import com.esrasen.vetclinicapi.core.utilies.ResultHelper;
import com.esrasen.vetclinicapi.dto.request.appointment.AppointmentSaveRequest;
import com.esrasen.vetclinicapi.dto.request.appointment.AppointmentUpdateRequest;
import com.esrasen.vetclinicapi.dto.response.CursorResponse;
import com.esrasen.vetclinicapi.dto.response.appointment.AppointmentResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/appointments")
@RequiredArgsConstructor
public class AppointmentController {

    private final IAppointmentService appointmentService;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<AppointmentResponse> get(@PathVariable("id") Long id) {
        AppointmentResponse appointmentResponse = appointmentService.getAppointmentById(id);
        return ResultHelper.success(appointmentResponse);
    }

    @GetMapping("/doctor/search")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<List<AppointmentResponse>> getAppointmentsByDoctorAndDateRange(
            @RequestParam(name = "doctorId") Long doctorId,
            @RequestParam(name = "startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam(name = "finishDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime finishDate) {
        List<AppointmentResponse> appointments = appointmentService.getAppointmentByDoctorAndDateRange(doctorId, startDate, finishDate);
        return ResultHelper.success(appointments);
    }

    @GetMapping("/animal/search")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<List<AppointmentResponse>> getAppointmentsByAnimalAndDateRange(
            @RequestParam(name = "animalId") Long animalId,
            @RequestParam(name = "startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam(name = "finishDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime finishDate) {
        List<AppointmentResponse> appointments = appointmentService.getAppointmentByAnimalAndDateRange(animalId, startDate, finishDate);
        return ResultHelper.success(appointments);
    }


    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CursorResponse<AppointmentResponse>> cursor(
            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
            @RequestParam(name = "pageSize", required = false, defaultValue = "10") int pageSize) {

        CursorResponse<AppointmentResponse> appointmentResponsePage = appointmentService.cursor(page, pageSize);
        return ResultHelper.success(appointmentResponsePage);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<AppointmentResponse> save(@Valid @RequestBody AppointmentSaveRequest appointmentSaveRequest) {
        AppointmentResponse saveAppointmentResponse = appointmentService.save(appointmentSaveRequest);
        return ResultHelper.created(saveAppointmentResponse);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<AppointmentResponse> update(@Valid @RequestBody AppointmentUpdateRequest appointmentUpdateRequest) {
        AppointmentResponse updateAppointmentResponse = appointmentService.update(appointmentUpdateRequest);
        return ResultHelper.success(updateAppointmentResponse);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<AppointmentResponse> delete(@PathVariable("id") Long id) {
        AppointmentResponse appointmentResponse = appointmentService.delete(id);
        return ResultHelper.success(appointmentResponse);
    }
}