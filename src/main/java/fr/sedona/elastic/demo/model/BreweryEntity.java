package fr.sedona.elastic.demo.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import org.hibernate.search.engine.backend.types.Aggregable;
import org.hibernate.search.engine.backend.types.Sortable;
import org.hibernate.search.mapper.pojo.bridge.mapping.annotation.PropertyBinderRef;
import org.hibernate.search.mapper.pojo.bridge.mapping.annotation.ValueBridgeRef;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.FullTextField;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.Indexed;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.KeywordField;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.PropertyBinding;

import fr.sedona.elastic.demo.search.OriginValueBridge;
import fr.sedona.elastic.demo.search.binder.BeerBinder;
import io.quarkus.hibernate.orm.panache.PanacheEntity;

/**
 * Brewery entity
 */
@Entity(name="Brewery")
@Indexed
public class BreweryEntity extends PanacheEntity {

    @FullTextField
    @FullTextField(name = "nameAutocomplete", analyzer = "custom")
    @KeywordField(name = "nameAggregable", sortable = Sortable.YES, aggregable = Aggregable.YES)
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
