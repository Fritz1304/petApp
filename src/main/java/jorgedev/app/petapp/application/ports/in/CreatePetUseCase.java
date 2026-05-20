package jorgedev.app.petapp.application.ports.in;

import jorgedev.app.petapp.application.dto.CreatePetCommand;
import jorgedev.app.petapp.application.dto.PetResponse;

public interface CreatePetUseCase {
    PetResponse createPet(CreatePetCommand command);
}
