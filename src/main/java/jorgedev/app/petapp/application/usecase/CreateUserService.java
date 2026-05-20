package jorgedev.app.petapp.application.usecase;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

import jorgedev.app.petapp.application.dto.CreateUserCommand;
import jorgedev.app.petapp.application.exception.UsernameAlreadyExistsException;
import jorgedev.app.petapp.application.dto.UserResponse;
import jorgedev.app.petapp.application.ports.in.CreateUserUseCase;
import jorgedev.app.petapp.application.ports.out.UserRepositoryPort;
import jorgedev.app.petapp.domain.model.User;

@Service
public class CreateUserService implements CreateUserUseCase {

    private final UserRepositoryPort userRepositoryPort;
    private final PasswordEncoder passwordEncoder;

    public CreateUserService(UserRepositoryPort userRepositoryPort, PasswordEncoder passwordEncoder) {
        this.userRepositoryPort = userRepositoryPort;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserResponse createUser(CreateUserCommand command) {
        if (userRepositoryPort.findByUsername(command.username()).isPresent()) {
            throw new UsernameAlreadyExistsException(command.username());
        }

        User user = new User(
                UUID.randomUUID().toString(),
                command.name(),
                command.username(),
                passwordEncoder.encode(command.password())
        );

        User savedUser = userRepositoryPort.save(user);

        return new UserResponse(
                savedUser.getId(),
                savedUser.getName(),
                savedUser.getUsername()
        );
    }
}
