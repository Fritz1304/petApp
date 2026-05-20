package jorgedev.app.petapp.domain.model;

public class Pet {

    private String id;
    private String name;
    private String type;
    private Double weightInGrams;

    // Falta agregar las debidas restricciones referentes a seguridada y reglas del negocio
    public Pet(String id, String name, String type, Double weightInGrams) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.weightInGrams = weightInGrams;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public Double getWeightInGrams() {
        return weightInGrams;
    }
}
