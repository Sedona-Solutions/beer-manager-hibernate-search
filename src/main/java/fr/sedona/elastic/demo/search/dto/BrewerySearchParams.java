package fr.sedona.elastic.demo.search.dto;

/**
 * Search parameters for brewery
 */
public class BrewerySearchParams {

    private String origin;

    private String beerName;

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getBeerName() {
        return beerName;
    }

    public void setBeerName(String beerName) {
        this.beerName = beerName;
    }
}
