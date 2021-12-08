package fr.sedona.elastic.demo.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import javax.inject.Inject;

import org.junit.jupiter.api.Test;

import fr.sedona.elastic.demo.model.dto.BreweryDTO;
import fr.sedona.elastic.demo.search.dto.BrewerySearchParams;
import fr.sedona.elastic.demo.service.BrewerySearchService;
import io.quarkus.test.junit.QuarkusTest;

/**
 * Test suite for {@link BrewerySearchServiceImpl}
 */
@QuarkusTest
class BrewerySearchServiceImplTest {

    @Inject
    BrewerySearchService searchService;

    @Test
    void search_should_return_all_breweries_if_no_filter_set() {
        // given
        BrewerySearchParams params = new BrewerySearchParams();

        // when
        List<BreweryDTO> results = searchService.search(params);

        // then
        assertEquals(5, results.size());
    }

    @Test
    void search_should_return_only_breweries_from_selected_origin_if_origin_filter_set() {
        // given
        BrewerySearchParams params = new BrewerySearchParams();
        params.setOrigin("Monde");

        // when
        List<BreweryDTO> results = searchService.search(params);

        // then
        assertEquals(1, results.size());
        assertEquals("Danemark", results.get(0).getCountry());
    }
}
