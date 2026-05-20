package jorgedev.app.petapp.application.ports.in;

import jorgedev.app.petapp.application.dto.PetResponse;

import java.util.List;

public interface ListPetsUseCase {
    List<PetResponse> listPets();
}
