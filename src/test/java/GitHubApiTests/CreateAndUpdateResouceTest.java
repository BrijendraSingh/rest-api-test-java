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
    private static String ACCESS_TOKEN = "";

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
        assert false;
    }

    @Test
    public void shouldBeAbleToEditAnIssue(){
        assert false;
    }

    @Test
    public void shouldBeAbleToLockAnIssue(){
        assert false;
    }

    @Test
    public void shouldBeAbleToUnlockAnIssue(){
        assert false;
    }
}
