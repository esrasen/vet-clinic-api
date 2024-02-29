package com.esrasen.vetclinicapi.business.concretes;

import com.esrasen.vetclinicapi.business.abstracts.IVaccineService;
import com.esrasen.vetclinicapi.core.config.modelMapper.IModelMapperService;
import com.esrasen.vetclinicapi.core.exception.NotFoundException;
import com.esrasen.vetclinicapi.core.result.ResultData;
import com.esrasen.vetclinicapi.core.utilies.Msg;
import com.esrasen.vetclinicapi.core.utilies.ResultHelper;
import com.esrasen.vetclinicapi.dao.IVaccineRepo;
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
    private final IModelMapperService modelMapperService;


    @Override
    public Vaccine save(Vaccine vaccine) {

        Long animalId = vaccine.getAnimal().getId();
        String vaccineName = vaccine.getName();
        String vaccineCode = vaccine.getCode();
        LocalDate protectionStartDate = vaccine.getProtectionStartDate();

        if (existsByAnimalIdAndNameAndCodeAndProtectionFinishDateAfter(animalId, vaccineName, vaccineCode, protectionStartDate)) {
            return this.vaccineRepo.save(vaccine);
        }
        throw new NotFoundException(Msg.ALREADY_EXISTS);
    }

    @Override
    public Vaccine get(Long id) {
        return this.vaccineRepo.findById(id).orElseThrow(() -> new NotFoundException(Msg.NOT_FOUND));
    }

    @Override
    public List<Vaccine> findByAnimalId(Long id) {
        if (vaccineRepo.findByAnimalId(id).isEmpty())
            throw new NotFoundException(Msg.NOT_FOUND);
        return this.vaccineRepo.findByAnimalId(id);
    }

    @Override
    public Page<Vaccine> cursor(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        return this.vaccineRepo.findAll(pageable);
    }

    @Override
    public Vaccine update(Vaccine vaccine) {
        this.get(vaccine.getId());
        return this.vaccineRepo.save(vaccine);
    }

    @Override
    public boolean delete(Long id) {
        Vaccine vaccine = this.get(id);
        this.vaccineRepo.delete(vaccine);
        return true;
    }

    @Override
    public ResultData<List<VaccineResponse>> findByProtectionFinishDateBetween(LocalDate startDate, LocalDate finishDate) {
        List<Vaccine> vaccineList = this.vaccineRepo.findByProtectionFinishDateBetween(startDate, finishDate);

        List<VaccineResponse> vaccineResponseList = vaccineList.stream()
                .map(entity -> modelMapperService.forResponse().map(entity, VaccineResponse.class))
                .collect(Collectors.toList());

        return ResultHelper.success(vaccineResponseList);
    }

    @Override
    public boolean existsByAnimalIdAndNameAndCodeAndProtectionFinishDateAfter(Long animalId, String vaccineName, String vaccineCode, LocalDate protectionFinishDate) {

        return !this.vaccineRepo.existsByAnimalIdAndNameAndCodeAndProtectionFinishDateAfter(animalId, vaccineName, vaccineCode, protectionFinishDate);
    }


}
