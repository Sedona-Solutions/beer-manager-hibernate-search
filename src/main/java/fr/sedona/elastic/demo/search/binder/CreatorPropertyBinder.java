package fr.sedona.elastic.demo.search.binder;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.hibernate.search.engine.backend.document.DocumentElement;
import org.hibernate.search.engine.backend.document.IndexFieldReference;
import org.hibernate.search.engine.backend.document.IndexObjectFieldReference;
import org.hibernate.search.mapper.pojo.bridge.PropertyBridge;
import org.hibernate.search.mapper.pojo.bridge.binding.PropertyBindingContext;
import org.hibernate.search.mapper.pojo.bridge.mapping.programmatic.PropertyBinder;
import org.hibernate.search.mapper.pojo.bridge.runtime.PropertyBridgeWriteContext;

import fr.sedona.elastic.demo.model.ExternalUserEntity;
import fr.sedona.elastic.demo.repository.ExternalUserRepository;
import io.quarkus.arc.Unremovable;

/**
 * PropertyBinder used to create an object creator with two attributes.
 */
@ApplicationScoped
@Unremovable
public class CreatorPropertyBinder implements PropertyBinder {

    private final ExternalUserRepository userRepository;

    @Inject
    public CreatorPropertyBinder(ExternalUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void bind(PropertyBindingContext context) {
        context.dependencies().useRootOnly();

        // mapping type definition are made here
        var textType = context.typeFactory()
                .asString()
                .analyzer("custom")
                .searchAnalyzer("standard")
                .toIndexFieldType();

        var keywordType = context.typeFactory()
                .asString()
                .toIndexFieldType();

        // sub-object definition
        var creatorObjectField = context.indexSchemaElement()
                .objectField("creator");

        // both subobject and subfields are passed to the bridge
        context.bridge(Long.class, new CreatorBridge(
                userRepository,
                creatorObjectField.toReference(),
                creatorObjectField.field("lastName", textType).toReference(),
                creatorObjectField.field("firstName", keywordType).toReference())
        );

    }

    private static class CreatorBridge implements PropertyBridge<Long> {
        private final ExternalUserRepository userRepository;
        private final IndexObjectFieldReference creatorObjectField;
        private final IndexFieldReference<String> lastNameField;
        private final IndexFieldReference<String> firstNameField;

        public CreatorBridge(ExternalUserRepository userRepository, IndexObjectFieldReference creatorObjectField,
                             IndexFieldReference<String> lastNameField,
                             IndexFieldReference<String> firstNameField) {
            this.userRepository  = userRepository;
            this.creatorObjectField =  creatorObjectField;
            this.lastNameField = lastNameField;
            this.firstNameField = firstNameField;
        }

        @Override
        public void write(
                DocumentElement document,
                Long bridgedElement,
                PropertyBridgeWriteContext propertyBridgeWriteContext) {
            // Retrieve creator
            ExternalUserEntity creator = this.userRepository.findById(bridgedElement);

            if (creator == null){
                return;
            }

            // add creator object and its attributes to the document
            DocumentElement creatorObject = document.addObject(this.creatorObjectField);
            creatorObject.addValue(this.lastNameField, creator.getLastName());
            creatorObject.addValue(this.firstNameField, creator.getFirstName());
        }
    }
}
