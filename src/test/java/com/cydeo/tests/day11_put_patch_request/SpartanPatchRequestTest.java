package com.cydeo.tests.day11_put_patch_request;

import com.cydeo.utils.SpartanRestUtils;
import com.cydeo.utils.SpartanTestBase;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import io.restassured.http.ContentType;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class SpartanPatchRequestTest extends SpartanTestBase {
    /**
     * PATCH /api/spartans/{id}
     * Partially Update A Spartan
     * <p>
     * Given accept type is json
     * And content type is json
     * And path param id is 15
     * And json body is
     * {
     * "phone": 1234567425
     * }
     * When i send PATCH request to /spartans/{id}
     * Then status code 204
     */

    @DisplayName("PATCH /spartans/{id}")
    @Test
    public void spartanPatchTest() {
        Map<String, Long> requestMap = new HashMap<>();
        requestMap.put("phone", 7777500040L);

        int spartanId = 15;

        given().accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .pathParam("id", spartanId)
                .and().body(requestMap)
                .when().patch("/spartans/{id}")
                .then().statusCode(204)
                .and().body(emptyOrNullString());

        Map<String, Object> spartanMap = SpartanRestUtils.getSpartan(15);
        System.out.println("spartanMap = " + spartanMap);

        assertThat(spartanMap.get("phone"), equalTo(requestMap.get("phone")));
    }
}
