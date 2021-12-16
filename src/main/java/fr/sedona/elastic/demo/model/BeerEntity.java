package fr.sedona.elastic.demo.model;

import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

/**
 * Beer entity
 */
@Entity(name="Beer")
public class BeerEntity extends PanacheEntity {

    private String name;

    @ManyToOne
    private BreweryEntity brewery;

    @ElementCollection
    private List<String> flavors;

    private float alcoholLevel;

    /**
     * Id of user who added the beer, should be managed in another application
     */
    private long creatorId;

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

    public List<String> getFlavors() {
        return flavors;
    }

    public void setFlavors(List<String> flavors) {
        this.flavors = flavors;
    }

    public float getAlcoholLevel() {
        return alcoholLevel;
    }

    public void setAlcoholLevel(float alcoholLevel) {
        this.alcoholLevel = alcoholLevel;
    }

    public long getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(long creatorId) {
        this.creatorId = creatorId;
    }
}
