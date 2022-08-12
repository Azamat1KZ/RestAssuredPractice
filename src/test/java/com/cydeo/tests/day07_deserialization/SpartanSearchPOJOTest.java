package com.cydeo.tests.day07_deserialization;

import com.cydeo.pojo.Spartan;
import com.cydeo.pojo.SpartanSearch;
import com.cydeo.utils.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class SpartanSearchPOJOTest extends SpartanTestBase {
    /**
     * Given accept type is json
     * And query param nameContains value is "e"
     * And query param gender value is "Female"
     * When I send get request to /spartans/search
     * Then status code is 200
     * And content type is Json
     * And json response data is as expected
     */
    @Test
    public void spartanSearchToPOJOTest() {
        Map<String, String> queryMap = new HashMap<>();
        queryMap.put("nameContains", "e");
        queryMap.put("gender", "Female");

        Response response = given().accept(ContentType.JSON)
                .and().queryParams(queryMap)
                .when().get("/spartans/search");

        response.prettyPrint();
        assertEquals(200, response.statusCode());
        assertEquals(ContentType.JSON.toString(), response.contentType());

        //deserialization json to SpartanSearch pojo class
        SpartanSearch spartanSearch = response.body().as(SpartanSearch.class);

        //total elements
        System.out.println("total Element = " + spartanSearch.getTotalElement());
        System.out.println("All spartans = " + spartanSearch.getContent());
        System.out.println("First spartan info = " + spartanSearch.getContent().get(0));

        //goto to content list of spartans and get index 1 single spartan
        Spartan secondSpartan = spartanSearch.getContent().get(1);
        System.out.println("Second spartan info" + secondSpartan);
        System.out.println("Second spartan name = " + secondSpartan.getName());
        System.out.println("Second spartan id = " + secondSpartan.getId());

        List<Spartan> spartanData = spartanSearch.getContent();
        System.out.println("spartanData.get(1) = " + spartanData.get(1));

        //read all names
        List<String> names = new ArrayList<>();
        for (Spartan sp : spartanData){
            names.add(sp.getName());
        }
        System.out.println("names = " + names);

        //using functional programming. stream
        List<String> allNames = spartanData.stream().map(sp -> sp.getName()).collect(Collectors.toList());
        System.out.println("allNames = " + allNames);

    }
}
