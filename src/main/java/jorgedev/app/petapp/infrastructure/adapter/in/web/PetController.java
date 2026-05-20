package jorgedev.app.petapp.infrastructure.adapter.in.web;

import jorgedev.app.petapp.application.dto.CreatePetCommand;
import jorgedev.app.petapp.application.dto.PetResponse;
import jorgedev.app.petapp.application.ports.in.CreatePetUseCase;
import jorgedev.app.petapp.application.ports.in.DeletePetUseCase;
import jorgedev.app.petapp.application.ports.in.ListPetsUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pets")
public class PetController {

    private final CreatePetUseCase createPetUseCase;
    private final ListPetsUseCase listPetsUseCase;
    private final DeletePetUseCase deletePetUseCase;
    public PetController(CreatePetUseCase createPetUseCase, ListPetsUseCase listPetsUseCase, DeletePetUseCase deletePetUseCase) {
        this.createPetUseCase = createPetUseCase;
        this.listPetsUseCase = listPetsUseCase;
        this.deletePetUseCase = deletePetUseCase;
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

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePet(@PathVariable String id) {
        deletePetUseCase.deletePet(id);
    }
}
