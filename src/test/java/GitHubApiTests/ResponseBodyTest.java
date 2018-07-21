package GitHubApiTests;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.CustomTypeSafeMatcher;
import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import utilities.AppConfig;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static io.restassured.path.json.JsonPath.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class ResponseBodyTest {

    private static String USER_NAME = "";

    @Before
    public void setUp() throws IOException {
        RestAssured.baseURI = new AppConfig().getProperty("apiUrl");
        USER_NAME = new AppConfig().getProperty("userName");
    }

    @After
    public void tearDown(){
    }

    @Test
    public void shouldContainIdInUsersEndpoint(){
        given()
                .pathParam("login", USER_NAME).
        when()
                .get("/users/{login}").
        then()
                .assertThat()
                .body("id", equalTo(9004987));
    }

    @Test
    public void shouldContainProjectNameInReposEndpoint(){
        Map<String, Object> expectedItem = new HashMap<>();
        expectedItem.put("name", "rest-api-test-java");

        Response response = given()
                .pathParam("userName", USER_NAME).
                        when()
                .request("GET", "users/{userName}/repos").
                        then()
                .extract()
                .response();

        List<Map<String, String>> repoDetails = from(response.asString()).getList(".");
        assertThat(repoDetails, hasItem(new CustomTypeSafeMatcher<Map<String,String>>("an entrySet that contains " + expectedItem.entrySet()) {
            @Override
            protected boolean matchesSafely(Map<String, String> o) {
                return hasItems(expectedItem.entrySet().toArray()).matches(o.entrySet());
            }
        }));

//        JsonPath jsonPath = new JsonPath(response.asString()).setRoot(".");
//        System.out.println(jsonPath);
    }

    @Test
    public void shouldContainLoginNameInFollowersEndpoint(){
        Map<String, Object> expectedItem = new HashMap<>();
        expectedItem.put("login", "nalinikanth");

        Response response = given()
                                .pathParam("userName", USER_NAME).
                            when()
                                .get("users/{userName}/followers").
                            then()
                                .extract()
                                .response();

        assertThat(response.getHeader("X-RateLimit-Limit"), is("60"));
        assertThat(response.getContentType(), containsString("application/json"));

        JsonPath jsonPath = new JsonPath(response.asString());
        ArrayList<Map<String, Object>> jsonList = jsonPath.get(".");
        assertThat(jsonList.get(0), hasKey("login"));
        assertThat(jsonList.get(0).get("login"), is(expectedItem.get("login")));

    }

}
