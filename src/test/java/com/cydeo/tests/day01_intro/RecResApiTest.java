package com.cydeo.tests.day01_intro;

import io.restassured.response.Response;
import org.apiguardian.api.API;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import javax.annotation.meta.When;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * When User sends GET Request to
 * https://reqres.in/api/users
 * <p>
 * Then Response status code should be 200
 * And Response body should contain "George"
 * And Header Content type should be json
 */

public class RecResApiTest {
    String url = "https://reqres.in/api/users";

    @DisplayName("GET all users")
    @Test
    public void usersGetTest() {
        Response response = when().get(url);

        //Then Response status code should be 200
        System.out.println("Status code: " + response.statusCode());
        assertEquals(200, response.statusCode());

        //BDD syntax
        response.then().statusCode(200);
        response.then().assertThat().statusCode(200);

        //And Response body should contain "George"
        System.out.println("Response json body = " + response.asString());
        assertTrue(response.asString().contains("George"));

        //And Header Content type should be application/json
        System.out.println("Content type header value = " + response.contentType());
        assertTrue(response.contentType().contains("application/json"));
    }

    //    When User Sends get request to API Endpoint: "https://reqres.in/api/users/5"
//    Then Response status code should be 200
//    And Response body should contain user info "Charles"
    @DisplayName("GET single user")
    @Test
    public void getSingleUserApiTest() {
        Response response = when().get(url + "/5");
        System.out.println("Status code: " + response.statusCode());
        assertEquals(200, response.statusCode());
        System.out.println("Response json body = " + response.asString());
        assertTrue(response.prettyPrint().contains("Charles"));

    }

    //    When Send get request to API Endpoint: "https://reqres.in/api/users/50"
    //    Then Response status code should be 404
    //    And Response body should contain "{}"


    @DisplayName("GET request to no existing user")
    @Test
    public void getSingleUserNegativeApiTest() {
        Response response = when().get(url + "/50");
        assertEquals(404, response.statusCode());
        assertEquals("{}", response.asString());
    }
}
