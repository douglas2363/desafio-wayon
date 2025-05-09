package br.com.desafio.Wayon.code.plant.management;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "plant")
public class Plant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long code;

    @Size(max =  10)
    private String description;

    public Plant(String number, String aBeautifulFlower) {
        this.code = Long.valueOf(number);
        this.description = aBeautifulFlower;
    }

    public Plant() {
        // Default constructor

    }

    public Long getId() {
        return code;
    }

    public void setId(Long id) {
        this.code = id;
    }

    public @Size(max = 10) String getDescription() {
        return description;
    }

    public void setDescription(@Size(max = 10) String description) {
        this.description = description;
    }
}
