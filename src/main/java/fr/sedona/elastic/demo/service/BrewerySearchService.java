package fr.sedona.elastic.demo.service;


import com.google.gson.JsonObject;
import fr.sedona.elastic.demo.model.dto.BreweryDTO;
import fr.sedona.elastic.demo.search.dto.BrewerySearchParams;

import java.util.List;

/**
 * Brewery search service
 */
public interface BrewerySearchService {

    List<BreweryDTO> autocompleteByName(String nameQuery);

    List<BreweryDTO> search(BrewerySearchParams params);

    String searchJson(BrewerySearchParams params);
}
