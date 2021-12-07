package fr.sedona.elastic.demo.resource;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

@QuarkusTest
class BeerResourceTest {

    @Test
    void testGet() {
        given()
                .when().get("/beers/1")
                .then()
                .statusCode(200)
                .body("id", is(1))
                .body("name", is("1664"))
                .body("brewery.id", is(1))
                .body("brewery.name", is("Kronenbourg"));
    }

    @Test
    void testGetAll() {
        given()
                .when().get("/beers")
                .then()
                .statusCode(200)
                .body("size()", is(10));
    }

}
