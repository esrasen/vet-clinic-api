package com.esrasen.vetclinicapi.business.concretes;


import com.esrasen.vetclinicapi.business.abstracts.IDoctorService;
import com.esrasen.vetclinicapi.core.config.modelMapper.IModelMapperService;
import com.esrasen.vetclinicapi.core.exception.DublicateEntityException;
import com.esrasen.vetclinicapi.core.exception.NotFoundException;
import com.esrasen.vetclinicapi.core.utilies.Msg;
import com.esrasen.vetclinicapi.dao.IDoctorRepo;
import com.esrasen.vetclinicapi.dto.request.customer.CustomerSaveRequest;
import com.esrasen.vetclinicapi.dto.request.customer.CustomerUpdateRequest;
import com.esrasen.vetclinicapi.dto.request.doctor.DoctorSaveRequest;
import com.esrasen.vetclinicapi.dto.request.doctor.DoctorUpdateRequest;
import com.esrasen.vetclinicapi.dto.response.CursorResponse;
import com.esrasen.vetclinicapi.dto.response.animal.AnimalResponse;
import com.esrasen.vetclinicapi.dto.response.customer.CustomerResponse;
import com.esrasen.vetclinicapi.dto.response.doctor.DoctorResponse;
import com.esrasen.vetclinicapi.entities.Animal;
import com.esrasen.vetclinicapi.entities.Customer;
import com.esrasen.vetclinicapi.entities.Doctor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DoctorManager implements IDoctorService {

    private final IDoctorRepo doctorRepo;
    private final IModelMapperService modelMapper;


    @Override
    public DoctorResponse getDoctorById(Long id) {
        Doctor doctor = doctorRepo.findById(id).orElseThrow(() -> new NotFoundException(Msg.NOT_FOUND));
        return modelMapper.forResponse().map(doctor, DoctorResponse.class);
    }

    @Override
    public CursorResponse<DoctorResponse> cursor(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<Doctor> doctors = doctorRepo.findAll(pageable);
        Page<DoctorResponse> cursorResponsePage = doctors
                .map(doctor -> this.modelMapper.forResponse().map(doctor, DoctorResponse.class));

        return new CursorResponse<>(doctors.getNumber(), doctors.getSize(), doctors.getTotalElements(), cursorResponsePage.getContent());

    }
    @Override
    public DoctorResponse save(DoctorSaveRequest doctorSaveRequest) {
        if (isDoctorAlreadyExist(doctorSaveRequest)) {
            throw new DublicateEntityException(Msg.ALREADY_EXISTS);
        }
        Doctor doctor = modelMapper.forRequest().map(doctorSaveRequest, Doctor.class);
        Doctor saveDoctor = doctorRepo.save(doctor);
        return modelMapper.forResponse().map(saveDoctor, DoctorResponse.class);
    }
    @Override
    public DoctorResponse update(DoctorUpdateRequest doctorUpdateRequest) {

        Doctor existingDoctor = doctorRepo.findById(doctorUpdateRequest.getId()).orElseThrow(() -> new NotFoundException(Msg.NOT_FOUND));
        modelMapper.forRequest().map(doctorUpdateRequest, existingDoctor);
        Doctor updateDoctor = doctorRepo.save(existingDoctor);

        return modelMapper.forResponse().map(updateDoctor, DoctorResponse.class);

    }

    @Override
    public DoctorResponse delete(Long id) {
        Doctor existingDoctor = doctorRepo.findById(id).orElseThrow(() -> new NotFoundException(Msg.NOT_FOUND));
        doctorRepo.delete(existingDoctor);

        return modelMapper.forResponse().map(existingDoctor, DoctorResponse.class);
    }
    private boolean isDoctorAlreadyExist(DoctorSaveRequest doctorSaveRequest) {
        return doctorRepo.findByNameAndPhoneAndMailAndAddressAndCity(doctorSaveRequest.getName(), doctorSaveRequest.getPhone(), doctorSaveRequest.getMail(), doctorSaveRequest.getAddress(), doctorSaveRequest.getCity()).isPresent();
    }
}
