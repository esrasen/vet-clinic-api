package com.esrasen.vetclinicapi.api;

import com.esrasen.vetclinicapi.business.abstracts.IAnimalService;
import com.esrasen.vetclinicapi.business.abstracts.IVaccineService;
import com.esrasen.vetclinicapi.core.config.modelMapper.IModelMapperService;
import com.esrasen.vetclinicapi.core.result.Result;
import com.esrasen.vetclinicapi.core.result.ResultData;
import com.esrasen.vetclinicapi.core.utilies.ResultHelper;
import com.esrasen.vetclinicapi.dto.request.vaccine.VaccineSaveRequest;
import com.esrasen.vetclinicapi.dto.request.vaccine.VaccineUpdateRequest;
import com.esrasen.vetclinicapi.dto.response.CursorResponse;
import com.esrasen.vetclinicapi.dto.response.vaccine.VaccineResponse;
import com.esrasen.vetclinicapi.entities.Vaccine;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vaccines")
public class VaccineController {
    private final IVaccineService vaccineService;
    private final IModelMapperService modelMapper;

    private final IAnimalService animalService;

    public VaccineController(IVaccineService vaccineService, IModelMapperService modelMapper, IAnimalService animalService) {
        this.vaccineService = vaccineService;
        this.modelMapper = modelMapper;
        this.animalService = animalService;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<VaccineResponse> save(@Valid @RequestBody VaccineSaveRequest vaccineSaveRequest) {

        Vaccine saveVaccine = this.modelMapper.forRequest().map(vaccineSaveRequest, Vaccine.class);
        this.vaccineService.save(saveVaccine);
        return ResultHelper.created(this.modelMapper.forResponse().map(saveVaccine, VaccineResponse.class));

    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<VaccineResponse> get(@PathVariable("id") Long id) {
        Vaccine vaccine = this.vaccineService.get(id);
        return ResultHelper.success(this.modelMapper.forResponse().map(vaccine, VaccineResponse.class));
    }

    @GetMapping("/info")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<List<VaccineResponse>> get1(@RequestParam("id") Long id) {
        List<Vaccine> vaccines = this.vaccineService.findByAnimalId(id);
        List<VaccineResponse> vaccineResponses = modelMapper.mapList(vaccines, VaccineResponse.class);

        return ResultHelper.success(vaccineResponses);
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CursorResponse<VaccineResponse>> cursor(
            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
            @RequestParam(name = "pageSize", required = false, defaultValue = "10") int pageSize) {

        Page<Vaccine> vaccinePage = this.vaccineService.cursor(page, pageSize);
        Page<VaccineResponse> vaccineResponsePage = vaccinePage
                .map(vaccine -> this.modelMapper.forResponse().map(vaccine, VaccineResponse.class));

        return ResultHelper.cursor(vaccineResponsePage);
    }


    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<VaccineResponse> update(@Valid @RequestBody VaccineUpdateRequest vaccineUpdateRequest) {
        Vaccine updateVaccine = this.modelMapper.forRequest().map(vaccineUpdateRequest, Vaccine.class);
        this.vaccineService.update(updateVaccine);
        return ResultHelper.success(this.modelMapper.forResponse().map(updateVaccine, VaccineResponse.class));
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@PathVariable("id") Long id) {
        this.vaccineService.delete(id);
        return ResultHelper.ok();
    }

}
