package fr.sedona.elastic.demo.model.dto;

import fr.sedona.elastic.demo.model.BeerEntity;
import fr.sedona.elastic.demo.model.BreweryEntity;
import org.mapstruct.Mapper;

/**
 * Mapper interface for Beer/Brewery operations
 */
@Mapper
public interface BeerMapper {

    BeerDTO toDto(BeerEntity entity);

    BreweryDTO toDto(BreweryEntity entity);
}
