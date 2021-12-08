package fr.sedona.elastic.demo.search.binder;

import org.hibernate.search.engine.backend.document.DocumentElement;
import org.hibernate.search.engine.backend.document.IndexFieldReference;
import org.hibernate.search.engine.backend.types.Aggregable;
import org.hibernate.search.engine.backend.types.Sortable;
import org.hibernate.search.mapper.pojo.bridge.TypeBridge;
import org.hibernate.search.mapper.pojo.bridge.binding.TypeBindingContext;
import org.hibernate.search.mapper.pojo.bridge.mapping.programmatic.TypeBinder;
import org.hibernate.search.mapper.pojo.bridge.runtime.TypeBridgeWriteContext;

import fr.sedona.elastic.demo.model.BeerEntity;
import fr.sedona.elastic.demo.model.BreweryType;

/**
 * Type binder used to compute family field value for beers
 */
public class BeerFamilyBinder implements TypeBinder {

    @Override
    public void bind(TypeBindingContext context) {
        // Define which attributes updates should trigger reindexing
        context.dependencies()
                .use("alcoholLevel")
                .use("brewery");

        // Define mapping for the family field
        var sortableKeywordType = context.typeFactory()
                .asString()
                .aggregable(Aggregable.YES)
                .sortable(Sortable.YES).toIndexFieldType();

        IndexFieldReference<String> familyFieldRef = context.indexSchemaElement()
                .field("family", sortableKeywordType)
                .toReference();

        context.bridge(BeerEntity.class, new BeerFamilyBinder.BeerFamilyBridge(familyFieldRef));
    }

    private static class BeerFamilyBridge implements TypeBridge<BeerEntity> {

        private final IndexFieldReference<String> beerFamilyField;

        public BeerFamilyBridge(IndexFieldReference<String> beerFamilyField) {
            this.beerFamilyField = beerFamilyField;
        }

        @Override
        public void write(DocumentElement target, BeerEntity bridgedElement, TypeBridgeWriteContext context) {
            // business logic is here
            String beerFamily = "";
            float alcoholLevel = bridgedElement.getAlcoholLevel();
            BreweryType breweryType = bridgedElement.getBrewery().getType();

            if (breweryType == BreweryType.CRAFT) {
                beerFamily = alcoholLevel < 6 ? "artisanale-legere" : "artisanale-forte";
            } else if (breweryType == BreweryType.INDUSTRIAL) {
                beerFamily = alcoholLevel < 6 ? "industrielle-legere" : "industrielle-forte";
            }

            // the defined field and its computed value is added to the ES document
            target.addValue(this.beerFamilyField, beerFamily);
        }
    }
}
