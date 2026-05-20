package jorgedev.app.petapp.application.usecase;

import jorgedev.app.petapp.application.dto.PetResponse;
import jorgedev.app.petapp.application.exception.UserNotFoundException;
import jorgedev.app.petapp.application.ports.in.ListPetsUseCase;
import jorgedev.app.petapp.application.ports.out.PetRepositoryPort;
import jorgedev.app.petapp.application.ports.out.UserPetRepositoryPort;
import jorgedev.app.petapp.application.ports.out.UserRepositoryPort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListPetService implements ListPetsUseCase {

    private final PetRepositoryPort petRepositoryPort;
    private final UserRepositoryPort userRepositoryPort;
    private final UserPetRepositoryPort userPetRepositoryPort;

    public ListPetService(
            PetRepositoryPort petRepositoryPort,
            UserRepositoryPort userRepositoryPort,
            UserPetRepositoryPort userPetRepositoryPort
    ) {
        this.petRepositoryPort = petRepositoryPort;
        this.userRepositoryPort = userRepositoryPort;
        this.userPetRepositoryPort = userPetRepositoryPort;
    }

    @Override
    public List<PetResponse> listPets(String username) {
        var user = userRepositoryPort.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException(username));

        List<String> petIds = userPetRepositoryPort.findAllByUserId(user.getId())
                .stream()
                .map(userPet -> userPet.getPetId())
                .toList();

        return petRepositoryPort.findAllByIds(petIds)
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
