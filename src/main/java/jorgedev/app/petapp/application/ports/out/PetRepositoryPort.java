package jorgedev.app.petapp.application.ports.out;

import jorgedev.app.petapp.application.dto.PetResponse;
import jorgedev.app.petapp.domain.model.Pet;

import java.util.List;

public interface PetRepositoryPort {
    Pet save(Pet pet);
    List<Pet> findAll();
}
