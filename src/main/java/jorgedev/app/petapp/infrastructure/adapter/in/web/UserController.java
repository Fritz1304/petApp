package jorgedev.app.petapp.infrastructure.adapter.in.web;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import jorgedev.app.petapp.application.dto.CreateUserCommand;
import jorgedev.app.petapp.application.dto.UserResponse;
import jorgedev.app.petapp.application.ports.in.CreateUserUseCase;
import jorgedev.app.petapp.application.ports.in.DeleteUserUseCase;
import jorgedev.app.petapp.application.ports.in.ListUserUseCase;
import jorgedev.app.petapp.application.ports.in.UpdateUserUseCase;

@RestController
@RequestMapping("/users")
public class UserController {

    private final CreateUserUseCase createUserUseCase;
    private final ListUserUseCase listUserUseCase;
    private final DeleteUserUseCase deleteUserUseCase;
    private final UpdateUserUseCase updateUserUseCase;

    public UserController(
            CreateUserUseCase createUserUseCase,
            ListUserUseCase listUserUseCase,
            DeleteUserUseCase deleteUserUseCase,
            UpdateUserUseCase updateUserUseCase
    ) {
        this.createUserUseCase = createUserUseCase;
        this.listUserUseCase = listUserUseCase;
        this.deleteUserUseCase = deleteUserUseCase;
        this.updateUserUseCase = updateUserUseCase;
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

    @PutMapping("/{id}")
    public UserResponse updateUser(@PathVariable String id, @RequestBody CreateUserCommand command) {
        return updateUserUseCase.updateUser(id, command);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable String id) {
        deleteUserUseCase.deleteUser(id);
    }
}
