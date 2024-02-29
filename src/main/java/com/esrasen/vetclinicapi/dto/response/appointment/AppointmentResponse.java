package com.esrasen.vetclinicapi.dto.response.appointment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentResponse {

    private Long id;
    private LocalDateTime appointmentDate;
    private String doctorName;
    private String animalName;
}
