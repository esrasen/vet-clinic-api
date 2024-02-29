package com.esrasen.vetclinicapi.business.abstracts;

import com.esrasen.vetclinicapi.entities.Animal;
import com.esrasen.vetclinicapi.entities.Customer;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ICustomerService {


    Customer save(Customer customer);

    Customer get(Long id);

    List<Customer> findAllByName(String name);

    List<Animal> findAnimalsByCustomerId(Long id);

    Page<Customer> cursor(int page, int pageSize);

    Customer update(Customer customer);

    boolean delete(Long id);
}
