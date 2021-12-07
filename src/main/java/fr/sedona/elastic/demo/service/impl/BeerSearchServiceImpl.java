package fr.sedona.elastic.demo.service.impl;

import fr.sedona.elastic.demo.model.BeerEntity;
import fr.sedona.elastic.demo.model.dto.BeerDTO;
import fr.sedona.elastic.demo.model.mapper.BeerMapper;
import fr.sedona.elastic.demo.service.BeerSearchService;
import org.hibernate.search.mapper.orm.session.SearchSession;
import org.mapstruct.factory.Mappers;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

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
}
