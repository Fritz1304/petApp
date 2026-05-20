package jorgedev.app.petapp.application.ports.in;

import jorgedev.app.petapp.application.dto.UserResponse;

public interface GetAuthenticatedUserUseCase {

    UserResponse getAuthenticatedUser(String username);
}
