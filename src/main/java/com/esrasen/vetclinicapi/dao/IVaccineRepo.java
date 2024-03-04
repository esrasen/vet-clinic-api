package com.esrasen.vetclinicapi.dao;

import com.esrasen.vetclinicapi.entities.Animal;
import com.esrasen.vetclinicapi.entities.Vaccine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface IVaccineRepo extends JpaRepository<Vaccine, Long> {

    List<Vaccine> findByAnimalId(Long id);


    boolean existsByAnimalIdAndNameAndCodeAndProtectionFinishDateAfter(Long animalId, String vaccineName, String vaccineCode, LocalDate protectionFinishDate);

    List<Vaccine> findByProtectionFinishDateBetween(LocalDate startDate, LocalDate finishDate);
    Optional<Vaccine> findByNameAndCodeAndProtectionStartDateAndProtectionFinishDateAndAnimalId(
            String name, String code, LocalDate protectionStartDate, LocalDate protectionFinishDate, Long animalId);
}
