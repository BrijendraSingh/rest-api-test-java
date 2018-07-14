package GitHubApiTests;

import io.restassured.RestAssured;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import utilities.AppConfig;

import java.io.IOException;

public class ResponseBodyTest {

    @Before
    public void setUp() throws IOException {
        RestAssured.baseURI = new AppConfig().getProperty("apiUrl");
    }

    @After
    public void tearDown(){
    }

    @Test
    public void shouldContainNameInUsersEndpoint(){
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
