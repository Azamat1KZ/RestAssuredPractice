package com.cydeo.tests.day06_xmlpath_deserialization;

import com.cydeo.pojo.Spartan;
import com.cydeo.utils.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class SpartanToPojoTest extends SpartanTestBase {
    @DisplayName("GET /spartan/{id} -> pojo object")
    @Test
    public void spartanToPojoTest() {
        Response response = given().accept(ContentType.JSON)
                .and().pathParam("id", 10)
                .when().get("/spartans/{id}");

        response.prettyPrint();

        assertEquals(200, response.statusCode());

        Spartan spartan = response.as(Spartan.class);
        System.out.println("spartan = " + spartan);

        System.out.println("id = " + spartan.getId());
        System.out.println("name = " + spartan.getName());
        System.out.println("gender = " + spartan.getGender());
        System.out.println("phone = " + spartan.getPhone());
    }
}
