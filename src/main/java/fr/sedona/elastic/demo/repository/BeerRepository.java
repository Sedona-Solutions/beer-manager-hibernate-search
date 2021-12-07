package fr.sedona.elastic.demo.repository;

import javax.enterprise.context.ApplicationScoped;

import fr.sedona.elastic.demo.model.BeerEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

/**
 * Beer repository
 */
@ApplicationScoped
public class BeerRepository implements PanacheRepository<BeerEntity> {
}
