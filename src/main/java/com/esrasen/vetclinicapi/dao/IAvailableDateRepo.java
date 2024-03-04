package com.esrasen.vetclinicapi.dao;

import com.esrasen.vetclinicapi.entities.AvailableDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface IAvailableDateRepo extends JpaRepository<AvailableDate, Long> {
    Optional<AvailableDate> findByDoctorIdAndAvailableDate(Long doctorId, LocalDate availableDate);

    Optional<AvailableDate> findByAvailableDateAndDoctorId(LocalDate availableDate, Long doctorId);
}
