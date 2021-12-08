package fr.sedona.elastic.demo.search.dto;

/**
 * Search parameters for beers
 */
public class BeerSearchParams {

    private String family;

    private String name;

    private Float alcoholLevelLowerBound;
    private Float alcoholLevelUpperBound;

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getAlcoholLevelLowerBound() {
        return alcoholLevelLowerBound;
    }

    public void setAlcoholLevelLowerBound(Float alcoholLevelLowerBound) {
        this.alcoholLevelLowerBound = alcoholLevelLowerBound;
    }

    public Float getAlcoholLevelUpperBound() {
        return alcoholLevelUpperBound;
    }

    public void setAlcoholLevelUpperBound(Float alcoholLevelUpperBound) {
        this.alcoholLevelUpperBound = alcoholLevelUpperBound;
    }
}
