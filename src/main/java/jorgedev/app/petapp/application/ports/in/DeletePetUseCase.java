package jorgedev.app.petapp.application.ports.in;

public interface DeletePetUseCase {

    void deletePet(String username, String petId);
}
