package com.esrasen.vetclinicapi.dto.request.vaccine;

import com.esrasen.vetclinicapi.entities.Animal;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VaccineUpdateRequest {


    @Positive(message = "Id değeri pozitif tam sayı olmalıdır.")
    private Long id;
    @NotNull(message = "Aşı adı boş olamaz.")
    private String name;
    @NotNull(message = "Aşı kodu boş olamaz.")
    private String code;
    private LocalDate protectionStartDate;
    private LocalDate protectionFinishDate;
    private Long animalId;
}
