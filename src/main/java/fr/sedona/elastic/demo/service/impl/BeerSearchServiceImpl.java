package fr.sedona.elastic.demo.service.impl;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.hibernate.search.engine.search.aggregation.AggregationKey;
import org.hibernate.search.engine.search.common.ValueConvert;
import org.hibernate.search.engine.search.predicate.dsl.BooleanPredicateClausesStep;
import org.hibernate.search.engine.search.query.SearchResult;
import org.hibernate.search.mapper.orm.session.SearchSession;
import org.mapstruct.factory.Mappers;

import fr.sedona.elastic.demo.model.BeerEntity;
import fr.sedona.elastic.demo.model.dto.BeerDTO;
import fr.sedona.elastic.demo.model.mapper.BeerMapper;
import fr.sedona.elastic.demo.search.dto.BeerSearchParams;
import fr.sedona.elastic.demo.service.BeerSearchService;

/**
 * Default implementation of beer search service
 */
@ApplicationScoped
public class BeerSearchServiceImpl implements BeerSearchService {

    private final SearchSession searchSession;
    private final BeerMapper mapper;

    @Inject
    public BeerSearchServiceImpl(SearchSession searchSession) {
        this.searchSession = searchSession;
        this.mapper = Mappers.getMapper(BeerMapper.class);
    }

    @Override
    public List<BeerDTO> searchByName(String nameQuery) {
        return searchSession.search(BeerEntity.class)
                .where(f -> f.match().field("name").matching(nameQuery))
                .fetchHits(10)
                .stream()
                .map(this.mapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<BeerDTO> searchByCreatorName(String creatorNameQuery) {
        // ValueConvert must be set to NO or a classcastexception will be thrown
        // as annotated type is a Long and query input is a string
        return searchSession.search(BeerEntity.class)
                .where(f -> f.match().field("creatorFullName").matching(creatorNameQuery, ValueConvert.NO))
                .fetchHits(10)
                .stream()
                .map(this.mapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<BeerDTO> searchByCreatorFirstNameAndLastName(String firstNameQuery, String lastNameQuery) {
        return searchSession.search(BeerEntity.class)
                .where(f -> {
                    BooleanPredicateClausesStep<?> mainQuery = f.bool();
                    if (firstNameQuery != null)  {
                        mainQuery.must(f.match().field("creator.firstName").matching(firstNameQuery));
                    }
                    if (lastNameQuery != null)  {
                        mainQuery.must(f.match().field("creator.lastName").matching(lastNameQuery));
                    }
                    return mainQuery;
                })
                .fetchHits(10)
                .stream()
                .map(this.mapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<BeerDTO> search(BeerSearchParams searchParams) {
        return searchSession.search(BeerEntity.class)
                .where(f -> {
                    BooleanPredicateClausesStep<?> mainQuery = f.bool();
                    String name = searchParams.getName();
                    String family = searchParams.getFamily();
                    Float alcoholLowerBound = searchParams.getAlcoholLevelLowerBound();
                    Float alcoholUpperBound = searchParams.getAlcoholLevelUpperBound();

                    if (name != null && !name.isBlank()){
                        mainQuery.must(f.match().field("name").matching(name));
                    }
                    if (family != null && !family.isBlank()){
                        mainQuery.must(f.match().field("family").matching(family));
                    }
                    if (alcoholLowerBound != null || alcoholUpperBound != null){
                        mainQuery.filter(f.range().field("alcoholLevel").between(alcoholLowerBound, alcoholUpperBound));
                    }
                    return mainQuery;
                })
                .fetchHits(10)
                .stream()
                .map(this.mapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Map<String,Long> aggregate(BeerSearchParams searchParams) {
        AggregationKey<Map<String, Long>> beersByBrewery = AggregationKey.of("beers_by_brewery");

        SearchResult<BeerEntity> result = searchSession.search(BeerEntity.class)
                .where(f -> {
                    BooleanPredicateClausesStep<?> mainQuery = f.bool();
                    String name = searchParams.getName();
                    String family = searchParams.getFamily();
                    Float alcoholLowerBound = searchParams.getAlcoholLevelLowerBound();
                    Float alcoholUpperBound = searchParams.getAlcoholLevelUpperBound();

                    if (name != null && !name.isBlank()) {
                        mainQuery.must(f.match().field("name").matching(name));
                    }
                    if (family != null && !family.isBlank()) {
                        mainQuery.must(f.match().field("family").matching(family));
                    }
                    if (alcoholLowerBound != null || alcoholUpperBound != null) {
                        mainQuery.filter(f.range().field("alcoholLevel").between(alcoholLowerBound, alcoholUpperBound));
                    }
                    return mainQuery;
                })
                .aggregation(beersByBrewery, f -> f.terms().field("brewery.nameAggregable", String  .class))
                .fetch(10);
        return result.aggregation(beersByBrewery);
    }
}
