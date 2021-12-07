package fr.sedona.elastic.demo.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.FullTextField;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.GenericField;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.Indexed;

import javax.persistence.*;
import java.util.List;

/**
 * Beer entity
 */
@Entity(name = "Beer")
@Indexed
public class BeerEntity extends PanacheEntity {

    @FullTextField
    private String name;

    @ManyToOne
    @JoinColumn(
            name = "BREWERY_ID",
            foreignKey = @ForeignKey(name = "FK_BEER_BREWERY")
    )
    private BreweryEntity brewery;

    @ElementCollection
    private List<String> ingredients;

    @GenericField
    private float alcoholLevel;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BreweryEntity getBrewery() {
        return brewery;
    }

    public void setBrewery(BreweryEntity brewery) {
        this.brewery = brewery;
    }

    public List<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<String> ingredients) {
        this.ingredients = ingredients;
    }

    public float getAlcoholLevel() {
        return alcoholLevel;
    }

    public void setAlcoholLevel(float alcoholLevel) {
        this.alcoholLevel = alcoholLevel;
    }

}
