package com.esrasen.vetclinicapi.dto.response.animal;

import com.esrasen.vetclinicapi.entities.Vaccine;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnimalVaccineResponse {

    private Long id;
    private String name;
    private String species;
    private String breed;
    private String gender;
    private String color;
    private LocalDate dateOfBirth;
    private String customerName;
    private List<String> vaccineNames;
}
