package com.cydeo.tests.day05_jsonpath;

import com.cydeo.utils.HrApiTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class HrEmployeesJsonPathTest extends HrApiTestBase {
    /**
     * Given accept type is Json
     * And query param limit is 200
     * when I send GET request to /employees
     * THen I can yse jsonPath to query and read values from json body
     */

    @DisplayName("GET /employees?limit=200 => jsonPath filters")
    @Test
    public void jsonPathEmployeesTest() {
        Response response = given().accept(ContentType.JSON)
                .and().queryParam("limit", 200)
                .when().get("/employees");
        assertEquals(200, response.statusCode());

        //convert/parse json response body to jsonPath object

        JsonPath jsonPath = response.jsonPath();

        System.out.println("first emp firstname = " + jsonPath.getString("items[0].first_name"));
        System.out.println("first emp job title = " + jsonPath.getString("items[0].job_id"));

        //Working with jsonPth lists:
        //get all the emails into a list and print out
        List<String> emails = jsonPath.getList("items.email");
        System.out.println("emails = " + emails);
        //assert  TGATES is among emails:
        assertTrue(emails.contains("TGATES"));

        //get all employee names from department 90

        List<String> namesAtDept90 = jsonPath.getList("items.findAll{it.department_id=90}.first_name");
        System.out.println("namesAtDept90 = " + namesAtDept90);
        System.out.println("namesAtDept90.size() = " + namesAtDept90.size());

        //get all employee first name who work as IT_PROG
        List<String> itProgs = jsonPath.getList("items.findAll{it.job_id=='IT_PROG'}.first_name");
        System.out.println("itProgs = " + itProgs);

        List<String> salaryMore5000 = jsonPath.getList("items.findAll{it.salary >= 5000}.first_name");
        System.out.println("salaryMore5000 = " + salaryMore5000);


        //get employee first name who has max salary
        String firstName = jsonPath.getString("items.max{it.salary}.first_name");
        System.out.println("firstName max salary = " + firstName);
        
        //get employee first name who has min salary
        String minSalary = jsonPath.getString("items.min{it.salary}.first_name");
        System.out.println("minSalary = " + minSalary);
    }
}
