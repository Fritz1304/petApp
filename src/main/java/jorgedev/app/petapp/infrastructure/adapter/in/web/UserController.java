package jorgedev.app.petapp.infrastructure.adapter.in.web;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import jorgedev.app.petapp.application.dto.CreateUserCommand;
import jorgedev.app.petapp.application.dto.UserResponse;
import jorgedev.app.petapp.application.ports.in.CreateUserUseCase;
import jorgedev.app.petapp.application.ports.in.DeleteUserUseCase;
import jorgedev.app.petapp.application.ports.in.ListUserUseCase;

@RestController
@RequestMapping("/users")
public class UserController {

    private final CreateUserUseCase createUserUseCase;
    private final ListUserUseCase listUserUseCase;
    private final DeleteUserUseCase deleteUserUseCase;

    public UserController(
            CreateUserUseCase createUserUseCase,
            ListUserUseCase listUserUseCase,
            DeleteUserUseCase deleteUserUseCase
    ) {
        this.createUserUseCase = createUserUseCase;
        this.listUserUseCase = listUserUseCase;
        this.deleteUserUseCase = deleteUserUseCase;
    }

    @GetMapping
    public List<UserResponse> listUsers() {
        return listUserUseCase.listUsers();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse createUser(@RequestBody CreateUserCommand command) {
        return createUserUseCase.createUser(command);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable String id) {
        deleteUserUseCase.deleteUser(id);
    }
}
