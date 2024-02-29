package com.esrasen.vetclinicapi.dto.response.availableDate;

import com.esrasen.vetclinicapi.entities.Doctor;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AvailableDateResponse {

    private Long id;
    private LocalDate availableDate;
    private String doctorName;
}
