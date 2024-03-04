package com.esrasen.vetclinicapi.business.abstracts;

import com.esrasen.vetclinicapi.core.result.ResultData;
import com.esrasen.vetclinicapi.dto.request.vaccine.VaccineSaveRequest;
import com.esrasen.vetclinicapi.dto.request.vaccine.VaccineUpdateRequest;
import com.esrasen.vetclinicapi.dto.response.CursorResponse;
import com.esrasen.vetclinicapi.dto.response.vaccine.VaccineResponse;
import com.esrasen.vetclinicapi.entities.Animal;
import com.esrasen.vetclinicapi.entities.Doctor;
import com.esrasen.vetclinicapi.entities.Vaccine;
import org.springframework.data.domain.Page;

import java.time.LocalDate;
import java.util.List;

public interface IVaccineService {

    VaccineResponse getVaccineById(Long id);

    List<VaccineResponse> findByAnimalId(Long id);

    ResultData<List<VaccineResponse>> findByProtectionFinishDateBetween(LocalDate startDate, LocalDate finishDate);

    boolean existsByAnimalIdAndNameAndCodeAndProtectionFinishDateAfter(Long animalId, String vaccineName, String vaccineCode, LocalDate protectionFinishDate);

    CursorResponse<VaccineResponse> cursor(int page, int pageSize);

    VaccineResponse save(VaccineSaveRequest vaccineSaveRequest);

    VaccineResponse update(VaccineUpdateRequest vaccineUpdateRequest);

    VaccineResponse delete(Long id);

}
