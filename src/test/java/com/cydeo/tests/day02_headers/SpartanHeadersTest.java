package com.cydeo.tests.day02_headers;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class SpartanHeadersTest {
    String url = "http://54.167.139.116:8000/api/spartans";
    /**
     * /**
     *      • When I send a GET request to
     *      • spartan_base_url/api/spartans
     *      • Then Response STATUS CODE must be 200
     *      • And I Should see all Spartans data in JSON format
     *      */

    @DisplayName("GET /api/spartans and expect Json response")
    @Test
    public void getAllSpartansHeaderTest(){
        when().get(url). //request part
            then().assertThat().statusCode(200).
                //and().contentType("application/json");
                and().contentType(ContentType.JSON);

    }

    /**
     * Given Accept type is application/xml
     • When I send a GET request to
     • spartan_base_url/api/spartans
     • Then Response STATUS CODE must be 200
     • And I Should see all Spartans data in xml format
     */

    @DisplayName("GET api/spartans with req header")
    @Test
    public void acceptTypeHeaderTest(){
        given().accept(ContentType.XML)
                .when().get(url)
                .then().assertThat().statusCode(200)
                .and().contentType(ContentType.XML);
    }

    @DisplayName("GET /api/spartans - read headers")
    @Test
    public void readResponseHeaders(){
        /**
         * Given Accept type is application/json
         • When I send a GET request to
         • spartan_base_url/api/spartans
         • Then Response STATUS CODE must be 200
         • And I Should see all Spartans data in json format
         */
        Response response = given().accept(ContentType.JSON)
                .when().get(url);
        System.out.println("response.getHeader(\"Date\") = " + response.getHeader("Date"));
        System.out.println("response.getHeader(\"Content-type\") = " + response.getHeader("Content-type"));
        System.out.println("response.getHeader(\"Connection\") = " + response.getHeader("Connection"));

        System.out.println();
        System.out.println("response.getHeaders() = " + response.getHeaders());

        //ensure header Keep-alive is present
        assertTrue(response.getHeader("Keep-alive") != null);


    }
}
