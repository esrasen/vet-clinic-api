package com.esrasen.vetclinicapi.dao;

import com.esrasen.vetclinicapi.entities.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDoctorRepo extends JpaRepository<Doctor, Long> {
}
