package com.esrasen.vetclinicapi.core.config.modelMapper;


import com.esrasen.vetclinicapi.dto.request.animal.AnimalSaveRequest;
import com.esrasen.vetclinicapi.dto.request.appointment.AppointmentSaveRequest;
import com.esrasen.vetclinicapi.dto.request.availableDate.AvailableDateSaveRequest;
import com.esrasen.vetclinicapi.dto.request.customer.CustomerSaveRequest;
import com.esrasen.vetclinicapi.dto.request.doctor.DoctorSaveRequest;
import com.esrasen.vetclinicapi.dto.request.vaccine.VaccineSaveRequest;
import com.esrasen.vetclinicapi.entities.*;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper getModelMapper() {
        return new ModelMapper();
    }

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.addMappings(new PropertyMap<AnimalSaveRequest, Animal>() {
            @Override
            protected void configure() {
                skip(destination.getId());
            }
        });
        modelMapper.addMappings(new PropertyMap<AvailableDateSaveRequest, AvailableDate>() {
            @Override
            protected void configure() {
                skip(destination.getId());
            }
        });
        modelMapper.addMappings(new PropertyMap<AppointmentSaveRequest, Appointment>() {
            @Override
            protected void configure() {
                skip(destination.getId());
            }
        });
        modelMapper.addMappings(new PropertyMap<CustomerSaveRequest, Customer>() {
            @Override
            protected void configure() {
                skip(destination.getId());
            }
        });
        modelMapper.addMappings(new PropertyMap<DoctorSaveRequest, Doctor>() {
            @Override
            protected void configure() {
                skip(destination.getId());
            }
        });
        modelMapper.addMappings(new PropertyMap<VaccineSaveRequest, Vaccine>() {
            @Override
            protected void configure() {
                skip(destination.getId());
            }
        });
        return modelMapper;
    }


}
