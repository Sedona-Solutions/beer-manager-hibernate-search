package fr.sedona.elastic.demo.repository;

import javax.enterprise.context.ApplicationScoped;

import fr.sedona.elastic.demo.model.ExternalUserEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

/**
 * External user repository
 */
@ApplicationScoped
public class ExternalUserRepository implements PanacheRepository<ExternalUserEntity> {
}
