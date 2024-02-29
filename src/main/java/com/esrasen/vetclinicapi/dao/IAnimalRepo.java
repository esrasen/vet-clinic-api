package com.esrasen.vetclinicapi.dao;

import com.esrasen.vetclinicapi.entities.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IAnimalRepo extends JpaRepository<Animal, Long> {
    List<Animal> findAllByName(String name);

    List<Animal> findAnimalsByCustomerId(Long id);

}
