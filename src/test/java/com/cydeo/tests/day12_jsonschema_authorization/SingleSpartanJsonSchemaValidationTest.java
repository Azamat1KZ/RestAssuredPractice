package com.cydeo.tests.day12_jsonschema_authorization;

import com.cydeo.utils.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class SingleSpartanJsonSchemaValidationTest extends SpartanTestBase {
    /**
     * given accept type is json
     * and path param id is 15
     * when I send GEt request to /spartans/{id}
     * then status code is 200
     * And json payload/body matches SingleSpartanSchema.json
     */

    @Test
    public void singleSpartanValidationTest() {
        given().accept(ContentType.JSON)
                .and().pathParam("id", 15)
                .when().get("/spartans/{id}")
                .then().statusCode(200)
                .and().body(JsonSchemaValidator.matchesJsonSchema(new File("src/test/resources/jsonschemas/SingleSpartanSchema.json")))
                .and().log().all();
    }

    @DisplayName("GET /spartans and json schema validation")
    @Test
/**
 * given accept type is json
 * when I send GET request /spartans
 * Then status code is 200
 * And json payload/body matches AllSpartansScema.json
 */
    public void allSpartansValidationTest() {
        given().accept(ContentType.JSON)
                .when().get("/spartans")
                .then().statusCode(200)
                .and().body(JsonSchemaValidator.matchesJsonSchema(new File("src/test/resources/jsonschemas/AllSpartansSchema.json")))
                .log().all();
    }

    @Test
    /**
     given accept type is json
     And query params: nameContains "e" and gender "Female"
     when I send GET request to /spartans
     Then status code is 200
     And json payload/body matches SearchSpartansSchema.json
     */
    public void searchSpartanSchemaValidationTest() {
        given().accept(ContentType.JSON)
                .and().queryParam("nameContains", "e")
                .and().queryParam("gender", "Female")
                .when().get("/spartans/search")
                .then().assertThat().statusCode(200)
                .and().body(JsonSchemaValidator.matchesJsonSchema(new File("src/test/resources/jsonschemas/SearchSpartansSchema.json")))
                .log().all();
    }

}
