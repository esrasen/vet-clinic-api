package com.esrasen.vetclinicapi.dto.request.availableDate;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AvailableDateUpdateRequest {

    @Positive(message = "Id değeri pozitif tam sayı olmalıdır.")
    private Long id;
    @NotNull(message = "Tarih boş olamaz.")
    private LocalDate availableDate;
    private Long doctorId;
}
