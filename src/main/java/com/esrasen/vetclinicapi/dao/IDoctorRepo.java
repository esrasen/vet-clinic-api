package com.esrasen.vetclinicapi.dao;

import com.esrasen.vetclinicapi.entities.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IDoctorRepo extends JpaRepository<Doctor, Long> {
    Optional<Doctor> findByNameAndPhoneAndMailAndAddressAndCity(String name, String phone, String email, String address, String city);
}
