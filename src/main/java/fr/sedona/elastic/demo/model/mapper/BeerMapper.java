package fr.sedona.elastic.demo.model.mapper;

import fr.sedona.elastic.demo.model.BeerEntity;
import fr.sedona.elastic.demo.model.BreweryEntity;
import fr.sedona.elastic.demo.model.dto.BeerDTO;
import fr.sedona.elastic.demo.model.dto.BreweryDTO;
import org.mapstruct.Mapper;

/**
 * Mapper interface for Beer/Brewery operations
 */
@Mapper
public interface BeerMapper {

    BeerDTO toDto(BeerEntity entity);

    BreweryDTO toDto(BreweryEntity entity);
}
