package fr.sedona.elastic.demo.resource;

import fr.sedona.elastic.demo.model.dto.BreweryDTO;
import fr.sedona.elastic.demo.service.BrewerySearchService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Resource for breweries
 */
@Path("/brewery")
public class BreweryResource {

    private final BrewerySearchService searchService;

    @Inject
    public BreweryResource(BrewerySearchService searchService) {
        this.searchService = searchService;
    }

    @GET
    @Path("autocomplete/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<BreweryDTO> autocompleteByName(@PathParam("name") String name) {
        return this.searchService.autocompleteByName(name);
    }
}
