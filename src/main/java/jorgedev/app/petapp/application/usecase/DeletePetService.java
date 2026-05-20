package jorgedev.app.petapp.application.usecase;

import jorgedev.app.petapp.application.ports.in.DeletePetUseCase;
import jorgedev.app.petapp.application.ports.out.PetRepositoryPort;
import jorgedev.app.petapp.application.ports.out.UserPetRepositoryPort;
import jorgedev.app.petapp.application.ports.out.UserRepositoryPort;
import jorgedev.app.petapp.application.exception.PetAccessDeniedException;
import jorgedev.app.petapp.application.exception.PetNotFoundException;
import jorgedev.app.petapp.application.exception.UserNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class DeletePetService implements DeletePetUseCase {

    private final PetRepositoryPort petRepositoryPort;
    private final UserRepositoryPort userRepositoryPort;
    private final UserPetRepositoryPort userPetRepositoryPort;

    public DeletePetService(
            PetRepositoryPort petRepositoryPort,
            UserRepositoryPort userRepositoryPort,
            UserPetRepositoryPort userPetRepositoryPort
    ) {
        this.petRepositoryPort = petRepositoryPort;
        this.userRepositoryPort = userRepositoryPort;
        this.userPetRepositoryPort = userPetRepositoryPort;
    }

    @Override
    public void deletePet(String username, String petId) {
        var user = userRepositoryPort.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException(username));

        petRepositoryPort.findById(petId)
                .orElseThrow(() -> new PetNotFoundException(petId));

        var ownerRelation = userPetRepositoryPort.findOwnerByPetId(petId)
                .orElseThrow(() -> new PetAccessDeniedException("Pet does not have an owner relation"));

        if (!ownerRelation.getUserId().equals(user.getId())) {
            throw new PetAccessDeniedException("Only the owner can delete this pet");
        }

        userPetRepositoryPort.deleteAllByPetId(petId);
        petRepositoryPort.delete(petId);
    }
}
