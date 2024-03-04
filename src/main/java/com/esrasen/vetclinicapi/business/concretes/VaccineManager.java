package com.esrasen.vetclinicapi.business.concretes;

import com.esrasen.vetclinicapi.business.abstracts.IVaccineService;
import com.esrasen.vetclinicapi.core.config.modelMapper.IModelMapperService;
import com.esrasen.vetclinicapi.core.exception.DublicateEntityException;
import com.esrasen.vetclinicapi.core.exception.NotFoundException;
import com.esrasen.vetclinicapi.core.result.ResultData;
import com.esrasen.vetclinicapi.core.utilies.Msg;
import com.esrasen.vetclinicapi.core.utilies.ResultHelper;
import com.esrasen.vetclinicapi.dao.IVaccineRepo;
import com.esrasen.vetclinicapi.dto.request.vaccine.VaccineSaveRequest;
import com.esrasen.vetclinicapi.dto.request.vaccine.VaccineUpdateRequest;
import com.esrasen.vetclinicapi.dto.response.CursorResponse;
import com.esrasen.vetclinicapi.dto.response.vaccine.VaccineResponse;
import com.esrasen.vetclinicapi.entities.Vaccine;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VaccineManager implements IVaccineService {
    private final IVaccineRepo vaccineRepo;
    private final IModelMapperService modelMapper;


    @Override
    public VaccineResponse getVaccineById(Long id) {
        Vaccine vaccine = vaccineRepo.findById(id).orElseThrow(() -> new NotFoundException(Msg.NOT_FOUND));
        return modelMapper.forResponse().map(vaccine, VaccineResponse.class);
    }

    @Override
    public List<VaccineResponse> findByAnimalId(Long id) {
        List<Vaccine> vaccines = vaccineRepo.findByAnimalId(id);
        if (vaccines.isEmpty())
            throw new NotFoundException(Msg.NOT_FOUND);
        return modelMapper.mapList(vaccines, VaccineResponse.class);
    }

    @Override
    public ResultData<List<VaccineResponse>> findByProtectionFinishDateBetween(LocalDate startDate, LocalDate finishDate) {
        List<Vaccine> vaccineList = this.vaccineRepo.findByProtectionFinishDateBetween(startDate, finishDate);

        if (vaccineList.isEmpty()) {
            throw new NotFoundException(Msg.NOT_FOUND);
        }

        List<VaccineResponse> vaccineResponseList = vaccineList.stream()
                .map(entity -> modelMapper.forResponse().map(entity, VaccineResponse.class))
                .collect(Collectors.toList());

        return ResultHelper.success(vaccineResponseList);
    }

    @Override
    public boolean existsByAnimalIdAndNameAndCodeAndProtectionFinishDateAfter(Long animalId, String vaccineName, String vaccineCode, LocalDate protectionFinishDate) {

        return !this.vaccineRepo.existsByAnimalIdAndNameAndCodeAndProtectionFinishDateAfter(animalId, vaccineName, vaccineCode, protectionFinishDate);
    }

    @Override
    public CursorResponse<VaccineResponse> cursor(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<Vaccine> vaccines = vaccineRepo.findAll(pageable);
        Page<VaccineResponse> vaccineResponsePage = vaccines
                .map(vaccine -> this.modelMapper.forResponse().map(vaccine, VaccineResponse.class));

        return new CursorResponse<>(vaccines.getNumber(), vaccines.getSize(), vaccines.getTotalElements(), vaccineResponsePage.getContent());

    }

    @Override
    public VaccineResponse save(VaccineSaveRequest vaccineSaveRequest) {
        if (isVaccineAlreadyExist(vaccineSaveRequest)) {
            throw new DublicateEntityException(Msg.ALREADY_EXISTS);
        }

        Vaccine vaccine = modelMapper.forRequest().map(vaccineSaveRequest, Vaccine.class);
        Long animalId = vaccine.getAnimal().getId();
        String vaccineName = vaccine.getName();
        String vaccineCode = vaccine.getCode();
        LocalDate protectionStartDate = vaccine.getProtectionStartDate();

        if (existsByAnimalIdAndNameAndCodeAndProtectionFinishDateAfter(animalId, vaccineName, vaccineCode, protectionStartDate)) {
            Vaccine saveVaccine = vaccineRepo.save(vaccine);
            return modelMapper.forResponse().map(saveVaccine, VaccineResponse.class);
        }
        throw new NotFoundException(Msg.ALREADY_EXISTS_VACCINE);
    }

    @Override
    public VaccineResponse update(VaccineUpdateRequest vaccineUpdateRequest) {
        Vaccine existingVaccine = vaccineRepo.findById(vaccineUpdateRequest.getId()).orElseThrow(() -> new NotFoundException(Msg.NOT_FOUND));
        modelMapper.forRequest().map(vaccineUpdateRequest, existingVaccine);
        Vaccine updateVaccine = vaccineRepo.save(existingVaccine);

        return modelMapper.forResponse().map(updateVaccine, VaccineResponse.class);

    }

    @Override
    public VaccineResponse delete(Long id) {

        Vaccine existingVaccine = vaccineRepo.findById(id).orElseThrow(() -> new NotFoundException(Msg.NOT_FOUND));
        vaccineRepo.delete(existingVaccine);

        return modelMapper.forResponse().map(existingVaccine, VaccineResponse.class);
    }
    private boolean isVaccineAlreadyExist(VaccineSaveRequest vaccineSaveRequest) {
        return vaccineRepo.findByNameAndCodeAndProtectionStartDateAndProtectionFinishDateAndAnimalId(
                vaccineSaveRequest.getName(),
                vaccineSaveRequest.getCode(),
                vaccineSaveRequest.getProtectionStartDate(),
                vaccineSaveRequest.getProtectionFinishDate(),
                vaccineSaveRequest.getAnimalId())
                .isPresent();
    }
}
