package jorgedev.app.petapp.application.ports.out;

import java.util.List;
import java.util.Optional;

import jorgedev.app.petapp.domain.model.UserPet;

public interface UserPetRepositoryPort {

    UserPet save(UserPet userPet);

    List<UserPet> findAllByUserId(String userId);

    Optional<UserPet> findByUserIdAndPetId(String userId, String petId);

    Optional<UserPet> findOwnerByPetId(String petId);

    boolean existsByUserIdAndPetId(String userId, String petId);

    void deleteAllByPetId(String petId);
}
