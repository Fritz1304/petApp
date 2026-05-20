package jorgedev.app.petapp.application.ports.out;

import jorgedev.app.petapp.domain.model.Pet;

public interface PetRepositoryPort {
    Pet save(Pet pet);
}
