package jorgedev.app.petapp.application.usecase;

import jorgedev.app.petapp.application.dto.PetResponse;
import jorgedev.app.petapp.application.ports.in.ListPetsUseCase;
import jorgedev.app.petapp.application.ports.out.PetRepositoryPort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListPetService implements ListPetsUseCase {

    private final PetRepositoryPort petRepositoryPort;

    public ListPetService(PetRepositoryPort petRepositoryPort) {
        this.petRepositoryPort = petRepositoryPort;
    }

    @Override
    public List<PetResponse> listPets() {
        return petRepositoryPort.findAll()
                .stream()
                .map(pet -> new PetResponse(
                        pet.getId(),
                        pet.getName(),
                        pet.getType(),
                        pet.getWeightInGrams()
                ))
                .toList();
    }
}
