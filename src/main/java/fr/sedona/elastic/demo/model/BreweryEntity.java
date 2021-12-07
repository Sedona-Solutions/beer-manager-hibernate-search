package fr.sedona.elastic.demo.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

/**
 * Brewery entity
 */
@Entity(name="Brewery")
public class BreweryEntity extends PanacheEntity {

    private String name;

    @Enumerated(EnumType.STRING)
    private BreweryType type;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BreweryType getType() {
        return type;
    }

    public void setType(BreweryType type) {
        this.type = type;
    }
}
