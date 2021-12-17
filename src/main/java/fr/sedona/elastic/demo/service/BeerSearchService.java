package fr.sedona.elastic.demo.service;


import java.util.List;

import fr.sedona.elastic.demo.model.dto.BeerDTO;

/**
 * Beer search service
 */
public interface BeerSearchService {

    List<BeerDTO> searchByName(String nameQuery);

    List<BeerDTO> searchByCreatorName(String creatorNameQuery);

    List<BeerDTO> searchByCreatorFirstNameAndLastName(String firstName, String lastName);
}
