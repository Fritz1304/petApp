package jorgedev.app.petapp.application.usecase;

import jorgedev.app.petapp.application.dto.CreatePetCommand;
import jorgedev.app.petapp.application.dto.PetResponse;
import jorgedev.app.petapp.application.ports.in.CreatePetUseCase;
import jorgedev.app.petapp.application.ports.out.PetRepositoryPort;
import jorgedev.app.petapp.domain.model.Pet;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CreatePetService implements CreatePetUseCase {

    private final PetRepositoryPort petRepositoryPort;

    public CreatePetService(PetRepositoryPort petRepositoryPort) {
        this.petRepositoryPort = petRepositoryPort;
    }

    @Override
    public PetResponse createPet(CreatePetCommand command) {

        Pet pet = new Pet(
                UUID.randomUUID().toString(),
                command.name(),
                command.type(),
                command.weightInGrams()
        );

        Pet savedPet = petRepositoryPort.save(pet);

        return new PetResponse(
                savedPet.getId(),
                savedPet.getName(),
                savedPet.getType(),
                savedPet.getWeightInGrams()
        );
    }
}
