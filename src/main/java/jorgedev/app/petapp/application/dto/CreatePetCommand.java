package jorgedev.app.petapp.application.dto;

public record CreatePetCommand(
        String name,
        String type,
        Double weightInGrams
) {
}
