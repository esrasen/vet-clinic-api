package com.esrasen.vetclinicapi.api;

import com.esrasen.vetclinicapi.business.abstracts.IAvailableDateService;
import com.esrasen.vetclinicapi.core.result.ResultData;
import com.esrasen.vetclinicapi.core.utilies.ResultHelper;
import com.esrasen.vetclinicapi.dto.request.availableDate.AvailableDateSaveRequest;
import com.esrasen.vetclinicapi.dto.request.availableDate.AvailableDateUpdateRequest;
import com.esrasen.vetclinicapi.dto.response.CursorResponse;
import com.esrasen.vetclinicapi.dto.response.availableDate.AvailableDateResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/available-dates")
@RequiredArgsConstructor
public class AvailableDateController {

    private final IAvailableDateService availableDateService;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<AvailableDateResponse> getAvailableDateById(@PathVariable("id") Long id) {
        AvailableDateResponse availableDateResponse = availableDateService.getAvailableDateById(id);
        return ResultHelper.success(availableDateResponse);
    }

    @GetMapping()
    public ResultData<CursorResponse<AvailableDateResponse>> cursor(
            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
            @RequestParam(name = "pageSize", required = false, defaultValue = "10") int pageSize) {
        CursorResponse<AvailableDateResponse> availableDateResponsePage = availableDateService.cursor(page, pageSize);
        return ResultHelper.success(availableDateResponsePage);
    }


    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<AvailableDateResponse> save(@Valid @RequestBody AvailableDateSaveRequest availableDateSaveRequest) {
        AvailableDateResponse saveAvailableDateResponse = availableDateService.save(availableDateSaveRequest);
        return ResultHelper.created(saveAvailableDateResponse);
    }

    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<AvailableDateResponse> update(@Valid @RequestBody AvailableDateUpdateRequest availableDateUpdateRequest) {
        AvailableDateResponse updateAvailableDateResponse = availableDateService.update(availableDateUpdateRequest);
        return ResultHelper.success(updateAvailableDateResponse);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<AvailableDateResponse> delete(@PathVariable("id") Long id) {
        AvailableDateResponse availableDateResponse = availableDateService.delete(id);
        return ResultHelper.success(availableDateResponse);
    }
}