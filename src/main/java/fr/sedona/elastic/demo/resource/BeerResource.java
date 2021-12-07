package fr.sedona.elastic.demo.resource;

import fr.sedona.elastic.demo.model.BeerEntity;
import fr.sedona.elastic.demo.model.dto.BeerDTO;
import fr.sedona.elastic.demo.repository.BeerRepository;
import fr.sedona.elastic.demo.service.BeerSearchService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Resource for beers
 */
@Path("/beers")
public class BeerResource {

    private final BeerRepository beerRepository;
    private final BeerSearchService searchService;

    @Inject
    public BeerResource(BeerRepository beerRepository, BeerSearchService searchService) {
        this.beerRepository = beerRepository;
        this.searchService = searchService;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<BeerEntity> getBeers() {
        return this.beerRepository.listAll();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public BeerEntity getBeer(@PathParam("id") long id) {
        return this.beerRepository.findById(id);
    }

    @GET
    @Path("search/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<BeerDTO> searchBeersByName(@PathParam("name") String name) {
        return this.searchService.searchByName(name);
    }
}
