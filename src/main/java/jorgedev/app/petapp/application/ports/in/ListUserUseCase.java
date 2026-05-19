package jorgedev.app.petapp.application.ports.in;

import jorgedev.app.petapp.application.dto.UserResponse;

import java.util.List;

public interface ListUserUseCase {
    List<UserResponse> listUsers();
}
