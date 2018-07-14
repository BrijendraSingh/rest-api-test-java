package GitHubApiTests;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import utilities.AppConfig;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.is;

public class CreateAndUpdateResouceTest {
    private static String USER_NAME = "";
    private static String ACCESS_TOKEN = "token eb05958b396ba12be0f34d3df93d6cd972138a0f";

    @Before
    public void setUp() throws IOException {
        RestAssured.baseURI = new AppConfig().getProperty("apiUrl");
        USER_NAME = new AppConfig().getProperty("userName");
    }

    @After
    public void tearDown(){
    }

    @Test
    public void shouldBeAbleToCreateAnIssue(){
        String issueDetails = "{\"title\": \"create_issue\", \"body\":\"this is a sample issue\"}";
        String path = "repos/" + USER_NAME +"/rest-api-test-java/issues";

        RequestSpecification request = RestAssured.given();

        request.header("Content-Type", "application/json");
        request.header("Authorization", ACCESS_TOKEN);

        request.body(issueDetails);

        Response response = request.post(path);
        assertThat(response.statusCode(), is(201));
    }

    @Test
    public void shouldBeAbleToEditAnIssue(){
        // create an issue, get the list of issues
        // and then pick the first issue to modify that issue

        String issueListPath = "repos/" + USER_NAME +"/rest-api-test-java/issues";

        RequestSpecification request = RestAssured.given();

        request.header("Content-Type", "application/json");
        request.header("Authorization", ACCESS_TOKEN);

        Response response = request.get(issueListPath);

        JsonPath jsonPath = new JsonPath(response.asString());
        ArrayList<Map<String, Object>> jsonList = jsonPath.get(".");

        String issueNumber = jsonList.get(0).get("number").toString();

        String issueDetails = "{\"title\": \"edit_issue\", \"body\":\"this is an edited issue\"}";
        String issueEditPath = "repos/" + USER_NAME +"/rest-api-test-java/issues/" + issueNumber;

        request.body(issueDetails);
        response = request.patch(issueEditPath);
        assertThat(response.statusCode(), is(200));
    }

    @Test
    public void shouldBeAbleToLockAnIssue(){
        // create an issue to ensure there is at least one issue,
        // get the first issue number and then lock it

        String issueListPath = "repos/" + USER_NAME +"/rest-api-test-java/issues";

        RequestSpecification request = RestAssured.given();

        request.header("Content-Type", "application/json");
        request.header("Accept", "application/vnd.github.sailor-v-preview+json");
        request.header("Authorization", ACCESS_TOKEN);

        Response response = request.get(issueListPath);

        JsonPath jsonPath = new JsonPath(response.asString());
        ArrayList<Map<String, Object>> jsonList = jsonPath.get(".");

        String issueNumber = jsonList.get(0).get("number").toString();
        String issueDetails = "{\"lock_reason\": \"too heated\"}";
        String issueLockPath = "repos/" + USER_NAME +"/rest-api-test-java/issues/" + issueNumber
                + "/lock";

        request.body(issueDetails);
        response = request.put(issueLockPath);
        assertThat(response.statusCode(), is(204));

    }

    @Test
    public void shouldBeAbleToUnlockAnIssue(){

        // create an issue, get the issue number, lock it and then unlock


        String issueListPath = "repos/" + USER_NAME +"/rest-api-test-java/issues";

        RequestSpecification request = RestAssured.given();

        request.header("Content-Type", "application/json");
        request.header("Accept", "application/vnd.github.sailor-v-preview+json");
        request.header("Authorization", ACCESS_TOKEN);

        Response response = request.get(issueListPath);

        JsonPath jsonPath = new JsonPath(response.asString());
        ArrayList<Map<String, Object>> jsonList = jsonPath.get(".");

        String issueNumber = jsonList.get(0).get("number").toString();
        String issueDetails = "{\"lock_reason\": \"too heated\"}";
        String issueLockPath = "repos/" + USER_NAME +"/rest-api-test-java/issues/" + issueNumber
                + "/lock";

        request.body(issueDetails);
        response = request.delete(issueLockPath);
        assertThat(response.statusCode(), is(204));

    }
}
