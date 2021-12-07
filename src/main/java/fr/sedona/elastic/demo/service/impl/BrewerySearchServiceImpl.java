package fr.sedona.elastic.demo.service.impl;

import fr.sedona.elastic.demo.model.BreweryEntity;
import fr.sedona.elastic.demo.model.dto.BreweryDTO;
import fr.sedona.elastic.demo.model.mapper.BeerMapper;
import fr.sedona.elastic.demo.search.dto.BrewerySearchParams;
import fr.sedona.elastic.demo.service.BrewerySearchService;
import org.hibernate.search.backend.elasticsearch.ElasticsearchExtension;
import org.hibernate.search.backend.elasticsearch.search.projection.dsl.ElasticsearchSearchProjectionFactory;
import org.hibernate.search.engine.search.predicate.dsl.BooleanPredicateClausesStep;
import org.hibernate.search.mapper.orm.session.SearchSession;
import org.mapstruct.factory.Mappers;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Default implementation of brewery search service
 */
@ApplicationScoped
public class BrewerySearchServiceImpl implements BrewerySearchService {

    private final SearchSession searchSession;
    private final BeerMapper mapper;

    @Inject
    public BrewerySearchServiceImpl(SearchSession searchSession) {
        this.searchSession = searchSession;
        this.mapper = Mappers.getMapper(BeerMapper.class);
    }

    @Override
    public List<BreweryDTO> autocompleteByName(String nameQuery) {
        return searchSession.search(BreweryEntity.class)
                .where(f -> f.match().field("nameAutocomplete").matching(nameQuery))
                .fetchHits(10)
                .stream()
                .map(this.mapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<BreweryDTO> search(BrewerySearchParams params) {
        return searchSession.search(BreweryEntity.class).where(f -> {
            BooleanPredicateClausesStep<?> mainQuery = f.bool();
            String origin = params.getOrigin();
            if (origin != null) {
                mainQuery.must(f.match().field("origin").matching(origin));
            }
            return mainQuery;
        }).fetchHits(10)
                .stream()
                .map(this.mapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public String searchJson(BrewerySearchParams params) {
        return searchSession.search(BreweryEntity.class)
                .extension(ElasticsearchExtension.get())
                .select(ElasticsearchSearchProjectionFactory::source)
                .where(f -> {
                    BooleanPredicateClausesStep<?> mainQuery = f.bool();
                    if (params.getOrigin() != null) {
                        mainQuery.must(f.match().field("origin").matching(params.getOrigin()));
                    }
                    if (params.getBeerName() != null) {
                        mainQuery.must(f.match().field("beers.beerName").matching(params.getBeerName()));
                    }
                    return mainQuery;
                }).fetch(10).responseBody().toString();
    }
}
