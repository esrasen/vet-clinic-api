package com.esrasen.vetclinicapi.api;

import com.esrasen.vetclinicapi.business.abstracts.IVaccineService;
import com.esrasen.vetclinicapi.core.result.ResultData;
import com.esrasen.vetclinicapi.core.utilies.ResultHelper;
import com.esrasen.vetclinicapi.dto.request.vaccine.VaccineSaveRequest;
import com.esrasen.vetclinicapi.dto.request.vaccine.VaccineUpdateRequest;
import com.esrasen.vetclinicapi.dto.response.CursorResponse;
import com.esrasen.vetclinicapi.dto.response.vaccine.VaccineResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/vaccines")
@RequiredArgsConstructor
public class VaccineController {

    private final IVaccineService vaccineService;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<VaccineResponse> getVaccineById(@PathVariable("id") Long id) {
        VaccineResponse vaccineResponse = vaccineService.getVaccineById(id);
        return ResultHelper.success(vaccineResponse);
    }

    @GetMapping("/info")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<List<VaccineResponse>> get1(@RequestParam("id") Long id) {
        List<VaccineResponse> vaccineResponses = vaccineService.findByAnimalId(id);
        return ResultHelper.success(vaccineResponses);
    }

    @GetMapping("/expiring")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<List<VaccineResponse>> getVaccines(@RequestParam(name = "startDate") LocalDate startDate,
                                                         @RequestParam(name = "finishDate") LocalDate finishDate) {
        return vaccineService.findByProtectionFinishDateBetween(startDate, finishDate);
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CursorResponse<VaccineResponse>> cursor(
            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
            @RequestParam(name = "pageSize", required = false, defaultValue = "10") int pageSize) {
        CursorResponse<VaccineResponse> vaccineResponsePage = vaccineService.cursor(page, pageSize);
        return ResultHelper.success(vaccineResponsePage);
    }


    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<VaccineResponse> save(@Valid @RequestBody VaccineSaveRequest vaccineSaveRequest) {
        VaccineResponse saveVaccineResponse = vaccineService.save(vaccineSaveRequest);
        return ResultHelper.created(saveVaccineResponse);
    }

    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<VaccineResponse> update(@Valid @RequestBody VaccineUpdateRequest vaccineUpdateRequest) {
        VaccineResponse updateVaccineResponse = vaccineService.update(vaccineUpdateRequest);
        return ResultHelper.success(updateVaccineResponse);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<VaccineResponse> delete(@PathVariable("id") Long id) {
        VaccineResponse vaccineResponse = vaccineService.delete(id);
        return ResultHelper.success(vaccineResponse);
    }
}