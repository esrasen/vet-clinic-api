package com.esrasen.vetclinicapi.dto.request.customer;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerUpdateRequest {

    @Positive(message = "Id değeri pozitif tam sayı olmalıdır.")
    private Long id;
    @NotNull(message = "Müşteri adı boş olamaz.")
    private String name;
    private String phone;
    @Email
    private String email;
    private String address;
    private String city;
}
