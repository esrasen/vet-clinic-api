package com.esrasen.vetclinicapi.business.abstracts;

import com.esrasen.vetclinicapi.dto.request.animal.AnimalSaveRequest;
import com.esrasen.vetclinicapi.dto.request.customer.CustomerSaveRequest;
import com.esrasen.vetclinicapi.dto.request.customer.CustomerUpdateRequest;
import com.esrasen.vetclinicapi.dto.response.CursorResponse;
import com.esrasen.vetclinicapi.dto.response.animal.AnimalResponse;
import com.esrasen.vetclinicapi.dto.response.customer.CustomerResponse;
import com.esrasen.vetclinicapi.entities.Animal;
import com.esrasen.vetclinicapi.entities.Customer;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ICustomerService {

    CustomerResponse getCustomerById(Long id);

    CursorResponse<CustomerResponse> cursor(int page, int pageSize);

    List<CustomerResponse> findAllByName(String name);

    List<AnimalResponse> findAnimalsByCustomerId(Long id);

    CustomerResponse save(CustomerSaveRequest customerSaveRequest);

    CustomerResponse update(CustomerUpdateRequest customerUpdateRequest);

    CustomerResponse delete(Long id);
}
