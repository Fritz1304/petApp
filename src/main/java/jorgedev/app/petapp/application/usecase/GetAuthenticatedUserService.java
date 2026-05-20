package jorgedev.app.petapp.application.usecase;

import org.springframework.stereotype.Service;

import jorgedev.app.petapp.application.dto.UserResponse;
import jorgedev.app.petapp.application.exception.UserNotFoundException;
import jorgedev.app.petapp.application.ports.in.GetAuthenticatedUserUseCase;
import jorgedev.app.petapp.application.ports.out.UserRepositoryPort;

@Service
public class GetAuthenticatedUserService implements GetAuthenticatedUserUseCase {

    private final UserRepositoryPort userRepositoryPort;

    public GetAuthenticatedUserService(UserRepositoryPort userRepositoryPort) {
        this.userRepositoryPort = userRepositoryPort;
    }

    @Override
    public UserResponse getAuthenticatedUser(String username) {
        return userRepositoryPort.findByUsername(username)
                .map(user -> new UserResponse(
                        user.getId(),
                        user.getName(),
                        user.getUsername()
                ))
                .orElseThrow(() -> new UserNotFoundException(username));
    }
}
