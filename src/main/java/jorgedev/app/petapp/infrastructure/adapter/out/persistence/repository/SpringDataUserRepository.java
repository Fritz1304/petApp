package jorgedev.app.petapp.infrastructure.adapter.out.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import jorgedev.app.petapp.infrastructure.adapter.out.persistence.entity.UserEntity;

public interface SpringDataUserRepository extends JpaRepository<UserEntity, String> {
}
