package jorgedev.app.petapp.application.usecase;

import java.util.UUID;

import org.springframework.stereotype.Service;

import jorgedev.app.petapp.application.dto.CreateUserCommand;
import jorgedev.app.petapp.application.dto.UserResponse;
import jorgedev.app.petapp.application.ports.in.CreateUserUseCase;
import jorgedev.app.petapp.application.ports.out.UserRepositoryPort;
import jorgedev.app.petapp.domain.model.User;

@Service
public class CreateUserService implements CreateUserUseCase {

    private final UserRepositoryPort userRepositoryPort;

    public CreateUserService(UserRepositoryPort userRepositoryPort) {
        this.userRepositoryPort = userRepositoryPort;
    }

    @Override
    public UserResponse createUser(CreateUserCommand command) {
        User user = new User(
                UUID.randomUUID().toString(),
                command.name(),
                command.username(),
                command.password()
        );

        User savedUser = userRepositoryPort.save(user);

        return new UserResponse(
                savedUser.getId(),
                savedUser.getName(),
                savedUser.getUsername()
        );
    }
}
