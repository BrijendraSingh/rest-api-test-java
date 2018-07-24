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
        assert false;
    }

    @Test
    public void shouldContainProjectNameInReposEndpoint(){
        assert false;
    }

    @Test
    public void shouldContainLoginNameInFollowersEndpoint(){
        assert false;
    }

}
