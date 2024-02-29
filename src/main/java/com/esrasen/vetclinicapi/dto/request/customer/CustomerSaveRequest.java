package com.esrasen.vetclinicapi.dto.request.customer;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerSaveRequest {

    @NotNull(message = "Müşteri adı boş olamaz.")
    @NotEmpty
    private String name;
    private String phone;
    @Email
    private String email;
    private String address;
    private String city;
}
