package fr.sedona.elastic.demo.resource;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

import org.junit.jupiter.api.Test;

import fr.sedona.elastic.demo.search.dto.BeerSearchParams;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;

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

    @Test
    void testSearchByName() {
        given()
                .when().get("/beers/search/elephant")
                .then()
                .statusCode(200)
                .body("size()", is(1))
                .body("[0].name", is("Elephant 1959"));
    }

    @Test
    void testSearch() {
        BeerSearchParams searchParams = new BeerSearchParams();
        searchParams.setName("Red");
        searchParams.setFamily("artisanale-forte");
        searchParams.setAlcoholLevelLowerBound(7.0f);
        searchParams.setAlcoholLevelUpperBound(8.0f);

        given()
                .body(searchParams)
                .contentType(ContentType.JSON)
                .when().post("/beers/search")
                .then()
                .statusCode(200)
                .body("size()", is(1))
                .body("[0].name", is("Red is Dead"))
                .body("[0].alcoholLevel", is(7.2f))
                .body("[0].brewery.type", is("CRAFT"));
    }

}
