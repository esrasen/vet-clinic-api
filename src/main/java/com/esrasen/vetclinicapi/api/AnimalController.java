package com.esrasen.vetclinicapi.api;

import com.esrasen.vetclinicapi.business.abstracts.IAnimalService;
import com.esrasen.vetclinicapi.business.abstracts.ICustomerService;
import com.esrasen.vetclinicapi.business.abstracts.IVaccineService;
import com.esrasen.vetclinicapi.core.config.modelMapper.IModelMapperService;
import com.esrasen.vetclinicapi.core.result.ResultData;
import com.esrasen.vetclinicapi.core.utilies.ResultHelper;
import com.esrasen.vetclinicapi.dto.request.animal.AnimalSaveRequest;
import com.esrasen.vetclinicapi.dto.request.animal.AnimalUpdateRequest;
import com.esrasen.vetclinicapi.dto.response.CursorResponse;
import com.esrasen.vetclinicapi.dto.response.animal.AnimalResponse;
import com.esrasen.vetclinicapi.dto.response.animal.AnimalVaccineResponse;
import com.esrasen.vetclinicapi.entities.Animal;
import com.esrasen.vetclinicapi.entities.Customer;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/animals")
public class AnimalController {

    private final IAnimalService animalService;
    private final IModelMapperService modelMapper;

    private final ICustomerService customerService;
    private final IVaccineService vaccineService;


    public AnimalController(IAnimalService animalService, IModelMapperService modelMapper, ICustomerService customerService, IVaccineService vaccineService) {
        this.animalService = animalService;
        this.modelMapper = modelMapper;
        this.customerService = customerService;
        this.vaccineService = vaccineService;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<AnimalResponse> save(@Valid @RequestBody AnimalSaveRequest animalSaveRequest) {


        Animal saveAnimal = this.modelMapper.forRequest().map(animalSaveRequest, Animal.class);

        Customer customer = this.customerService.get(animalSaveRequest.getCustomerId());
        saveAnimal.setCustomer(customer);


        this.animalService.save(saveAnimal);
        return ResultHelper.created(this.modelMapper.forResponse().map(saveAnimal, AnimalResponse.class));
    }


    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<AnimalResponse> get(@PathVariable("id") Long id) {
        Animal animal = this.animalService.get(id);
        return ResultHelper.success(this.modelMapper.forResponse().map(animal, AnimalResponse.class));
    }

    @GetMapping("/info")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<List<AnimalResponse>> get(@RequestParam(name = "name") String name) {
        List<Animal> animals = this.animalService.findAllByName(name);
        List<AnimalResponse> animalResponses = modelMapper.mapList(animals, AnimalResponse.class);

        return ResultHelper.success(animalResponses);
    }

    @GetMapping("/vaccines/expiring")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<List<AnimalVaccineResponse>> get(@RequestParam(name = "startDate") LocalDate startDate,
                                                       @RequestParam(name = "finishDate") LocalDate finishDate) {
        List<Animal> animals = this.animalService.findAnimalsWithVaccinesExpiringBetween(startDate, finishDate);
        List<AnimalVaccineResponse> animalResponses = modelMapper.mapList(animals, AnimalVaccineResponse.class);

        return ResultHelper.success(animalResponses);
    }


    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CursorResponse<AnimalResponse>> cursor(
            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
            @RequestParam(name = "pageSize", required = false, defaultValue = "10") int pageSize) {

        Page<Animal> animalPage = this.animalService.cursor(page, pageSize);
        Page<AnimalResponse> animalResponsePage = animalPage
                .map(animal -> this.modelMapper.forResponse().map(animal, AnimalResponse.class));

        return ResultHelper.cursor(animalResponsePage);

    }

    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<AnimalResponse> update(@Valid @RequestBody AnimalUpdateRequest animalUpdateRequest) {
        Animal updateAnimal = this.modelMapper.forRequest().map(animalUpdateRequest, Animal.class);
        this.animalService.update(updateAnimal);
        return ResultHelper.success(this.modelMapper.forResponse().map(updateAnimal, AnimalResponse.class));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<AnimalResponse> delete(@PathVariable("id") Long id) {
        Animal animal = this.animalService.get(id);
        this.animalService.delete(id);
        return ResultHelper.success(this.modelMapper.forResponse().map(animal, AnimalResponse.class));
    }
}
