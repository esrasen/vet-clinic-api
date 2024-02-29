package com.esrasen.vetclinicapi.dto.request.availableDate;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AvailableDateSaveRequest {

    @NotNull(message = "Tarih boş olamaz.")
    private LocalDate availableDate;
    private Long doctorId;
}
