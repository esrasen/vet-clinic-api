package com.esrasen.vetclinicapi.business.concretes;

import com.esrasen.vetclinicapi.business.abstracts.ICustomerService;
import com.esrasen.vetclinicapi.core.exception.NotFoundException;
import com.esrasen.vetclinicapi.core.utilies.Msg;
import com.esrasen.vetclinicapi.dao.IAnimalRepo;
import com.esrasen.vetclinicapi.dao.ICustomerRepo;
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


    @Override
    public Customer save(Customer customer) {
        return this.customerRepo.save(customer);
    }

    @Override
    public Customer get(Long id) {
        return this.customerRepo.findById(id).orElseThrow(() -> new NotFoundException(Msg.NOT_FOUND));
    }

    @Override
    public List<Customer> findAllByName(String name) {
        if (customerRepo.findAllByName(name).isEmpty())
            throw new NotFoundException(Msg.NOT_FOUND);
        return this.customerRepo.findAllByName(name);
    }

    @Override
    public List<Animal> findAnimalsByCustomerId(Long id) {

        if (animalRepo.findAnimalsByCustomerId(id).isEmpty())
            throw new NotFoundException(Msg.NOT_FOUND);
        return this.animalRepo.findAnimalsByCustomerId(id);
    }


    @Override
    public Page<Customer> cursor(int page, int pageSize) {

        Pageable pageable = PageRequest.of(page, pageSize);
        return this.customerRepo.findAll(pageable);
    }

    @Override
    public Customer update(Customer customer) {

        this.get(customer.getId());
        return this.customerRepo.save(customer);
    }

    @Override
    public boolean delete(Long id) {
        Customer customer = this.get(id);
        this.customerRepo.delete(customer);
        return true;
    }
}
