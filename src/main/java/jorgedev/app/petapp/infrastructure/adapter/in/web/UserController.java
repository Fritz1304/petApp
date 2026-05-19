package jorgedev.app.petapp.infrastructure.adapter.in.web;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import jorgedev.app.petapp.application.dto.CreateUserCommand;
import jorgedev.app.petapp.application.dto.UserResponse;
import jorgedev.app.petapp.application.ports.in.CreateUserUseCase;

@RestController
@RequestMapping("/users")
public class UserController {

    private final CreateUserUseCase createUserUseCase;

    public UserController(CreateUserUseCase createUserUseCase) {
        this.createUserUseCase = createUserUseCase;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse createUser(@RequestBody CreateUserCommand command) {
        return createUserUseCase.createUser(command);
    }
}
