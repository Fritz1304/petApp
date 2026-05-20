package jorgedev.app.petapp.domain.model;

public class UserPet {

    private final String id;
    private final String userId;
    private final String petId;
    private final String role;

    public UserPet(String id, String userId, String petId, String role) {
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
