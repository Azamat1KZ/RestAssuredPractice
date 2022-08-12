package com.cydeo.tests.day02_headers;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;


public class SpartanApiHelloTest {
    String url = "http://54.167.139.116:8000/api/hello";

    @DisplayName("GET api/hello")
    @Test
    public void helloApiTest() {
        Response response = when().get(url); //comes from RestAssured library. Static imports works here
        System.out.println("status code = " + response.statusCode());
        assertEquals(200, response.statusCode());

        response.prettyPrint();
        assertEquals("Hello from Sparta", response.body().asString());

        System.out.println("Content type = " + response.contentType());
        assertEquals("text/plain;charset=UTF-8", response.contentType());


    }

    @DisplayName("GET Api/hello - bdd")
    @Test
    public void helloApiDddTest() {
        when().get(url)
                .then().assertThat().statusCode(200)
                .and().contentType("text/plain;charset=UTF-8");
    }
}
