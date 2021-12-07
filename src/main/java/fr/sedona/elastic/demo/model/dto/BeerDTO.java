package fr.sedona.elastic.demo.model.dto;

import java.util.List;

/**
 * Beer DTO
 */
public class BeerDTO {

    private long id;
    private String name;
    private float alcoholLevel;
    private List<String> ingredients;
    private BreweryDTO brewery;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getAlcoholLevel() {
        return alcoholLevel;
    }

    public void setAlcoholLevel(float alcoholLevel) {
        this.alcoholLevel = alcoholLevel;
    }

    public List<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<String> ingredients) {
        this.ingredients = ingredients;
    }

    public BreweryDTO getBrewery() {
        return brewery;
    }

    public void setBrewery(BreweryDTO brewery) {
        this.brewery = brewery;
    }
}
