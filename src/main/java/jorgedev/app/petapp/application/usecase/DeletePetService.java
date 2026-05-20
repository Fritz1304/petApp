package jorgedev.app.petapp.application.usecase;

import jorgedev.app.petapp.application.ports.in.DeletePetUseCase;
import jorgedev.app.petapp.application.ports.out.PetRepositoryPort;
import org.springframework.stereotype.Service;

@Service
public class DeletePetService implements DeletePetUseCase {

    private final PetRepositoryPort petRepositoryPort;

    public DeletePetService(PetRepositoryPort petRepositoryPort) {
        this.petRepositoryPort = petRepositoryPort;
    }

    @Override
    public void deletePet(String petId) {
        petRepositoryPort.delete(petId);
    }
}
