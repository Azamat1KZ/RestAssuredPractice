package com.cydeo.tests.day14_specifications;

import com.cydeo.utils.BookItAPITestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;

public class BookItSpecTest extends BookItAPITestBase {
    /**
     Given accept type is json
     And header Authorization is access_token of teacher
     When I send GET request to /api/teachers/me
     Then status code is 200
     And content type is json
     And teacher info is presented in payload
     */
    @Test
        public void teacherInfoTest() {
    Map<String, ?> teacherMap = given().spec(teacherReqSpec)
            .when().get("/api/teachers/me")
            .then().spec(responseSpec)
            .and().extract().body().as(Map.class);

        Assertions.assertEquals(1816, teacherMap.get("id"));
        Assertions.assertEquals("Barbabas", teacherMap.get("firstName"));
        Assertions.assertEquals("Lyst", teacherMap.get("lastName"));
        Assertions.assertEquals("teacher", teacherMap.get("role"));

        //by using hamcrest matchers
        given().spec(teacherReqSpec)
                .when().get("/api/teachers/me")
                .then().spec(responseSpec)
                .and().body("id",          is(1816),
                        "firstName",  is("Barbabas"),
                        "lastName",           is("Lyst"),
                        "role",               is("teacher"));
    }
}
