package com.cydeo.tests.day10_db_vs_api_put_delete;

import com.cydeo.utils.ConfigurationReader;
import com.cydeo.utils.DBUtils;
import com.cydeo.utils.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

public class SpartanAPIAndDBValidationTEst extends SpartanTestBase {
    /**
     given accept is json and content type is json
     and body is:
     {
     "gender": "Male",
     "name": "TestPost1"
     "phone": 1234567425
     }
     When I send POST request to /spartans
     Then status code is 201
     And content type is json
     And "success" is "A Spartan is Born!"
     When I send database query
     SELECT name, gender, phone
     FROM spartans
     WHERE spartan_id = newIdFrom Post request;
     Then name, gender , phone values must match with POST request details
     */

    @DisplayName("POST /api/spartan -< then validate in DB")
    @Test
    public void postNewSpartanThenValidateInDBTest() {
        Map<String, Object> postRequestMap = new HashMap<>();
        postRequestMap.put("gender", "Female");
        postRequestMap.put("name", "Sansa%Stark");
        postRequestMap.put("phone", 1234567425L);

        Response response = given().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .and().body(postRequestMap)
                .when().post("/spartans");

        response.prettyPrint();
        assertThat(response.statusCode(), equalTo(201));
        assertThat(response.contentType(), equalTo("application/json"));

        JsonPath jsonPath = response.jsonPath();
        assertThat(response.jsonPath().getString("success"), equalTo("A Spartan is Born!"));
       // assertThat(response.jsonPath().getString("message"), equalTo("A Spartan is born!"));

        int newSpartanID = response.path("data.id");
        System.out.println("newSpartanID = " + newSpartanID);

        //database steps
        String query = "SELECT NAME, GENDER, PHONE\n" +
                "FROM SPARTANS\n" +
                "WHERE SPARTAN_ID = " + newSpartanID;
        
        String dbUrl = ConfigurationReader.getProperty("spartan.db.url");
        String dbUser = ConfigurationReader.getProperty("spartan.db.userName");
        String dbPwd = ConfigurationReader.getProperty("spartan.db.password");

        //connect to database
        DBUtils.createConnection(dbUrl, dbUser, dbPwd);
        //run the query and get result as Map object
        Map<String, Object> dbMap = DBUtils.getRowMap(query);
        System.out.println("dbMap = " + dbMap);
        
        //assert/validate data from database Matches from post request
        //actual, expected
        assertThat(dbMap.get("NAME"), equalTo(postRequestMap.get("name")));
        assertThat(dbMap.get("GENDER"), equalTo(postRequestMap.get("gender")));
        assertThat(dbMap.get("PHONE"), equalTo(postRequestMap.get("phone")+""));

        //disconnect from database
        DBUtils.destroy();
    }
}
