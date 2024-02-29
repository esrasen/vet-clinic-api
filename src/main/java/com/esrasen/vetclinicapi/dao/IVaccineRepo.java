package com.esrasen.vetclinicapi.dao;

import com.esrasen.vetclinicapi.entities.Animal;
import com.esrasen.vetclinicapi.entities.Vaccine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface IVaccineRepo extends JpaRepository<Vaccine, Long> {

    List<Vaccine> findByAnimalId(Long id);


    @Query("SELECT a FROM Animal a JOIN fetch Vaccine v ON a.id = v.animal.id WHERE v.protectionFinishDate BETWEEN :startDate AND :finishDate")
    List<Animal> findAnimalsWithVaccinesExpiringBetween(@Param("startDate") LocalDate startDate, @Param("finishDate") LocalDate finishDate);

    boolean existsByAnimalIdAndNameAndCodeAndProtectionFinishDateAfter(Long animalId, String vaccineName, String vaccineCode, LocalDate protectionFinishDate);

}