package com.esrasen.vetclinicapi.dto.request.vaccine;

import com.esrasen.vetclinicapi.entities.Animal;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VaccineSaveRequest {

    @NotNull(message = "Aşı adı boş olamaz.")
    private String name;
    @NotNull(message = "Aşı kodu boş olamaz.")
    private String code;
    private LocalDate protectionStartDate;
    private LocalDate protectionFinishDate;
    private Long animalId;
}
