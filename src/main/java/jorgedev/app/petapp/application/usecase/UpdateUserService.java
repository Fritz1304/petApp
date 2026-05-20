package jorgedev.app.petapp.application.usecase;

import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

import jorgedev.app.petapp.application.dto.CreateUserCommand;
import jorgedev.app.petapp.application.dto.UserResponse;
import jorgedev.app.petapp.application.ports.in.UpdateUserUseCase;
import jorgedev.app.petapp.application.ports.out.UserRepositoryPort;
import jorgedev.app.petapp.domain.model.User;

@Service
public class UpdateUserService implements UpdateUserUseCase {

    private final UserRepositoryPort userRepositoryPort;
    private final PasswordEncoder passwordEncoder;

    public UpdateUserService(UserRepositoryPort userRepositoryPort, PasswordEncoder passwordEncoder) {
        this.userRepositoryPort = userRepositoryPort;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserResponse updateUser(String id, CreateUserCommand command) {
        User user = new User(
                id,
                command.name(),
                command.username(),
                passwordEncoder.encode(command.password())
        );

        User updatedUser = userRepositoryPort.update(user);

        return new UserResponse(
                updatedUser.getId(),
                updatedUser.getName(),
                updatedUser.getUsername()
        );
    }
}
