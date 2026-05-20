package jorgedev.app.petapp.application.dto;

public record AddUserToPetCommand(
        String username,
        String role
) {
}
