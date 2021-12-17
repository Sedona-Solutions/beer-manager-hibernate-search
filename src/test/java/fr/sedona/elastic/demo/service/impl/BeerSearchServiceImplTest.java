package fr.sedona.elastic.demo.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.junit.jupiter.api.Test;

import fr.sedona.elastic.demo.model.dto.BeerDTO;
import fr.sedona.elastic.demo.service.BeerSearchService;
import io.quarkus.test.junit.QuarkusTest;

/**
 * Test suite for {@link BeerSearchServiceImpl}
 */
@QuarkusTest
class BeerSearchServiceImplTest {

    @Inject
    BeerSearchService searchService;

    @Test
    void searchByName_should_return_expected_beers_with_caasae_insensitive_query() {
        // given
        String query = "skoll";

        // when
        List<BeerDTO> results = searchService.searchByName(query);

        // then
        assertEquals(1, results.size());
        assertEquals("Skoll", results.get(0).getName());
    }

    @Test
    void searchByCreatorName_should_return_expected_beers_with_last_name_in_query() {
        // given
        String query = "lamotte";

        // when
        List<BeerDTO> results = searchService.searchByCreatorName(query);

        // then
        assertEquals(4, results.size());
        List<String> beers = results.stream().map(BeerDTO::getName).sorted().collect(Collectors.toList());
        assertEquals(List.of("1664","Carlsberg","Skoll","Tigre Bock"), beers);
    }

    @Test
    void searchByCreatorName_should_return_expected_beers_with_first_name_in_query() {
        // given
        String query = "EDOUARD";

        // when
        List<BeerDTO> results = searchService.searchByCreatorName(query);

        // then
        assertEquals(4, results.size());
        List<String> beers = results.stream().map(BeerDTO::getName).sorted().collect(Collectors.toList());
        assertEquals(List.of("1664","Carlsberg","Skoll","Tigre Bock"), beers);
    }

    @Test
    void searchByCreatorFirstNameAndLastName_should_return_expected_beers_with_exact_first_name() {
        // given
        String firstName = "Edouard";

        // when
        List<BeerDTO> results = searchService.searchByCreatorFirstNameAndLastName(firstName, null);

        // then
        assertEquals(4, results.size());
        List<String> beers = results.stream().map(BeerDTO::getName).sorted().collect(Collectors.toList());
        assertEquals(List.of("1664","Carlsberg","Skoll","Tigre Bock"), beers);
    }

    @Test
    void searchByCreatorFirstNameAndLastName_should_return_no_beers_with_different_case_first_name() {
        // given
        String firstName = "edouard";

        // when
        List<BeerDTO> results = searchService.searchByCreatorFirstNameAndLastName(firstName, null);

        // then
        assertTrue(results.isEmpty());
    }

    @Test
    void searchByCreatorFirstNameAndLastName_should_return_expected_beers_with_case_insensitive_last_name() {
        // given
        String lastName = "lamotte";

        // when
        List<BeerDTO> results = searchService.searchByCreatorFirstNameAndLastName(null, lastName);

        // then
        assertEquals(4, results.size());
        List<String> beers = results.stream().map(BeerDTO::getName).sorted().collect(Collectors.toList());
        assertEquals(List.of("1664","Carlsberg","Skoll","Tigre Bock"), beers);
    }

    @Test
    void searchByCreatorFirstNameAndLastName_should_return_expected_beers_with_first_and_last_name() {
        // given
        String firstName = "Edouard";
        String lastName = "lamotte";

        // when
        List<BeerDTO> results = searchService.searchByCreatorFirstNameAndLastName(firstName, lastName);

        // then
        assertEquals(4, results.size());
        List<String> beers = results.stream().map(BeerDTO::getName).sorted().collect(Collectors.toList());
        assertEquals(List.of("1664","Carlsberg","Skoll","Tigre Bock"), beers);
    }
}
