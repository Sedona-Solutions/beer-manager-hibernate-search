package fr.sedona.elastic.demo.resource;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import fr.sedona.elastic.demo.model.dto.BreweryDTO;
import fr.sedona.elastic.demo.search.dto.BrewerySearchParams;
import fr.sedona.elastic.demo.service.BrewerySearchService;

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

    @POST
    @Path("search-json")
    @Consumes(MediaType.APPLICATION_JSON)
    public String searchJson(BrewerySearchParams params) {
        return this.searchService.searchJson(params);
    }
}
