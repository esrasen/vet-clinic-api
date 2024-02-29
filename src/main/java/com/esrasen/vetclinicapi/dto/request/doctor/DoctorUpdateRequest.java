package com.esrasen.vetclinicapi.dto.request.doctor;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoctorUpdateRequest {

    @Positive(message = "Id değeri pozitif tam sayı olmalıdır.")
    private Long id;
    @NotNull(message = "Doktor adı boş olamaz.")
    private String name;
    private String phone;
    @Email
    private String mail;
    private String address;
    private String city;
}
