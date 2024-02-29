package com.esrasen.vetclinicapi.api;

import com.esrasen.vetclinicapi.business.abstracts.ICustomerService;
import com.esrasen.vetclinicapi.core.config.modelMapper.IModelMapperService;
import com.esrasen.vetclinicapi.core.result.Result;
import com.esrasen.vetclinicapi.core.result.ResultData;
import com.esrasen.vetclinicapi.core.utilies.ResultHelper;
import com.esrasen.vetclinicapi.dto.request.customer.CustomerSaveRequest;
import com.esrasen.vetclinicapi.dto.request.customer.CustomerUpdateRequest;
import com.esrasen.vetclinicapi.dto.response.CursorResponse;
import com.esrasen.vetclinicapi.dto.response.animal.AnimalResponse;
import com.esrasen.vetclinicapi.dto.response.customer.CustomerResponse;
import com.esrasen.vetclinicapi.entities.Animal;
import com.esrasen.vetclinicapi.entities.Customer;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {
    private final ICustomerService customerService;
    private final IModelMapperService modelMapper;

    public CustomerController(ICustomerService customerService, IModelMapperService modelMapper) {
        this.customerService = customerService;
        this.modelMapper = modelMapper;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<CustomerResponse> save(@Valid @RequestBody CustomerSaveRequest customerSaveRequest) {

        Customer saveCustomer = this.modelMapper.forRequest().map(customerSaveRequest, Customer.class);
        this.customerService.save(saveCustomer);
        return ResultHelper.created(this.modelMapper.forResponse().map(saveCustomer, CustomerResponse.class));
    }


    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CustomerResponse> get(@PathVariable("id") Long id) {
        Customer customer = this.customerService.get(id);
        return ResultHelper.success(this.modelMapper.forResponse().map(customer, CustomerResponse.class));
    }

    @GetMapping("/info")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<List<CustomerResponse>> get(@RequestParam(name = "name") String name) {
        List<Customer> customers = this.customerService.findAllByName(name);
        List<CustomerResponse> customerResponses = modelMapper.mapList(customers, CustomerResponse.class);

        return ResultHelper.success(customerResponses);
    }


    @GetMapping("/{id}/animals")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<List<AnimalResponse>> getAnimalsByCustomerId(@PathVariable("id") Long id) {
        List<Animal> animals = this.customerService.findAnimalsByCustomerId(id);
        List<AnimalResponse> animalResponses = modelMapper.mapList(animals, AnimalResponse.class);

        return ResultHelper.success(animalResponses);
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CursorResponse<CustomerResponse>> cursor(
            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
            @RequestParam(name = "pageSize", required = false, defaultValue = "10") int pageSize) {

        Page<Customer> customerPage = this.customerService.cursor(page, pageSize);
        Page<CustomerResponse> customerResponsePage = customerPage
                .map(customer -> this.modelMapper.forResponse().map(customer, CustomerResponse.class));

        return ResultHelper.cursor(customerResponsePage);
    }


    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CustomerResponse> update(@Valid @RequestBody CustomerUpdateRequest customerUpdateRequest) {
        Customer updateCustomer = this.modelMapper.forRequest().map(customerUpdateRequest, Customer.class);
        this.customerService.update(updateCustomer);
        return ResultHelper.success(this.modelMapper.forResponse().map(updateCustomer, CustomerResponse.class));
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@PathVariable("id") Long id) {
        this.customerService.delete(id);
        return ResultHelper.ok();
    }


}
