package jorgedev.app.petapp.application.dto;

public record PetResponse(
        String id,
        String name,
        String type,
        Double weightInGrams
) {

}
