package fr.sedona.demo.model.dto;

import fr.sedona.demo.model.BeerEntity;
import fr.sedona.demo.model.BreweryEntity;
import fr.sedona.demo.model.BreweryType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test suite for BeerMapper
 */
class BeerMapperTest {

    BeerMapper mapper;

    @BeforeEach
    public void setUp(){
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
        entity.setFlavors(List.of("Malt","Houblon"));
        entity.setAlcoholLevel(5.8f);
        entity.setBrewery(brewery);

        // when
        BeerDTO dto = mapper.toDto(entity);

        // then
        assertThat(dto.getName()).isEqualTo(entity.getName());
        assertThat(dto.getAlcoholLevel()).isEqualTo(entity.getAlcoholLevel());
        assertThat(dto.getFlavors()).containsExactlyElementsOf(entity.getFlavors());
        assertThat(dto.getBrewery()).isEqualToComparingFieldByField(brewery);
    }
}
