package br.com.desafio.Wayon.code.plant.management;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class PlantDTO {

    @NotNull
    private Long code;

   @Size(max = 10)
    private String description;

    public @NotNull Long getCode() {
        return code;
    }

    public void setCode(@NotNull Long code) {
        this.code = code;
    }

    public @Size(max = 10) String getDescription() {
        return description;
    }

    public void setDescription(@Size(max = 10) String description) {
        this.description = description;
    }
}
