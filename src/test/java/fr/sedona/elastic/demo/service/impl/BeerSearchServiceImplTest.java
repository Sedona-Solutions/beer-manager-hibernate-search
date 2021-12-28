package fr.sedona.elastic.demo.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.junit.jupiter.api.Test;

import fr.sedona.elastic.demo.model.dto.BeerDTO;
import fr.sedona.elastic.demo.search.dto.BeerSearchParams;
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


    @Test
    void search_should_return_10_beers_if_no_filter_set() {
        // given
        BeerSearchParams params = new BeerSearchParams();

        // when
        List<BeerDTO> results = searchService.search(params);

        // then
        assertEquals(10, results.size());
    }

    @Test
    void search_should_return_only_beers_with_selected_name_if_name_filter_set() {
        // given
        BeerSearchParams params = new BeerSearchParams();
        params.setName("1664");

        // when
        List<BeerDTO> results = searchService.search(params);

        // then
        assertEquals(1, results.size());
        assertEquals("1664", results.get(0).getName());
    }

    @Test
    void search_should_return_only_beers_above_alcohol_lower_bound_if_lower_bound_filter_set() {
        // given
        BeerSearchParams params = new BeerSearchParams();
        params.setAlcoholLevelLowerBound(7.0f);

        // when
        List<BeerDTO> results = searchService.search(params);

        // then
        assertEquals(3, results.size());
        List<String> names = results.stream()
                .map(BeerDTO::getName)
                .sorted()
                .collect(Collectors.toList());

        assertEquals(List.of("Apocalypse Now", "Elephant 1959", "Red is Dead"), names);
    }

    @Test
    void search_should_return_only_beers_below_alcohol_upper_bound_if_upper_bound_filter_set() {
        // given
        BeerSearchParams params = new BeerSearchParams();
        params.setAlcoholLevelUpperBound(5.6f);

        // when
        List<BeerDTO> results = searchService.search(params);

        // then
        assertEquals(2, results.size());
        List<String> names = results.stream()
                .map(BeerDTO::getName)
                .sorted()
                .collect(Collectors.toList());

        assertEquals(List.of("1664", "Tigre Bock"), names);
    }

    @Test
    void search_should_return_only_beers_between_both_alcohol_bound_if_both_bounds_filters_set() {
        // given
        BeerSearchParams params = new BeerSearchParams();
        params.setAlcoholLevelLowerBound(7.0f);
        params.setAlcoholLevelUpperBound(7.5f);

        // when
        List<BeerDTO> results = searchService.search(params);

        // then
        assertEquals(2, results.size());
        List<String> names = results.stream()
                .map(BeerDTO::getName)
                .sorted()
                .collect(Collectors.toList());

        assertEquals(List.of("Elephant 1959", "Red is Dead"), names);
    }

    @Test
    void search_should_return_only_beers_with_selected_family_if_family_filter_set() {
        // given
        BeerSearchParams params = new BeerSearchParams();
        params.setFamily("artisanale-forte");

        // when
        List<BeerDTO> results = searchService.search(params);

        // then
        assertEquals(4, results.size());
        List<String> names = results.stream()
                .map(BeerDTO::getName)
                .sorted()
                .collect(Collectors.toList());

        assertEquals(List.of("Apocalypse Now", "Delicatessen", "Elephant 1959", "Red is Dead"), names);
    }

    @Test
    void search_should_return_only_beers_matching_all_criteras_if_all_filters_set() {
        // given
        BeerSearchParams params = new BeerSearchParams();
        params.setFamily("artisanale-forte");
        params.setName("Elephant");
        params.setAlcoholLevelLowerBound(7.0f);
        params.setAlcoholLevelUpperBound(7.5f);

        // when
        List<BeerDTO> results = searchService.search(params);

        // then
        assertEquals(1, results.size());
        assertEquals("Elephant 1959", results.get(0).getName());
    }

    @Test
    void aggregate_should_return_only_beers_with_selected_family_if_family_filter_set() {
        // given
        BeerSearchParams params = new BeerSearchParams();
        params.setFamily("artisanale-forte");

        // when
        Map<String, Long> results = searchService.aggregate(params);

        // then
        assertEquals(1, results.size());
        assertEquals(4,  results.get("Sainte-Cru"));
    }

    @Test
    void aggregate_should_return_only_beers_with_alcohol_level_below_upper_bound_if_upper_bound_set() {
        // given
        BeerSearchParams params = new BeerSearchParams();
        params.setAlcoholLevelUpperBound(6.0f);

        // when
        Map<String, Long> results = searchService.aggregate(params);

        // then
        assertEquals(3, results.size());
        assertEquals(3,  results.get("Kronenbourg"));
        assertEquals(1,  results.get("Carlsberg"));
        assertEquals(1,  results.get("La Bière de la Rade"));
    }

    @Test
    void aggregate_should_return_only_beers_with_alcohol_level_above_lower_bound_if_lower_bound_set() {
        // given
        BeerSearchParams params = new BeerSearchParams();
        params.setAlcoholLevelLowerBound(7.0f);

        // when
        Map<String, Long> results = searchService.aggregate(params);

        // then
        assertEquals(1, results.size());
        assertEquals(3,  results.get("Sainte-Cru"));
    }

    @Test
    void aggregate_should_return_only_beers_with_name_matching_search_if_name_filter_set() {
        // given
        BeerSearchParams params = new BeerSearchParams();
        params.setName("Elephant");

        // when
        Map<String, Long> results = searchService.aggregate(params);

        // then
        assertEquals(1, results.size());
        assertEquals(1, results.get("Sainte-Cru"));
    }
}
