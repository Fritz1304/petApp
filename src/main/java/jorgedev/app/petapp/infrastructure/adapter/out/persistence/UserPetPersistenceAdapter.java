package jorgedev.app.petapp.infrastructure.adapter.out.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import jorgedev.app.petapp.application.ports.out.UserPetRepositoryPort;
import jorgedev.app.petapp.domain.model.UserPet;
import jorgedev.app.petapp.infrastructure.adapter.out.persistence.entity.UserPetEntity;
import jorgedev.app.petapp.infrastructure.adapter.out.persistence.repository.SpringDataUserPetRepository;

@Component
public class UserPetPersistenceAdapter implements UserPetRepositoryPort {

    private static final String OWNER_ROLE = "OWNER";

    private final SpringDataUserPetRepository springDataUserPetRepository;

    public UserPetPersistenceAdapter(SpringDataUserPetRepository springDataUserPetRepository) {
        this.springDataUserPetRepository = springDataUserPetRepository;
    }

    @Override
    public UserPet save(UserPet userPet) {
        UserPetEntity savedEntity = springDataUserPetRepository.save(new UserPetEntity(
                userPet.getId(),
                userPet.getUserId(),
                userPet.getPetId(),
                userPet.getRole()
        ));

        return toDomain(savedEntity);
    }

    @Override
    public List<UserPet> findAllByUserId(String userId) {
        return springDataUserPetRepository.findAllByUserId(userId)
                .stream()
                .map(this::toDomain)
                .toList();
    }

    @Override
    public Optional<UserPet> findByUserIdAndPetId(String userId, String petId) {
        return springDataUserPetRepository.findByUserIdAndPetId(userId, petId)
                .map(this::toDomain);
    }

    @Override
    public Optional<UserPet> findOwnerByPetId(String petId) {
        return springDataUserPetRepository.findByPetIdAndRole(petId, OWNER_ROLE)
                .map(this::toDomain);
    }

    @Override
    public boolean existsByUserIdAndPetId(String userId, String petId) {
        return springDataUserPetRepository.existsByUserIdAndPetId(userId, petId);
    }

    @Override
    public void deleteAllByPetId(String petId) {
        springDataUserPetRepository.deleteAllByPetId(petId);
    }

    private UserPet toDomain(UserPetEntity userPetEntity) {
        return new UserPet(
                userPetEntity.getId(),
                userPetEntity.getUserId(),
                userPetEntity.getPetId(),
                userPetEntity.getRole()
        );
    }
}
