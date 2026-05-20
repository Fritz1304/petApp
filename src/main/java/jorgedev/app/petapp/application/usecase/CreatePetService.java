package jorgedev.app.petapp.application.usecase;

import jorgedev.app.petapp.application.dto.CreatePetCommand;
import jorgedev.app.petapp.application.dto.PetResponse;
import jorgedev.app.petapp.application.exception.UserNotFoundException;
import jorgedev.app.petapp.application.ports.in.CreatePetUseCase;
import jorgedev.app.petapp.application.ports.out.PetRepositoryPort;
import jorgedev.app.petapp.application.ports.out.UserPetRepositoryPort;
import jorgedev.app.petapp.application.ports.out.UserRepositoryPort;
import jorgedev.app.petapp.domain.model.Pet;
import jorgedev.app.petapp.domain.model.UserPet;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CreatePetService implements CreatePetUseCase {

    private final PetRepositoryPort petRepositoryPort;
    private final UserRepositoryPort userRepositoryPort;
    private final UserPetRepositoryPort userPetRepositoryPort;

    public CreatePetService(
            PetRepositoryPort petRepositoryPort,
            UserRepositoryPort userRepositoryPort,
            UserPetRepositoryPort userPetRepositoryPort
    ) {
        this.petRepositoryPort = petRepositoryPort;
        this.userRepositoryPort = userRepositoryPort;
        this.userPetRepositoryPort = userPetRepositoryPort;
    }

    @Override
    public PetResponse createPet(String username, CreatePetCommand command) {
        var user = userRepositoryPort.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException(username));

        Pet pet = new Pet(
                UUID.randomUUID().toString(),
                command.name(),
                command.type(),
                command.weightInGrams()
        );

        Pet savedPet = petRepositoryPort.save(pet);

        userPetRepositoryPort.save(new UserPet(
                UUID.randomUUID().toString(),
                user.getId(),
                savedPet.getId(),
                "OWNER"
        ));

        return new PetResponse(
                savedPet.getId(),
                savedPet.getName(),
                savedPet.getType(),
                savedPet.getWeightInGrams()
        );
    }
}
