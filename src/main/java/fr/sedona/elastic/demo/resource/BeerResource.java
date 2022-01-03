package fr.sedona.elastic.demo.resource;

import fr.sedona.elastic.demo.model.BeerEntity;
import fr.sedona.elastic.demo.model.dto.BeerDTO;
import fr.sedona.elastic.demo.model.mapper.BeerMapper;
import fr.sedona.elastic.demo.repository.BeerRepository;
import fr.sedona.elastic.demo.search.dto.BeerSearchParams;
import fr.sedona.elastic.demo.service.BeerSearchService;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Resource for beers
 */
@Path("/beers")
@Produces(MediaType.APPLICATION_JSON)
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
    public List<BeerDTO> getBeers() {
        return this.beerRepository.listAll()
                .stream()
                .map(beerMapper::toDto)
                .collect(Collectors.toList());
    }

    @GET
    @Path("{id}")
    public BeerDTO getBeer(@PathParam("id") long id) {
        return beerMapper.toDto(this.beerRepository.findById(id));
    }

    @GET
    @Path("search/{name}")
    public List<BeerDTO> searchBeersByName(@PathParam("name") String name) {
        return this.searchService.searchByName(name);
    }

    @GET
    @Path("search/creator/{name}")
    public List<BeerDTO> searchBeersByCreatorName(@PathParam("name") String name) {
        return this.searchService.searchByCreatorName(name);
    }

    @GET
    @Path("search/creator")
    public List<BeerDTO> searchBeersByCreatorFirstNameAndLastName(@QueryParam("firstName") String firstName, @QueryParam("lastName") String lastName) {
        return this.searchService.searchByCreatorFirstNameAndLastName(firstName, lastName);
    }

    @POST
    @Path("search")
    public List<BeerDTO> searchBeersByName(BeerSearchParams searchParams) {
        return this.searchService.search(searchParams);
    }

    @POST
    @Path("aggregate")
    public Map<String, Long> aggregateBeers(BeerSearchParams searchParams) {
        return this.searchService.aggregate(searchParams);
    }

    @PATCH
    @Path("{id}")
    @Transactional
    public Response updateBeerName(@PathParam("id") Long id, @RequestBody String name) {
        BeerEntity beer = beerRepository.findById(id);
        beer.setName(name);
        return Response.ok().build();
    }
}
