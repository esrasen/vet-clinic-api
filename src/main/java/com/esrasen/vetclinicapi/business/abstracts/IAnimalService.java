package com.esrasen.vetclinicapi.business.abstracts;

import com.esrasen.vetclinicapi.dto.request.animal.AnimalSaveRequest;
import com.esrasen.vetclinicapi.dto.request.animal.AnimalUpdateRequest;
import com.esrasen.vetclinicapi.dto.response.CursorResponse;
import com.esrasen.vetclinicapi.dto.response.animal.AnimalResponse;
import com.esrasen.vetclinicapi.entities.Animal;

import java.util.List;

public interface IAnimalService {

    AnimalResponse getAnimalById(Long id);

    CursorResponse<AnimalResponse> cursor(int page, int pageSize);

    List<AnimalResponse> findAllByName(String name);

    AnimalResponse save(AnimalSaveRequest animalSaveRequest);

    AnimalResponse update(AnimalUpdateRequest animalUpdateRequest);

    AnimalResponse delete(Long id);
}
