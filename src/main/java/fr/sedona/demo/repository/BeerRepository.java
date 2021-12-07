package fr.sedona.demo.repository;

import javax.enterprise.context.ApplicationScoped;

import fr.sedona.demo.model.BeerEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

/**
 * Beer repository
 */
@ApplicationScoped
public class BeerRepository implements PanacheRepository<BeerEntity> {
}
