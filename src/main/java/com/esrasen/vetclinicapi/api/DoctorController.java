package com.esrasen.vetclinicapi.api;

import com.esrasen.vetclinicapi.business.abstracts.IDoctorService;
import com.esrasen.vetclinicapi.core.result.ResultData;
import com.esrasen.vetclinicapi.core.utilies.ResultHelper;
import com.esrasen.vetclinicapi.dto.request.doctor.DoctorSaveRequest;
import com.esrasen.vetclinicapi.dto.request.doctor.DoctorUpdateRequest;
import com.esrasen.vetclinicapi.dto.response.CursorResponse;
import com.esrasen.vetclinicapi.dto.response.doctor.DoctorResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/doctors")
@RequiredArgsConstructor
public class DoctorController {

    private final IDoctorService doctorService;

    @GetMapping("/{id}")
    public ResultData<DoctorResponse> getDoctorById(@PathVariable("id") Long id) {
        DoctorResponse doctorResponse = doctorService.getDoctorById(id);
        return ResultHelper.success(doctorResponse);
    }

    @GetMapping()
    public ResultData<CursorResponse<DoctorResponse>> cursor(
            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
            @RequestParam(name = "pageSize", required = false, defaultValue = "10") int pageSize) {
        CursorResponse<DoctorResponse> doctorResponsePage = doctorService.cursor(page, pageSize);
        return ResultHelper.success(doctorResponsePage);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<DoctorResponse> save(@Valid @RequestBody DoctorSaveRequest doctorSaveRequest) {
        DoctorResponse saveDoctorResponse = doctorService.save(doctorSaveRequest);
        return ResultHelper.created(saveDoctorResponse);
    }

    @PutMapping()
    public ResultData<DoctorResponse> update(@Valid @RequestBody DoctorUpdateRequest doctorUpdateRequest) {
        DoctorResponse updateDoctorResponse = doctorService.update(doctorUpdateRequest);
        return ResultHelper.success(updateDoctorResponse);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<DoctorResponse> delete(@PathVariable("id") Long id) {
        DoctorResponse doctorResponse = doctorService.delete(id);
        return ResultHelper.success(doctorResponse);
    }
}