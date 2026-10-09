package praktikum.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import praktikum.client.UserClient;
import praktikum.config.TestConfig;
import praktikum.config.TestContext;
import praktikum.model.User;
import praktikum.pages.HomePage;
import praktikum.util.TestDataGenerator;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AuthorizationSteps {

    private final TestContext context;
    private final UserClient userClient;

    public AuthorizationSteps(TestContext context) {
        this.context = context;
        this.userClient = new UserClient();
    }

    @Given("a registered user exists")
    public void registeredUserExists() {
        User user = TestDataGenerator.generateUser();
        context.setUser(user);

        Response response = userClient.signup(user);

        assertEquals(201, response.statusCode());

        String accessToken = userClient
                .signin(user)
                .jsonPath()
                .getString("token.access_token");

        context.setAccessToken(accessToken);
    }

    @When("the user logs in with valid credentials")
    public void userLogsInWithValidCredentials() {
        User user = context.getUser();

        open(TestConfig.BASE_URL);

        new HomePage()
                .clickLoginAndRegistration()
                .enterEmail(user.getEmail())
                .enterPassword(user.getPassword())
                .clickLogin();
    }

    @Then("the user is successfully authorized")
    public void userSuccessfullyAuthorized() {
        assertTrue(
                new HomePage().isUserAuthorized(),
                "После авторизации должна отображаться кнопка «Выйти»"
        );
    }
}