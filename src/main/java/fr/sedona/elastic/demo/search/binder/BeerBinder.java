package fr.sedona.elastic.demo.search.binder;

import fr.sedona.elastic.demo.model.BeerEntity;
import fr.sedona.elastic.demo.model.BreweryEntity;
import io.quarkus.arc.Unremovable;
import org.hibernate.search.engine.backend.document.DocumentElement;
import org.hibernate.search.engine.backend.document.IndexFieldReference;
import org.hibernate.search.engine.backend.document.IndexObjectFieldReference;
import org.hibernate.search.engine.backend.types.Aggregable;
import org.hibernate.search.engine.backend.types.Sortable;
import org.hibernate.search.mapper.pojo.bridge.PropertyBridge;
import org.hibernate.search.mapper.pojo.bridge.binding.PropertyBindingContext;
import org.hibernate.search.mapper.pojo.bridge.mapping.programmatic.PropertyBinder;
import org.hibernate.search.mapper.pojo.bridge.runtime.PropertyBridgeWriteContext;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
@Unremovable
public class BeerBinder implements PropertyBinder {

    @Override
    public void bind(PropertyBindingContext context) {
        context.dependencies().use("id");

        var beersObjectField = context.indexSchemaElement()
                .objectField("beers");

        var sortableType = context.typeFactory()
                .asString()
                .aggregable(Aggregable.YES)
                .sortable(Sortable.YES);
        var keywordType = sortableType
                .toIndexFieldType();

        context.bridge(List.class, new Bridge(
                beersObjectField.toReference(),
                beersObjectField.field("name", keywordType).multiValued().toReference())
        );

    }

    private static class Bridge implements PropertyBridge<List> {
        private final IndexObjectFieldReference beersFieldRef;
        private final IndexFieldReference<String> nameField;

        public Bridge(IndexObjectFieldReference beersFieldRef,
                      IndexFieldReference<String> nameField) {
            this.beersFieldRef = beersFieldRef;
            this.nameField = nameField;
        }

        @Override
        public void write(
                DocumentElement target,
                List bridgeElement,
                PropertyBridgeWriteContext propertyBridgeWriteContext) {
            DocumentElement breweries = target.addObject(this.beersFieldRef);
            bridgeElement.forEach(beerEntity -> {
                        breweries.addValue(this.nameField, ((BeerEntity) beerEntity).getName());
                    }
            );
        }
    }
}
