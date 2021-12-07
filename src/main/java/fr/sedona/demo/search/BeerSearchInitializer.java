package fr.sedona.demo.search;

import io.quarkus.runtime.StartupEvent;
import org.hibernate.search.mapper.orm.session.SearchSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.event.Observes;
import javax.inject.Inject;

/**
 * Initializer used to reindex all entities on application startup
 */
public class BeerSearchInitializer {

    private static final Logger LOGGER = LoggerFactory.getLogger(BeerSearchInitializer.class);

    private SearchSession searchSession;

    @Inject
    public BeerSearchInitializer(SearchSession searchSession) {
        this.searchSession = searchSession;
    }

    public void reindexOnStartup(@Observes StartupEvent startupEvent) throws InterruptedException {
        this.searchSession.massIndexer().startAndWait();
        LOGGER.info("Data in DB reindexed");
    }

}
