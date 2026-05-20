package jorgedev.app.petapp.infrastructure.adapter.out.persistence.repository;

import jorgedev.app.petapp.infrastructure.adapter.out.persistence.entity.PetEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataPetRepository extends JpaRepository<PetEntity, String> {
}
