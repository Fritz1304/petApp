package jorgedev.app.petapp.infrastructure.adapter.out.persistence;

import jorgedev.app.petapp.application.ports.out.PetRepositoryPort;
import jorgedev.app.petapp.domain.model.Pet;
import jorgedev.app.petapp.infrastructure.adapter.out.persistence.entity.PetEntity;
import jorgedev.app.petapp.infrastructure.adapter.out.persistence.repository.SpringDataPetRepository;
import org.springframework.stereotype.Component;

@Component
public class PetPersistenceAdapter implements PetRepositoryPort {

    private final SpringDataPetRepository springDataPetRepository;

    public PetPersistenceAdapter(SpringDataPetRepository springDataPetRepository) {
        this.springDataPetRepository = springDataPetRepository;
    }

    @Override
    public Pet save(Pet pet) {
        PetEntity petEntity = new PetEntity(
                pet.getId(),
                pet.getName(),
                pet.getType(),
                pet.getWeightInGrams()
        );

        PetEntity savedPetEntity = springDataPetRepository.save(petEntity);

        return new Pet (
                savedPetEntity.getId(),
                savedPetEntity.getName(),
                savedPetEntity.getType(),
                savedPetEntity.getWeightInGrams()
        );
    }
}
