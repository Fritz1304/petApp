package jorgedev.app.petapp.application.ports.in;

import jorgedev.app.petapp.application.dto.CreateUserCommand;
import jorgedev.app.petapp.application.dto.UserResponse;

public interface CreateUserUseCase {

    UserResponse createUser(CreateUserCommand command);
}
