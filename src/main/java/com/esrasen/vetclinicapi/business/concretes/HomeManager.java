package com.esrasen.vetclinicapi.business.concretes;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Getter
@Setter
public class HomeManager {
   @Value("${template.animal.url}")
    private String animalHrefValue;

   @Value("${template.doctor.url}")
    private String doctorHrefValue;

    @Value("${template.customer.url}")
    private String customerHrefValue;

    @Value("${template.vaccine.url}")
    private String vaccineHrefValue;

    @Value("${template.swagger.url}")
    private String swaggerHrefValue;
}
