package jorgedev.app.petapp.application.ports.out;

import jorgedev.app.petapp.domain.model.Pet;

import java.util.List;
import java.util.Optional;

public interface PetRepositoryPort {
    Pet save(Pet pet);
    List<Pet> findAllByIds(List<String> petIds);
    Optional<Pet> findById(String petId);
    void delete(String petId);
}
