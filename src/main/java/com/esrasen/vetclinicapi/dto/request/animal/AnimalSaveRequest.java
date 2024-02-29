package com.esrasen.vetclinicapi.dto.request.animal;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnimalSaveRequest {

    @NotNull(message = "Hayvan adı boş olamaz.")
    private String name;
    private String species;
    private String breed;
    private String gender;
    private String color;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfBirth;
    private Long customerId;
}
