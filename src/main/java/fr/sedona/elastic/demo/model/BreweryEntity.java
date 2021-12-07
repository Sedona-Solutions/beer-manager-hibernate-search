package fr.sedona.elastic.demo.model;

import fr.sedona.elastic.demo.search.OriginValueBridge;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import org.hibernate.search.mapper.pojo.bridge.mapping.annotation.ValueBridgeRef;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.FullTextField;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.Indexed;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.KeywordField;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

/**
 * Brewery entity
 */
@Entity(name="Brewery")
@Indexed
public class BreweryEntity extends PanacheEntity {

    @FullTextField
    @FullTextField(name = "nameAutocomplete", analyzer = "custom")
    private String name;

    @KeywordField
    @Enumerated(EnumType.STRING)
    private BreweryType type;

    @KeywordField
    @KeywordField(name="origin", valueBridge = @ValueBridgeRef(type = OriginValueBridge.class))
    private String country;

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
