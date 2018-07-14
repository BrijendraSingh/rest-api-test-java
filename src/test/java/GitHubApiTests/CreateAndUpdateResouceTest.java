package GitHubApiTests;

import io.restassured.RestAssured;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import utilities.AppConfig;

import java.io.IOException;

public class CreateAndUpdateResouceTest {
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
