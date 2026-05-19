package jorgedev.app.petapp.application.ports.out;

import jorgedev.app.petapp.domain.model.User;

public interface UserRepositoryPort {

    User save(User user);
}
