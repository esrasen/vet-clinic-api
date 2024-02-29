package com.esrasen.vetclinicapi.business.abstracts;

import com.esrasen.vetclinicapi.entities.Animal;
import com.esrasen.vetclinicapi.entities.Customer;
import org.springframework.data.domain.Page;

import java.time.LocalDate;
import java.util.List;

public interface IAnimalService {


    Animal save(Animal animal);
    Animal get(Long id);
    List<Animal> findAllByName(String name);
    List<Animal> findAnimalsWithVaccinesExpiringBetween(LocalDate startDate, LocalDate finishDate);
    Page<Animal> cursor(int page, int pageSize);
    Animal update(Animal animal);
    boolean delete(Long id);
}
