package jorgedev.app.petapp.application.usecase;

import java.util.UUID;

import org.springframework.stereotype.Service;

import jorgedev.app.petapp.application.dto.AddUserToPetCommand;
import jorgedev.app.petapp.application.exception.PetAccessDeniedException;
import jorgedev.app.petapp.application.exception.PetNotFoundException;
import jorgedev.app.petapp.application.exception.UserNotFoundException;
import jorgedev.app.petapp.application.exception.UserPetAlreadyExistsException;
import jorgedev.app.petapp.application.ports.in.AddUserToPetUseCase;
import jorgedev.app.petapp.application.ports.out.PetRepositoryPort;
import jorgedev.app.petapp.application.ports.out.UserPetRepositoryPort;
import jorgedev.app.petapp.application.ports.out.UserRepositoryPort;
import jorgedev.app.petapp.domain.model.UserPet;

@Service
public class AddUserToPetService implements AddUserToPetUseCase {

    private static final String DEFAULT_ROLE = "COLLABORATOR";

    private final PetRepositoryPort petRepositoryPort;
    private final UserRepositoryPort userRepositoryPort;
    private final UserPetRepositoryPort userPetRepositoryPort;

    public AddUserToPetService(
            PetRepositoryPort petRepositoryPort,
            UserRepositoryPort userRepositoryPort,
            UserPetRepositoryPort userPetRepositoryPort
    ) {
        this.petRepositoryPort = petRepositoryPort;
        this.userRepositoryPort = userRepositoryPort;
        this.userPetRepositoryPort = userPetRepositoryPort;
    }

    @Override
    public void addUserToPet(String authenticatedUsername, String petId, AddUserToPetCommand command) {
        var authenticatedUser = userRepositoryPort.findByUsername(authenticatedUsername)
                .orElseThrow(() -> new UserNotFoundException(authenticatedUsername));

        var targetUser = userRepositoryPort.findByUsername(command.username())
                .orElseThrow(() -> new UserNotFoundException(command.username()));

        petRepositoryPort.findById(petId)
                .orElseThrow(() -> new PetNotFoundException(petId));

        var ownerRelation = userPetRepositoryPort.findOwnerByPetId(petId)
                .orElseThrow(() -> new PetAccessDeniedException("Pet does not have an owner relation"));

        if (!ownerRelation.getUserId().equals(authenticatedUser.getId())) {
            throw new PetAccessDeniedException("Only the owner can link users to this pet");
        }

        if (userPetRepositoryPort.existsByUserIdAndPetId(targetUser.getId(), petId)) {
            throw new UserPetAlreadyExistsException(command.username(), petId);
        }

        String role = command.role() == null || command.role().isBlank()
                ? DEFAULT_ROLE
                : command.role().trim().toUpperCase();

        userPetRepositoryPort.save(new UserPet(
                UUID.randomUUID().toString(),
                targetUser.getId(),
                petId,
                role
        ));
    }
}
