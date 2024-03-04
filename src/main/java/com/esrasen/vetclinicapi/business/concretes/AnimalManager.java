package com.esrasen.vetclinicapi.business.concretes;

import com.esrasen.vetclinicapi.business.abstracts.IAnimalService;
import com.esrasen.vetclinicapi.core.config.modelMapper.IModelMapperService;
import com.esrasen.vetclinicapi.core.exception.DublicateEntityException;
import com.esrasen.vetclinicapi.core.exception.NotFoundException;
import com.esrasen.vetclinicapi.core.utilies.Msg;
import com.esrasen.vetclinicapi.dao.IAnimalRepo;
import com.esrasen.vetclinicapi.dao.ICustomerRepo;
import com.esrasen.vetclinicapi.dto.request.animal.AnimalSaveRequest;
import com.esrasen.vetclinicapi.dto.request.animal.AnimalUpdateRequest;
import com.esrasen.vetclinicapi.dto.response.CursorResponse;
import com.esrasen.vetclinicapi.dto.response.animal.AnimalResponse;
import com.esrasen.vetclinicapi.entities.Animal;
import com.esrasen.vetclinicapi.entities.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AnimalManager implements IAnimalService {
    private final IAnimalRepo animalRepo;
    private final ICustomerRepo customerRepo;
    private final IModelMapperService modelMapper;

    @Override
    public AnimalResponse getAnimalById(Long id) {
        Animal animal = animalRepo.findById(id).orElseThrow(() -> new NotFoundException(Msg.NOT_FOUND));
        return modelMapper.forResponse().map(animal, AnimalResponse.class);
    }

    @Override
    public CursorResponse<AnimalResponse> cursor(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<Animal> animals = animalRepo.findAll(pageable);
        Page<AnimalResponse> animalResponsePage = animals
                .map(animal -> modelMapper.forResponse().map(animal, AnimalResponse.class));

        return new CursorResponse<>(animals.getNumber(), animals.getSize(), animals.getTotalElements(), animalResponsePage.getContent());

    }

    @Override
    public List<AnimalResponse> findAllByName(String name) {

        List<Animal> animals = animalRepo.findAllByName(name);

        if (animals.isEmpty())
            throw new NotFoundException(Msg.NOT_FOUND);

        return modelMapper.mapList(animals, AnimalResponse.class);
    }

    @Override
    public AnimalResponse save(AnimalSaveRequest animalSaveRequest) {
        if (isAnimalAlreadyExist(animalSaveRequest)){
            throw new DublicateEntityException(Msg.ALREADY_EXISTS);
        }
        Animal animal = modelMapper.forRequest().map(animalSaveRequest, Animal.class);
        Customer customer = customerRepo.findById(animalSaveRequest.getCustomerId()).orElseThrow(() -> new NotFoundException(Msg.NOT_FOUND));
        animal.setCustomer(customer);
        Animal saveAnimal = animalRepo.save(animal);
        return modelMapper.forResponse().map(saveAnimal, AnimalResponse.class);
    }


    @Override
    public AnimalResponse update(AnimalUpdateRequest animalUpdateRequest) {

        Animal existingAnimal = animalRepo.findById(animalUpdateRequest.getId()).orElseThrow(() -> new NotFoundException(Msg.NOT_FOUND));

        Customer customer = customerRepo.findById(animalUpdateRequest.getCustomerId()).orElseThrow(() -> new NotFoundException(Msg.NOT_FOUND));
        existingAnimal.setCustomer(customer);
        modelMapper.forRequest().map(animalUpdateRequest, existingAnimal);

        Animal updateAnimal = animalRepo.save(existingAnimal);
        return modelMapper.forResponse().map(updateAnimal, AnimalResponse.class);
    }

    @Override
    public AnimalResponse delete(Long id) {
        Animal existingAnimal = animalRepo.findById(id).orElseThrow(() -> new NotFoundException(Msg.NOT_FOUND));
        animalRepo.delete(existingAnimal);

        return modelMapper.forResponse().map(existingAnimal, AnimalResponse.class);
    }

    private boolean isAnimalAlreadyExist(AnimalSaveRequest animalSaveRequest) {
        return animalRepo.findByNameAndSpeciesAndBreedAndGenderAndColorAndDateOfBirthAndCustomerId(
                animalSaveRequest.getName(),
                animalSaveRequest.getSpecies(),
                animalSaveRequest.getBreed(),
                animalSaveRequest.getGender(),
                animalSaveRequest.getColor(),
                animalSaveRequest.getDateOfBirth(),
                animalSaveRequest.getCustomerId()).isPresent();
    }
}
