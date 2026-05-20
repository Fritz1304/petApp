package jorgedev.app.petapp.infrastructure.adapter.out.persistence.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import jorgedev.app.petapp.infrastructure.adapter.out.persistence.entity.UserPetEntity;

public interface SpringDataUserPetRepository extends JpaRepository<UserPetEntity, String> {

    List<UserPetEntity> findAllByUserId(String userId);

    List<UserPetEntity> findAllByPetId(String petId);

    Optional<UserPetEntity> findByUserIdAndPetId(String userId, String petId);

    Optional<UserPetEntity> findByPetIdAndRole(String petId, String role);

    boolean existsByUserIdAndPetId(String userId, String petId);

    void deleteAllByPetId(String petId);
}
