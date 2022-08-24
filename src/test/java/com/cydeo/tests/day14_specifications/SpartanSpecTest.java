package com.cydeo.tests.day14_specifications;

import com.cydeo.utils.BookItAPITestBase;
import com.cydeo.utils.ConfigurationReader;
import com.cydeo.utils.SpartanSecureTestBase;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.jupiter.api.Test;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;

import io.restassured.response.Response;

import java.util.Map;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;


public class SpartanSpecTest extends SpartanSecureTestBase {

//    RequestSpecification requestSpec = given().accept(ContentType.JSON)
//            .and().auth().basic("admin", "admin"); //this is now sored in BaseClass (SpartanSecureTestBase). click on signature.

    ResponseSpecification responseSpec = expect().statusCode(200)
            .and().contentType(ContentType.JSON); // we can leave it here, but it's not a best practice, anyway inheritance works.

    @Test
    public void allSpartansTest() {
//        given().accept(ContentType.JSON)
//                .and().auth().basic("admin", "admin")
        given().spec(requestSpec)
                .when().get("/spartans")
                .then().spec(responseSpec).log().all();
    }

    @Test
    public void singleSpartanTest() {
        given().spec(requestSpec)
                .and().pathParam("id", 15)
                .when().get("/spartans/{id}")
                .then().spec(responseSpec).log().all();
    }
}

