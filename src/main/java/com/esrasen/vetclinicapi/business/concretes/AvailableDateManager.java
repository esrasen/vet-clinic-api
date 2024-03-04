package com.esrasen.vetclinicapi.business.concretes;

import com.esrasen.vetclinicapi.business.abstracts.IAvailableDateService;
import com.esrasen.vetclinicapi.core.config.modelMapper.IModelMapperService;
import com.esrasen.vetclinicapi.core.exception.DublicateEntityException;
import com.esrasen.vetclinicapi.core.exception.NotFoundException;
import com.esrasen.vetclinicapi.core.utilies.Msg;
import com.esrasen.vetclinicapi.dao.IAvailableDateRepo;
import com.esrasen.vetclinicapi.dao.IDoctorRepo;
import com.esrasen.vetclinicapi.dto.request.availableDate.AvailableDateSaveRequest;
import com.esrasen.vetclinicapi.dto.request.availableDate.AvailableDateUpdateRequest;
import com.esrasen.vetclinicapi.dto.response.CursorResponse;
import com.esrasen.vetclinicapi.dto.response.animal.AnimalResponse;
import com.esrasen.vetclinicapi.dto.response.availableDate.AvailableDateResponse;
import com.esrasen.vetclinicapi.dto.response.customer.CustomerResponse;
import com.esrasen.vetclinicapi.entities.Animal;
import com.esrasen.vetclinicapi.entities.AvailableDate;
import com.esrasen.vetclinicapi.entities.Customer;
import com.esrasen.vetclinicapi.entities.Doctor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AvailableDateManager implements IAvailableDateService {

    private final IAvailableDateRepo availableDateRepo;
    private final IDoctorRepo doctorRepo;
    private final IModelMapperService modelMapper;


    @Override
    public AvailableDateResponse getAvailableDateById(Long id) {
        AvailableDate availableDate = availableDateRepo.findById(id).orElseThrow(() -> new NotFoundException(Msg.NOT_FOUND));
        return modelMapper.forResponse().map(availableDate, AvailableDateResponse.class);
    }

    @Override
    public CursorResponse<AvailableDateResponse> cursor(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<AvailableDate> availableDates = availableDateRepo.findAll(pageable);
        Page<AvailableDateResponse> cursorResponsePage = availableDates
                .map(availableDate -> this.modelMapper.forResponse().map(availableDate, AvailableDateResponse.class));

        return new CursorResponse<>(availableDates.getNumber(), availableDates.getSize(), availableDates.getTotalElements(), cursorResponsePage.getContent());
    }

    @Override
    public AvailableDateResponse save(AvailableDateSaveRequest availableDateSaveRequest) {
        if (isAvailableDateAlreadyExist(availableDateSaveRequest)) {
            throw new DublicateEntityException(Msg.ALREADY_EXISTS);
        }
        AvailableDate availableDate = modelMapper.forRequest().map(availableDateSaveRequest, AvailableDate.class);
        Doctor doctor = doctorRepo.findById(availableDateSaveRequest.getDoctorId()).orElseThrow(() -> new NotFoundException(Msg.NOT_FOUND));
        availableDate.setDoctor(doctor);
        AvailableDate saveAvailableDate = availableDateRepo.save(availableDate);
        return modelMapper.forResponse().map(saveAvailableDate, AvailableDateResponse.class);
    }

    @Override
    public AvailableDateResponse update(AvailableDateUpdateRequest availableDateUpdateRequest) {
        AvailableDate availableDate = availableDateRepo.findById(availableDateUpdateRequest.getId()).orElseThrow(() -> new NotFoundException(Msg.NOT_FOUND));

        Doctor doctor = doctorRepo.findById(availableDateUpdateRequest.getDoctorId()).orElseThrow(() -> new NotFoundException(Msg.NOT_FOUND));
        availableDate.setDoctor(doctor);
        modelMapper.forRequest().map(availableDateUpdateRequest, availableDate);
        AvailableDate updateAvailableDate = availableDateRepo.save(availableDate);
        return modelMapper.forResponse().map(updateAvailableDate, AvailableDateResponse.class);

    }

    @Override
    public AvailableDateResponse delete(Long id) {
        AvailableDate availableDate = availableDateRepo.findById(id).orElseThrow(() -> new NotFoundException(Msg.NOT_FOUND));
        availableDateRepo.delete(availableDate);

        return modelMapper.forResponse().map(availableDate, AvailableDateResponse.class);
    }
    private boolean isAvailableDateAlreadyExist(AvailableDateSaveRequest availableDateSaveRequest) {
        return availableDateRepo.findByAvailableDateAndDoctorId(
                availableDateSaveRequest.getAvailableDate(),
                availableDateSaveRequest.getDoctorId())
                .isPresent();
    }
}
