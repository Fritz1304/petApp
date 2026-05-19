package jorgedev.app.petapp.application.dto;

public record CreateUserCommand(
        String name,
        String username,
        String password
) {
}
