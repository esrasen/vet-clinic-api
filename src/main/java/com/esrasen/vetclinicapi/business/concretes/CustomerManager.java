package com.esrasen.vetclinicapi.business.concretes;

import com.esrasen.vetclinicapi.business.abstracts.ICustomerService;
import com.esrasen.vetclinicapi.core.config.modelMapper.IModelMapperService;
import com.esrasen.vetclinicapi.core.exception.DublicateEntityException;
import com.esrasen.vetclinicapi.core.exception.NotFoundException;
import com.esrasen.vetclinicapi.core.utilies.Msg;
import com.esrasen.vetclinicapi.dao.IAnimalRepo;
import com.esrasen.vetclinicapi.dao.ICustomerRepo;
import com.esrasen.vetclinicapi.dto.request.animal.AnimalSaveRequest;
import com.esrasen.vetclinicapi.dto.request.customer.CustomerSaveRequest;
import com.esrasen.vetclinicapi.dto.request.customer.CustomerUpdateRequest;
import com.esrasen.vetclinicapi.dto.response.CursorResponse;
import com.esrasen.vetclinicapi.dto.response.animal.AnimalResponse;
import com.esrasen.vetclinicapi.dto.response.customer.CustomerResponse;
import com.esrasen.vetclinicapi.entities.Animal;
import com.esrasen.vetclinicapi.entities.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerManager implements ICustomerService {

    private final ICustomerRepo customerRepo;
    private final IAnimalRepo animalRepo;
    private final IModelMapperService modelMapper;


    @Override
    public CustomerResponse getCustomerById(Long id) {
        Customer customer = customerRepo.findById(id).orElseThrow(() -> new NotFoundException(Msg.NOT_FOUND));
        return modelMapper.forResponse().map(customer, CustomerResponse.class);
    }

    @Override
    public CursorResponse<CustomerResponse> cursor(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<Customer> customers = customerRepo.findAll(pageable);
        Page<CustomerResponse> cursorResponsePage = customers
                .map(customer -> this.modelMapper.forResponse().map(customer, CustomerResponse.class));

        return new CursorResponse<>(customers.getNumber(), customers.getSize(), customers.getTotalElements(), cursorResponsePage.getContent());

    }

    @Override
    public List<CustomerResponse> findAllByName(String name) {

        List<Customer> customers = customerRepo.findAllByName(name);

        if (customers.isEmpty())
            throw new NotFoundException(Msg.NOT_FOUND);

        return modelMapper.mapList(customers, CustomerResponse.class);
    }

    @Override
    public List<AnimalResponse> findAnimalsByCustomerId(Long id) {
        List<Animal> animals = this.animalRepo.findAnimalsByCustomerId(id);

        if (animals.isEmpty())
            throw new NotFoundException(Msg.NOT_FOUND);
        return modelMapper.mapList(animals, AnimalResponse.class);
    }


    @Override
    public CustomerResponse save(CustomerSaveRequest customerSaveRequest) {
        if (isCustomerAlreadyExist(customerSaveRequest)){
            throw new DublicateEntityException(Msg.ALREADY_EXISTS);
        }
        Customer customer = modelMapper.forRequest().map(customerSaveRequest, Customer.class);
        Customer saveCustomer = customerRepo.save(customer);
        return modelMapper.forResponse().map(saveCustomer, CustomerResponse.class);
    }

    @Override
    public CustomerResponse update(CustomerUpdateRequest customerUpdateRequest) {

        Customer existingCustomer = customerRepo.findById(customerUpdateRequest.getId()).orElseThrow(() -> new NotFoundException(Msg.NOT_FOUND));
        modelMapper.forRequest().map(customerUpdateRequest, existingCustomer);
        Customer updateCustomer = customerRepo.save(existingCustomer);

        return modelMapper.forResponse().map(updateCustomer, CustomerResponse.class);

    }

    @Override
    public CustomerResponse delete(Long id) {
        Customer existingCustomer = customerRepo.findById(id).orElseThrow(() -> new NotFoundException(Msg.NOT_FOUND));
        customerRepo.delete(existingCustomer);

        return modelMapper.forResponse().map(existingCustomer, CustomerResponse.class);
    }


    private boolean isCustomerAlreadyExist(CustomerSaveRequest customerSaveRequest) {
        return customerRepo.findByNameAndPhoneAndEmailAndAddressAndCity(customerSaveRequest.getName(), customerSaveRequest.getPhone(), customerSaveRequest.getEmail(), customerSaveRequest.getAddress(), customerSaveRequest.getCity()).isPresent();
    }
}
