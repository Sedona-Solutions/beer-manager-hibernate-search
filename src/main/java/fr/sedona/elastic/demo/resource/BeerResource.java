package fr.sedona.elastic.demo.resource;

import fr.sedona.elastic.demo.model.dto.BeerDTO;
import fr.sedona.elastic.demo.model.mapper.BeerMapper;
import fr.sedona.elastic.demo.repository.BeerRepository;
import fr.sedona.elastic.demo.service.BeerSearchService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Resource for beers
 */
@Path("/beers")
public class BeerResource {

    private final BeerRepository beerRepository;
    private final BeerMapper beerMapper;
    private final BeerSearchService searchService;

    @Inject
    public BeerResource(BeerRepository beerRepository,
                        BeerMapper beerMapper,
                        BeerSearchService searchService) {
        this.beerRepository = beerRepository;
        this.beerMapper = beerMapper;
        this.searchService = searchService;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<BeerDTO> getBeers() {
        return this.beerRepository.listAll()
                .stream().map(entity ->
                        beerMapper.toDto(entity))
                .collect(Collectors.toList()
                );
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public BeerDTO getBeer(@PathParam("id") long id) {
        return beerMapper.toDto(this.beerRepository.findById(id));
    }

    @GET
    @Path("search/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<BeerDTO> searchBeersByName(@PathParam("name") String name) {
        return this.searchService.searchByName(name);
    }
}
