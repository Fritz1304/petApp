package jorgedev.app.petapp.infrastructure.adapter.out.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "pets")
public class PetEntity {

    @Id
    private String id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String type;
    @Column(nullable = false)
    private Double weightInGrams;

    protected PetEntity() {}

    public PetEntity(String id, String name, String type, Double weightInGrams) {
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
