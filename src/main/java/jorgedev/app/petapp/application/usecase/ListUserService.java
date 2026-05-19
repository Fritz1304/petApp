package jorgedev.app.petapp.application.usecase;

import java.util.List;

import org.springframework.stereotype.Service;

import jorgedev.app.petapp.application.dto.UserResponse;
import jorgedev.app.petapp.application.ports.in.ListUserUseCase;
import jorgedev.app.petapp.application.ports.out.UserRepositoryPort;

@Service
public class ListUserService implements ListUserUseCase {

    private final UserRepositoryPort userRepositoryPort;

    public ListUserService(UserRepositoryPort userRepositoryPort) {
        this.userRepositoryPort = userRepositoryPort;
    }

    @Override
    public List<UserResponse> listUsers() {
        return userRepositoryPort.findAll()
                .stream()
                .map(user -> new UserResponse(
                        user.getId(),
                        user.getName(),
                        user.getUsername()
                ))
                .toList();
    }
}
