package jorgedev.app.petapp.application.ports.in;

import jorgedev.app.petapp.application.dto.AddUserToPetCommand;

public interface AddUserToPetUseCase {

    void addUserToPet(String authenticatedUsername, String petId, AddUserToPetCommand command);
}
