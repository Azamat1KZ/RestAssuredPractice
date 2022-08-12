package com.cydeo.tests.day03_parameters;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.annotation.meta.When;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class SpartanApiWithQueryParamsTest {

    /**
     * Given Accept type is Json
     * And query parameter values are:
     * gender|Female
     * nameContains|e
     * When user sends GET request to /api/spartans/search
     * Then response status code should be 200
     * And response content-type: application/json
     * And "Female" should be in response payload
     * And "Janette" should be in response payload
     */
    String url = "http://54.167.139.116:8000/api/spartans/search";

    @DisplayName("GET /api/spartans/search for query params")
    @Test
    public void searchForSpartanTest() {
        Response response = given().log().all().accept(ContentType.JSON)
                .and().queryParam("gender", "Female")
                .and().queryParam("nameContains", "e")
                .when().get(url);
        //verify the status code
        assertEquals(200, response.statusCode());

        //verify response header
        assertEquals("application/json", response.contentType());

        assertTrue(response.asString().contains("Female"));
        assertTrue(response.asString().contains("Janette"));
    }


}
