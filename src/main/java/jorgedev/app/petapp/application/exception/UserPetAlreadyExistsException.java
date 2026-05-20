package jorgedev.app.petapp.application.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class UserPetAlreadyExistsException extends RuntimeException {

    public UserPetAlreadyExistsException(String username, String petId) {
        super("User " + username + " is already linked to pet " + petId);
    }
}
