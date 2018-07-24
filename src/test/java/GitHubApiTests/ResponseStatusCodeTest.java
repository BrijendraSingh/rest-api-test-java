package GitHubApiTests;

import io.restassured.RestAssured;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import utilities.AppConfig;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.lessThan;

public class ResponseStatusCodeTest {

    @Before
    public void setUp() throws IOException {
        RestAssured.baseURI = new AppConfig().getProperty("apiUrl");
    }

    @After
    public void tearDown(){
    }

    @Test
    public void shouldReturn200WhenResourceFound(){
        when().get("/users").then().statusCode(200);
    }

    @Test
    public void shouldReturn404WhenResourceNotFound(){
        when().get("/userz").then().statusCode(404);
    }

    @Test
    public void testResponseTimeOfUsersEndpointIsLessThan3000ms(){
        when().get("/users").then().time(lessThan(3000L), TimeUnit.MILLISECONDS);
    }

}
