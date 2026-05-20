package jorgedev.app.petapp.infrastructure.adapter.in.web;

import jorgedev.app.petapp.application.dto.AddUserToPetCommand;
import jorgedev.app.petapp.application.dto.CreatePetCommand;
import jorgedev.app.petapp.application.dto.PetResponse;
import jorgedev.app.petapp.application.ports.in.AddUserToPetUseCase;
import jorgedev.app.petapp.application.ports.in.CreatePetUseCase;
import jorgedev.app.petapp.application.ports.in.DeletePetUseCase;
import jorgedev.app.petapp.application.ports.in.ListPetsUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/pets")
public class PetController {

    private final CreatePetUseCase createPetUseCase;
    private final ListPetsUseCase listPetsUseCase;
    private final DeletePetUseCase deletePetUseCase;
    private final AddUserToPetUseCase addUserToPetUseCase;

    public PetController(
            CreatePetUseCase createPetUseCase,
            ListPetsUseCase listPetsUseCase,
            DeletePetUseCase deletePetUseCase,
            AddUserToPetUseCase addUserToPetUseCase
    ) {
        this.createPetUseCase = createPetUseCase;
        this.listPetsUseCase = listPetsUseCase;
        this.deletePetUseCase = deletePetUseCase;
        this.addUserToPetUseCase = addUserToPetUseCase;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PetResponse createPet(@RequestBody CreatePetCommand command, Principal principal) {
        return createPetUseCase.createPet(principal.getName(), command);

    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<PetResponse> getPets(Principal principal) {
        return listPetsUseCase.listPets(principal.getName());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePet(@PathVariable String id, Principal principal) {
        deletePetUseCase.deletePet(principal.getName(), id);
    }

    @PostMapping("/{id}/users")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void addUserToPet(
            @PathVariable String id,
            @RequestBody AddUserToPetCommand command,
            Principal principal
    ) {
        addUserToPetUseCase.addUserToPet(principal.getName(), id, command);
    }
}
