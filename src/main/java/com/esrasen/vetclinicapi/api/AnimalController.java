package com.esrasen.vetclinicapi.api;

import com.esrasen.vetclinicapi.business.abstracts.IAnimalService;
import com.esrasen.vetclinicapi.core.result.ResultData;
import com.esrasen.vetclinicapi.core.utilies.ResultHelper;
import com.esrasen.vetclinicapi.dto.request.animal.AnimalSaveRequest;
import com.esrasen.vetclinicapi.dto.request.animal.AnimalUpdateRequest;
import com.esrasen.vetclinicapi.dto.response.CursorResponse;
import com.esrasen.vetclinicapi.dto.response.animal.AnimalResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/animals")
@RequiredArgsConstructor
public class AnimalController {

    private final IAnimalService animalService;

    @GetMapping("/{id}")
    public ResultData<AnimalResponse> getAnimalById(@PathVariable("id") Long id) {
        AnimalResponse animalResponse = this.animalService.getAnimalById(id);
        return ResultHelper.success(animalResponse);
    }

    @GetMapping()
    public ResultData<CursorResponse<AnimalResponse>> cursor(
            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
            @RequestParam(name = "pageSize", required = false, defaultValue = "10") int pageSize) {

        CursorResponse<AnimalResponse> animalResponsePage = this.animalService.cursor(page, pageSize);
        return ResultHelper.success(animalResponsePage);

    }

    @GetMapping("/info")
    public ResultData<List<AnimalResponse>> get(@RequestParam(name = "name") String name) {
        List<AnimalResponse> animals = this.animalService.findAllByName(name);

        return ResultHelper.success(animals);
    }


    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<AnimalResponse> save(@Valid @RequestBody AnimalSaveRequest animalSaveRequest) {
        AnimalResponse saveAnimalResponse = animalService.save(animalSaveRequest);

        return ResultHelper.created(saveAnimalResponse);
    }

    @PutMapping()
    public ResultData<AnimalResponse> update(@Valid @RequestBody AnimalUpdateRequest animalUpdateRequest) {
        AnimalResponse updateAnimal = this.animalService.update(animalUpdateRequest);
        return ResultHelper.success(updateAnimal);
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<AnimalResponse> delete(@PathVariable("id") Long id) {

        AnimalResponse animalResponse = this.animalService.delete(id);
        return ResultHelper.success(animalResponse);

    }
}
