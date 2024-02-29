package com.esrasen.vetclinicapi.dao;

import com.esrasen.vetclinicapi.entities.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface IAppointmentRepo extends JpaRepository<Appointment, Long> {
    List<Appointment> findByDoctorIdAndAppointmentDateBetween(Long doctorId, LocalDateTime startDateTime, LocalDateTime finishDateTime);
    List<Appointment> findByAnimalIdAndAppointmentDateBetween(Long animalId, LocalDateTime startDateTime, LocalDateTime finishDateTime);

   }
