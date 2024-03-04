package com.esrasen.vetclinicapi.api;

import com.esrasen.vetclinicapi.business.abstracts.ICustomerService;
import com.esrasen.vetclinicapi.core.result.ResultData;
import com.esrasen.vetclinicapi.core.utilies.ResultHelper;
import com.esrasen.vetclinicapi.dto.request.customer.CustomerSaveRequest;
import com.esrasen.vetclinicapi.dto.request.customer.CustomerUpdateRequest;
import com.esrasen.vetclinicapi.dto.response.CursorResponse;
import com.esrasen.vetclinicapi.dto.response.animal.AnimalResponse;
import com.esrasen.vetclinicapi.dto.response.customer.CustomerResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final ICustomerService customerService;

    @GetMapping("/{id}")
    public ResultData<CustomerResponse> getCustomerById(@PathVariable("id") Long id) {
        CustomerResponse customerResponse = customerService.getCustomerById(id);
        return ResultHelper.success(customerResponse);
    }

    @GetMapping()
    public ResultData<CursorResponse<CustomerResponse>> cursor(
            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
            @RequestParam(name = "pageSize", required = false, defaultValue = "10") int pageSize) {
        CursorResponse<CustomerResponse> customerResponsePage = customerService.cursor(page, pageSize);
        return ResultHelper.success(customerResponsePage);
    }

    @GetMapping("/info")
    public ResultData<List<CustomerResponse>> get(@RequestParam(name = "name") String name) {
        List<CustomerResponse> customerResponses = customerService.findAllByName(name);
        return ResultHelper.success(customerResponses);
    }

    @GetMapping("/{id}/animals")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<List<AnimalResponse>> getAnimalsByCustomerId(@PathVariable("id") Long id) {
        List<AnimalResponse> animalResponses = customerService.findAnimalsByCustomerId(id);
        return ResultHelper.success(animalResponses);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<CustomerResponse> save(@Valid @RequestBody CustomerSaveRequest customerSaveRequest) {
        CustomerResponse saveCustomerResponse = customerService.save(customerSaveRequest);
        return ResultHelper.created(saveCustomerResponse);
    }

    @PutMapping()
    public ResultData<CustomerResponse> update(@Valid @RequestBody CustomerUpdateRequest customerUpdateRequest) {
        CustomerResponse updateCustomerResponse = customerService.update(customerUpdateRequest);
        return ResultHelper.success(updateCustomerResponse);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CustomerResponse> delete(@PathVariable("id") Long id) {
        CustomerResponse customerResponse = customerService.delete(id);
        return ResultHelper.success(customerResponse);
    }
}