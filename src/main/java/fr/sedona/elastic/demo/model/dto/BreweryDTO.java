package fr.sedona.elastic.demo.model.dto;

import fr.sedona.elastic.demo.model.BreweryType;

/**
 * Brewery DTO
 */
public class BreweryDTO {

    private long id;
    private String name;
    private BreweryType type;
    private String country;

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

    public BreweryType getType() {
        return type;
    }

    public void setType(BreweryType type) {
        this.type = type;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
