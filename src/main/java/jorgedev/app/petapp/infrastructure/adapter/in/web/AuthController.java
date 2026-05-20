package jorgedev.app.petapp.infrastructure.adapter.in.web;

import java.security.Principal;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jorgedev.app.petapp.application.dto.UserResponse;
import jorgedev.app.petapp.application.ports.in.GetAuthenticatedUserUseCase;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final GetAuthenticatedUserUseCase getAuthenticatedUserUseCase;

    public AuthController(GetAuthenticatedUserUseCase getAuthenticatedUserUseCase) {
        this.getAuthenticatedUserUseCase = getAuthenticatedUserUseCase;
    }

    @GetMapping("/me")
    public UserResponse me(Principal principal) {
        return getAuthenticatedUserUseCase.getAuthenticatedUser(principal.getName());
    }
}
