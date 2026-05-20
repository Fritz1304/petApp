package jorgedev.app.petapp.application.usecase;

import org.springframework.stereotype.Service;

import jorgedev.app.petapp.application.dto.CreateUserCommand;
import jorgedev.app.petapp.application.dto.UserResponse;
import jorgedev.app.petapp.application.ports.in.UpdateUserUseCase;
import jorgedev.app.petapp.application.ports.out.UserRepositoryPort;
import jorgedev.app.petapp.domain.model.User;

@Service
public class UpdateUserService implements UpdateUserUseCase {

    private final UserRepositoryPort userRepositoryPort;

    public UpdateUserService(UserRepositoryPort userRepositoryPort) {
        this.userRepositoryPort = userRepositoryPort;
    }

    @Override
    public UserResponse updateUser(String id, CreateUserCommand command) {
        User user = new User(
                id,
                command.name(),
                command.username(),
                command.password()
        );

        User updatedUser = userRepositoryPort.update(user);

        return new UserResponse(
                updatedUser.getId(),
                updatedUser.getName(),
                updatedUser.getUsername()
        );
    }
}
