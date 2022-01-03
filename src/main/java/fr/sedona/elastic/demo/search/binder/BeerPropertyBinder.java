package fr.sedona.elastic.demo.search.binder;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;

import org.hibernate.search.engine.backend.document.DocumentElement;
import org.hibernate.search.engine.backend.document.IndexFieldReference;
import org.hibernate.search.engine.backend.document.IndexObjectFieldReference;
import org.hibernate.search.engine.backend.types.Aggregable;
import org.hibernate.search.engine.backend.types.Sortable;
import org.hibernate.search.mapper.pojo.bridge.PropertyBridge;
import org.hibernate.search.mapper.pojo.bridge.binding.PropertyBindingContext;
import org.hibernate.search.mapper.pojo.bridge.mapping.programmatic.PropertyBinder;
import org.hibernate.search.mapper.pojo.bridge.runtime.PropertyBridgeWriteContext;
import org.hibernate.search.mapper.pojo.extractor.builtin.BuiltinContainerExtractors;
import org.hibernate.search.mapper.pojo.extractor.mapping.programmatic.ContainerExtractorPath;

import fr.sedona.elastic.demo.model.BeerEntity;
import io.quarkus.arc.Unremovable;

@ApplicationScoped
@Unremovable
public class BeerPropertyBinder implements PropertyBinder {

    @Override
    public void bind(PropertyBindingContext context) {
        context.dependencies()
                .use(ContainerExtractorPath.explicitExtractor(BuiltinContainerExtractors.COLLECTION),
                        "name");

        var beersObjectField = context.indexSchemaElement()
                .objectField("beers");

        var sortableKeywordType = context.typeFactory()
                .asString()
                .aggregable(Aggregable.YES)
                .sortable(Sortable.YES)
                .toIndexFieldType();

        context.bridge(List.class, new Bridge(
                beersObjectField.toReference(),
                beersObjectField.field("beerName", sortableKeywordType).multiValued().toReference())
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
            bridgeElement.forEach(beerEntity ->
                    breweries.addValue(this.nameField, ((BeerEntity) beerEntity).getName())
            );
        }
    }
}
