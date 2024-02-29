package com.esrasen.vetclinicapi.business.abstracts;

import com.esrasen.vetclinicapi.entities.Doctor;
import org.springframework.data.domain.Page;

public interface IDoctorService {

    Doctor save(Doctor doctor);

    Doctor get(Long id);

    Page<Doctor> cursor(int page, int pageSize);

    Doctor update(Doctor doctor);

    boolean delete(Long id);
}
