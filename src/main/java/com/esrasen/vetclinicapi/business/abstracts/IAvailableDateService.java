package com.esrasen.vetclinicapi.business.abstracts;

import com.esrasen.vetclinicapi.entities.AvailableDate;
import com.esrasen.vetclinicapi.entities.Doctor;
import org.springframework.data.domain.Page;

public interface IAvailableDateService {

    AvailableDate save(AvailableDate availableDate);
    AvailableDate get(Long id);
    Page<AvailableDate> cursor(int page, int pageSize);
    AvailableDate update(AvailableDate availableDate);
    boolean delete(Long id);
}
