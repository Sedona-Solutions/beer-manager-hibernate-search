package fr.sedona.elastic.demo.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.hibernate.search.engine.search.common.ValueConvert;
import org.hibernate.search.engine.search.predicate.dsl.BooleanPredicateClausesStep;
import org.hibernate.search.mapper.orm.session.SearchSession;
import org.mapstruct.factory.Mappers;

import fr.sedona.elastic.demo.model.BeerEntity;
import fr.sedona.elastic.demo.model.dto.BeerDTO;
import fr.sedona.elastic.demo.model.mapper.BeerMapper;
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
}
