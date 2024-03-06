package com.esrasen.vetclinicapi.api;

import com.esrasen.vetclinicapi.business.concretes.HomeManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class HomeController {
    private final HomeManager homeManager;
    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("animalHrefValue", homeManager.getAnimalHrefValue());
        model.addAttribute("doctorHrefValue", homeManager.getDoctorHrefValue());
        model.addAttribute("customerHrefValue", homeManager.getCustomerHrefValue());
        model.addAttribute("vaccineHrefValue", homeManager.getVaccineHrefValue());
        model.addAttribute("swaggerHrefValue", homeManager.getSwaggerHrefValue());
        return "index";
    }
}
