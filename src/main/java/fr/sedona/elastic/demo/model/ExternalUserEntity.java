package fr.sedona.elastic.demo.model;

import javax.persistence.Entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

/**
 * External use entity : for demo purpose we store it in the same application as
 * the beers and breweries, but it should be in another application
 */
@Entity(name="ExternalUser")
public class ExternalUserEntity extends PanacheEntity {

    private String lastName;

    private String firstName;

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
}
