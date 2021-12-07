package fr.sedona.elastic.demo.resource;

import fr.sedona.elastic.demo.model.dto.BreweryDTO;
import fr.sedona.elastic.demo.search.dto.BrewerySearchParams;
import fr.sedona.elastic.demo.service.BrewerySearchService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Resource for breweries
 */
@Path("/breweries")
@Produces(MediaType.APPLICATION_JSON)
public class BreweryResource {

    private final BrewerySearchService searchService;

    @Inject
    public BreweryResource(BrewerySearchService searchService) {
        this.searchService = searchService;
    }

    @GET
    @Path("autocomplete/{name}")
    public List<BreweryDTO> autocompleteByName(@PathParam("name") String name) {
        return this.searchService.autocompleteByName(name);
    }

    @POST
    @Path("search")
    @Consumes(MediaType.APPLICATION_JSON)
    public List<BreweryDTO> search(BrewerySearchParams params) {
        return this.searchService.search(params);
    }
}
