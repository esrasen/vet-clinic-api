package com.esrasen.vetclinicapi.business.abstracts;

import com.esrasen.vetclinicapi.entities.Animal;
import com.esrasen.vetclinicapi.entities.Doctor;
import com.esrasen.vetclinicapi.entities.Vaccine;
import org.springframework.data.domain.Page;

import java.time.LocalDate;
import java.util.List;

public interface IVaccineService {
    Vaccine save(Vaccine vaccine);
    Vaccine get(Long id);
    List<Vaccine> findByAnimalId(Long id);
    Page<Vaccine> cursor(int page, int pageSize);
    Vaccine update(Vaccine vaccine);
    boolean delete(Long id);
    boolean existsByAnimalIdAndNameAndCodeAndProtectionFinishDateAfter(Long animalId,String vaccineName, String vaccineCode, LocalDate protectionFinishDate);

}
