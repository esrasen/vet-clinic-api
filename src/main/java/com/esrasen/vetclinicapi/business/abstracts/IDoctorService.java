package com.esrasen.vetclinicapi.business.abstracts;

import com.esrasen.vetclinicapi.dto.request.animal.AnimalSaveRequest;
import com.esrasen.vetclinicapi.dto.request.animal.AnimalUpdateRequest;
import com.esrasen.vetclinicapi.dto.request.doctor.DoctorSaveRequest;
import com.esrasen.vetclinicapi.dto.request.doctor.DoctorUpdateRequest;
import com.esrasen.vetclinicapi.dto.response.CursorResponse;
import com.esrasen.vetclinicapi.dto.response.animal.AnimalResponse;
import com.esrasen.vetclinicapi.dto.response.customer.CustomerResponse;
import com.esrasen.vetclinicapi.dto.response.doctor.DoctorResponse;
import com.esrasen.vetclinicapi.entities.Doctor;
import org.springframework.data.domain.Page;

public interface IDoctorService {


    DoctorResponse getDoctorById(Long id);

    CursorResponse<DoctorResponse> cursor(int page, int pageSize);

    DoctorResponse save(DoctorSaveRequest doctorSaveRequest);

    DoctorResponse update(DoctorUpdateRequest doctorUpdateRequest);

    DoctorResponse delete(Long id);
}
