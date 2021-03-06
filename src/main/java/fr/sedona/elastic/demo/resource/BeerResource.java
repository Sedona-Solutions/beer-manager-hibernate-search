package fr.sedona.elastic.demo.resource;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import fr.sedona.elastic.demo.model.BeerEntity;
import fr.sedona.elastic.demo.repository.BeerRepository;

/**
 * Resource for beers
 */
@Path("/beers")
public class BeerResource {

    private BeerRepository beerRepository;

    @Inject
    public BeerResource(BeerRepository beerRepository) {
        this.beerRepository = beerRepository;
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
}
