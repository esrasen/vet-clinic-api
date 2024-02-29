package com.esrasen.vetclinicapi.dao;

import com.esrasen.vetclinicapi.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICustomerRepo extends JpaRepository<Customer, Long> {
    List<Customer> findAllByName(String name);


}
