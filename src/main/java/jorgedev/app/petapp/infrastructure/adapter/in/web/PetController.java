package jorgedev.app.petapp.infrastructure.adapter.in.web;

import jorgedev.app.petapp.application.dto.CreatePetCommand;
import jorgedev.app.petapp.application.dto.PetResponse;
import jorgedev.app.petapp.application.ports.in.CreatePetUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pets")
public class PetController {

    private final CreatePetUseCase createPetUseCase;

    public PetController(CreatePetUseCase createPetUseCase) {
        this.createPetUseCase = createPetUseCase;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PetResponse createPet(@RequestBody CreatePetCommand command) {
        return createPetUseCase.createPet(command);
    }
}
