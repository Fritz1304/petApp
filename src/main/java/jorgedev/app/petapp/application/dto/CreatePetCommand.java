package jorgedev.app.petapp.application.dto;

public record CreatePetCommand(
        String id,
        String name,
        String type,
        Double weightInGrams
) {
}
