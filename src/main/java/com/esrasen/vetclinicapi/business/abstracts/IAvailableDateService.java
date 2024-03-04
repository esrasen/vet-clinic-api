package com.esrasen.vetclinicapi.business.abstracts;

import com.esrasen.vetclinicapi.dto.request.availableDate.AvailableDateSaveRequest;
import com.esrasen.vetclinicapi.dto.request.availableDate.AvailableDateUpdateRequest;
import com.esrasen.vetclinicapi.dto.response.CursorResponse;
import com.esrasen.vetclinicapi.dto.response.availableDate.AvailableDateResponse;
import com.esrasen.vetclinicapi.dto.response.customer.CustomerResponse;
import com.esrasen.vetclinicapi.entities.AvailableDate;
import com.esrasen.vetclinicapi.entities.Doctor;
import org.springframework.data.domain.Page;

public interface IAvailableDateService {

    AvailableDateResponse getAvailableDateById(Long id);

    CursorResponse<AvailableDateResponse> cursor(int page, int pageSize);

    AvailableDateResponse save(AvailableDateSaveRequest availableDateSaveRequest);

    AvailableDateResponse update(AvailableDateUpdateRequest availableDateUpdateRequest);

    AvailableDateResponse delete(Long id);
}
