package com.esrasen.vetclinicapi.business.concretes;

import com.esrasen.vetclinicapi.business.abstracts.IAnimalService;
import com.esrasen.vetclinicapi.core.exception.NotFoundException;
import com.esrasen.vetclinicapi.core.utilies.Msg;
import com.esrasen.vetclinicapi.dao.IAnimalRepo;
import com.esrasen.vetclinicapi.dao.IVaccineRepo;
import com.esrasen.vetclinicapi.entities.Animal;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class AnimalManager implements IAnimalService {
    private final IAnimalRepo animalRepo;
    private final IVaccineRepo vaccineRepo;

    public AnimalManager(IAnimalRepo animalRepo, IVaccineRepo vaccineRepo) {
        this.animalRepo = animalRepo;
        this.vaccineRepo = vaccineRepo;
    }

    @Override
    public Animal save(Animal animal) {
        return this.animalRepo.save(animal);
    }

    @Override
    public Animal get(Long id) {
        return this.animalRepo.findById(id).orElseThrow(() -> new NotFoundException(Msg.NOT_FOUND));
    }

    @Override
    public List<Animal> findAllByName(String name) {

        if (animalRepo.findAllByName(name).isEmpty())
            throw new NotFoundException(Msg.NOT_FOUND);
        return this.animalRepo.findAllByName(name);
    }

    @Override
    public List<Animal> findAnimalsWithVaccinesExpiringBetween(LocalDate startDate, LocalDate finishDate) {
        return this.vaccineRepo.findAnimalsWithVaccinesExpiringBetween(startDate, finishDate);
    }


    @Override
    public Page<Animal> cursor(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        return this.animalRepo.findAll(pageable);
    }

    @Override
    public Animal update(Animal animal) {
        this.get(animal.getId());
        return this.animalRepo.save(animal);
    }

    @Override
    public boolean delete(Long id) {
        Animal animal = this.get(id);
        this.animalRepo.delete(animal);
        return true;
    }
}
