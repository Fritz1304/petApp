package jorgedev.app.petapp.application.ports.out;

import java.util.List;
import java.util.Optional;

import jorgedev.app.petapp.domain.model.User;

public interface UserRepositoryPort {

    User save(User user);

    List<User> findAll();

    void delete(String id);

    User update(User user);

    Optional<User> findByUsername(String username);
}
