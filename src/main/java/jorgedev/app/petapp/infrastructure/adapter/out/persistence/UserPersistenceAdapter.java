package jorgedev.app.petapp.infrastructure.adapter.out.persistence;

import java.util.List;

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

    @Override
    public List<User> findAll() {
        return springDataUserRepository.findAll()
                .stream()
                .map(userEntity -> new User(
                        userEntity.getId(),
                        userEntity.getName(),
                        userEntity.getUsername(),
                        userEntity.getPassword()
                ))
                .toList();
    }

    @Override
    public void delete(String id) {
        springDataUserRepository.deleteById(id);
    }
}
