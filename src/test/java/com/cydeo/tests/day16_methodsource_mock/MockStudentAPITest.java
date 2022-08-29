package com.cydeo.tests.day16_methodsource_mock;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;
import static io.restassured.RestAssured.*;

public class MockStudentAPITest {
    @BeforeAll
    public static void setUp() {
        baseURI = "https://d3120ca5-966e-4912-9d5f-4044b2ed47c4.mock.pstmn.io";

    }
    @DisplayName("GET /student/1")
    @Test
    public void testStudent(){
        given().accept(ContentType.JSON)
                .when().get("/students/1")
                .then().assertThat().statusCode(200)
                .log().all();

    }
}
