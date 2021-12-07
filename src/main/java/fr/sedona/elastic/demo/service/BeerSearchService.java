package fr.sedona.elastic.demo.service;


import fr.sedona.elastic.demo.model.dto.BeerDTO;

import java.util.List;

/**
 * Beer search service
 */
public interface BeerSearchService {

    List<BeerDTO> searchByName(String nameQuery);
}
