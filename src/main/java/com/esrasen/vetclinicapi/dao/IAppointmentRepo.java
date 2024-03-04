package com.esrasen.vetclinicapi.dao;

import com.esrasen.vetclinicapi.entities.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface IAppointmentRepo extends JpaRepository<Appointment, Long> {
    List<Appointment> findByDoctorIdAndAppointmentDateBetween(Long doctorId, LocalDateTime startDateTime, LocalDateTime finishDateTime);
    List<Appointment> findByAnimalIdAndAppointmentDateBetween(Long animalId, LocalDateTime startDateTime, LocalDateTime finishDateTime);

    Optional<Appointment> findByAppointmentDateAndDoctorIdAndAnimalId(LocalDateTime appointmentDate, Long doctorId, Long animalId);
   }
