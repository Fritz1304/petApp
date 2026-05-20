package jorgedev.app.petapp.infrastructure.adapter.out.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(
        name = "user_pets",
        uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "pet_id"})
)
public class UserPetEntity {

    @Id
    private String id;

    @Column(name = "user_id", nullable = false)
    private String userId;

    @Column(name = "pet_id", nullable = false)
    private String petId;

    @Column(nullable = false)
    private String role;

    protected UserPetEntity() {
    }

    public UserPetEntity(String id, String userId, String petId, String role) {
        this.id = id;
        this.userId = userId;
        this.petId = petId;
        this.role = role;
    }

    public String getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public String getPetId() {
        return petId;
    }

    public String getRole() {
        return role;
    }
}
