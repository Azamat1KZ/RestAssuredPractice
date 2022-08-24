package com.cydeo.tests.day12_jsonschema_authorization;

import com.cydeo.utils.SpartanSecureTestBase;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class SpartanBasicAuthTest extends SpartanSecureTestBase {
    @DisplayName("GET /spartans with basic auth")
    @Test
    public void getSpartansWithAuthTest() {
        given().accept(ContentType.JSON)
                .and().auth().basic("admin", "admin")
                .when().get("/spartans")
                .then().assertThat().statusCode(200)
                .and().contentType(ContentType.JSON)
                .and().log().all();
    }

    @Test
    public void getAllSpartansUnAuthorizedTest() {
        /**
         given accept type is json
         When user sends GET request to /spartans
         Then status code is 401
         And content type is json
         And log response
         */
        given().accept(ContentType.JSON)
                .when().get("/spartans")
                .then().statusCode(401)
                .and().contentType(ContentType.JSON)
                .and().body("message", equalTo("Unauthorized"))
                .log().all();
    }
}
