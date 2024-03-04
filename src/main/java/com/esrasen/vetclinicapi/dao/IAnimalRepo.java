package com.esrasen.vetclinicapi.dao;

import com.esrasen.vetclinicapi.entities.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface IAnimalRepo extends JpaRepository<Animal, Long> {
    List<Animal> findAllByName(String name);

    List<Animal> findAnimalsByCustomerId(Long id);

    Optional<Animal> findByNameAndSpeciesAndBreedAndGenderAndColorAndDateOfBirthAndCustomerId(
            String name, String species, String breed, String gender, String color, LocalDate dateOfBirth, Long customerId);

}
