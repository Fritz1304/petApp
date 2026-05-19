package jorgedev.app.petapp.application.usecase;

import org.springframework.stereotype.Service;

import jorgedev.app.petapp.application.ports.in.DeleteUserUseCase;
import jorgedev.app.petapp.application.ports.out.UserRepositoryPort;

@Service
public class DeleteUserService implements DeleteUserUseCase {

    private final UserRepositoryPort userRepositoryPort;

    public DeleteUserService(UserRepositoryPort userRepositoryPort) {
        this.userRepositoryPort = userRepositoryPort;
    }

    @Override
    public void deleteUser(String id) {
        userRepositoryPort.delete(id);
    }
}
