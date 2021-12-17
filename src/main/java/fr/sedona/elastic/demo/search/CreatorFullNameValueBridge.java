package fr.sedona.elastic.demo.search;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.hibernate.search.mapper.pojo.bridge.ValueBridge;
import org.hibernate.search.mapper.pojo.bridge.runtime.ValueBridgeToIndexedValueContext;

import fr.sedona.elastic.demo.model.ExternalUserEntity;
import fr.sedona.elastic.demo.repository.ExternalUserRepository;
import io.quarkus.arc.Unremovable;

/**`
 * ValueBridge used to retrieve external user by id and get its full name.
 * Used as example of ValueBridge with dependency injection.
 */
@ApplicationScoped
@Unremovable
public class CreatorFullNameValueBridge implements ValueBridge<Long, String> {

    private final ExternalUserRepository userRepository;

    @Inject
    public CreatorFullNameValueBridge(ExternalUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public String toIndexedValue(Long value, ValueBridgeToIndexedValueContext context) {
        ExternalUserEntity creator = this.userRepository.findById(value);
        return  creator != null ? creator.getFirstName() + " " + creator.getLastName() : null;
    }

}
