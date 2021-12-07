package fr.sedona.elastic.demo.resource;

import fr.sedona.elastic.demo.search.dto.BrewerySearchParams;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

@QuarkusTest
class BreweryResourceTest {

    @Test
    void autocomplete_should_return_breweries_starting_with_input() {
        given()
        .when()
                .get("/breweries/autocomplete/carl")
        .then()
                .statusCode(200)
                .body("[0].id", is(4))
                .body("[0].name", is("Carlsberg"));
    }

    @Test
    void search_should_return_matching_breweries() {
        BrewerySearchParams searchParams = new BrewerySearchParams();
        searchParams.setOrigin("Monde");

        given()
                .contentType(ContentType.JSON)
                .body(searchParams)
        .when()
                .post("/breweries/search")
        .then()
                .statusCode(200)
                .body("size()", is(1))
                .body("[0].id", is(4))
                .body("[0].name", is("Carlsberg"))
                .body("[0].country", is("Danemark"));
    }

}
