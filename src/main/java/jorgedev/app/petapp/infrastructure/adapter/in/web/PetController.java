package jorgedev.app.petapp.infrastructure.adapter.in.web;

import jorgedev.app.petapp.application.dto.CreatePetCommand;
import jorgedev.app.petapp.application.dto.PetResponse;
import jorgedev.app.petapp.application.ports.in.CreatePetUseCase;
import jorgedev.app.petapp.application.ports.in.ListPetsUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pets")
public class PetController {

    private final CreatePetUseCase createPetUseCase;
    private final ListPetsUseCase listPetsUseCase;
    public PetController(CreatePetUseCase createPetUseCase, ListPetsUseCase listPetsUseCase) {
        this.createPetUseCase = createPetUseCase;
        this.listPetsUseCase = listPetsUseCase;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PetResponse createPet(@RequestBody CreatePetCommand command) {
        return createPetUseCase.createPet(command);

    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<PetResponse> getPets() {
        return listPetsUseCase.listPets();
    }
}
