package com.esrasen.vetclinicapi.core.config.modelMapper;


import com.esrasen.vetclinicapi.dto.request.animal.AnimalSaveRequest;
import com.esrasen.vetclinicapi.entities.Animal;
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
        return modelMapper;
    }


}
