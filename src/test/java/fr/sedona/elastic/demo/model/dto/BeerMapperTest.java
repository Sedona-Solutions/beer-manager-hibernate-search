package fr.sedona.elastic.demo.model.dto;

import fr.sedona.elastic.demo.model.BeerEntity;
import fr.sedona.elastic.demo.model.BreweryEntity;
import fr.sedona.elastic.demo.model.BreweryType;
import fr.sedona.elastic.demo.model.mapper.BeerMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test suite for BeerMapper
 */
class BeerMapperTest {

    BeerMapper mapper;

    @BeforeEach
    public void setUp() {
        mapper = Mappers.getMapper(BeerMapper.class);
    }

    @Test
    void beer_toDto_should_map_all_entity_fields() {
        // given
        BreweryEntity brewery = new BreweryEntity();
        brewery.id = 1L;
        brewery.setName("Kronenbourg");
        brewery.setType(BreweryType.INDUSTRIAL);
        BeerEntity entity = new BeerEntity();
        entity.setName("name");
        entity.setIngredients(List.of("Eau", "Malt", "Houblon"));
        entity.setAlcoholLevel(5.8f);
        entity.setBrewery(brewery);

        // when
        BeerDTO dto = mapper.toDto(entity);

        // then
        assertEquals(entity.getName(), dto.getName());
        assertEquals(entity.getAlcoholLevel(), dto.getAlcoholLevel());
        assertEquals(entity.getIngredients(), dto.getIngredients());
        assertEquals(brewery.id, dto.getBrewery().getId());
        assertEquals(brewery.getName(), dto.getBrewery().getName());
        assertEquals(brewery.getType(), dto.getBrewery().getType());
    }
}
