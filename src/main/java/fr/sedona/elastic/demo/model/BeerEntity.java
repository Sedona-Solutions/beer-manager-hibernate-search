package fr.sedona.elastic.demo.model;

import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.search.mapper.pojo.bridge.mapping.annotation.TypeBinderRef;
import org.hibernate.search.mapper.pojo.bridge.mapping.annotation.ValueBridgeRef;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.FullTextField;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.GenericField;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.Indexed;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.IndexedEmbedded;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.TypeBinding;

import fr.sedona.elastic.demo.search.CreatorFullNameValueBridge;
import fr.sedona.elastic.demo.search.binder.BeerFamilyBinder;
import io.quarkus.hibernate.orm.panache.PanacheEntity;

/**
 * Beer entity
 */
@Entity(name = "Beer")
@Indexed
@TypeBinding(binder = @TypeBinderRef(type = BeerFamilyBinder.class))
public class BeerEntity extends PanacheEntity {

    @FullTextField
    private String name;

    @ManyToOne
    @JoinColumn(
            name = "BREWERY_ID",
            foreignKey = @ForeignKey(name = "FK_BEER_BREWERY")
    )
    @IndexedEmbedded
    private BreweryEntity brewery;

    @ElementCollection
    private List<String> ingredients;

    @GenericField
    private float alcoholLevel;

    /**
     * Id of user who added the beer, should be managed in another application
     */
    @FullTextField(name="creatorFullName", valueBridge = @ValueBridgeRef(type = CreatorFullNameValueBridge.class))
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

    public long getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(long creatorId) {
        this.creatorId = creatorId;
    }
}
