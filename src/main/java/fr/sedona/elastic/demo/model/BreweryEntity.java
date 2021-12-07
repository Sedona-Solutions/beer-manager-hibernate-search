package fr.sedona.elastic.demo.model;

import fr.sedona.elastic.demo.search.binder.BeerBinder;
import fr.sedona.elastic.demo.search.OriginValueBridge;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import org.hibernate.search.mapper.pojo.bridge.mapping.annotation.PropertyBinderRef;
import org.hibernate.search.mapper.pojo.bridge.mapping.annotation.ValueBridgeRef;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.FullTextField;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.Indexed;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.KeywordField;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.PropertyBinding;

import javax.persistence.*;
import java.util.List;

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

    @OneToMany(mappedBy = "brewery", fetch = FetchType.EAGER)
    @PropertyBinding(binder = @PropertyBinderRef(type = BeerBinder.class))
    private List<BeerEntity> beers;

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

    public List<BeerEntity> getBeers() {
        return beers;
    }

    public void setBeers(List<BeerEntity> beers) {
        this.beers = beers;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
