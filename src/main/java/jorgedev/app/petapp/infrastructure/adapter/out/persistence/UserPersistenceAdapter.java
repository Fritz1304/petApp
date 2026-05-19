package jorgedev.app.petapp.infrastructure.adapter.out.persistence;

import org.springframework.stereotype.Component;

import jorgedev.app.petapp.application.ports.out.UserRepositoryPort;
import jorgedev.app.petapp.domain.model.User;
import jorgedev.app.petapp.infrastructure.adapter.out.persistence.entity.UserEntity;
import jorgedev.app.petapp.infrastructure.adapter.out.persistence.repository.SpringDataUserRepository;

@Component
public class UserPersistenceAdapter implements UserRepositoryPort {

    private final SpringDataUserRepository springDataUserRepository;

    public UserPersistenceAdapter(SpringDataUserRepository springDataUserRepository) {
        this.springDataUserRepository = springDataUserRepository;
    }

    @Override
    public User save(User user) {
        UserEntity userEntity = new UserEntity(
                user.getId(),
                user.getName(),
                user.getUsername(),
                user.getPassword()
        );

        UserEntity savedEntity = springDataUserRepository.save(userEntity);

        return new User(
                savedEntity.getId(),
                savedEntity.getName(),
                savedEntity.getUsername(),
                savedEntity.getPassword()
        );
    }
}
