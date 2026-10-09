package praktikum.steps;
import praktikum.pages.RegistrationPage;

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
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class RegistrationSteps {

    private final TestContext context;
    private final UserClient userClient;

    public RegistrationSteps(TestContext context) {
        this.context = context;
        this.userClient = new UserClient();
    }

    @Given("the user opens the home page")
    public void openMainPage() {
        open(TestConfig.BASE_URL);
    }

    @When("the user registers with unique data")
    public void registerWithUniqueData() {
        User user = TestDataGenerator.generateUser();
        context.setUser(user);

        new HomePage()
                .clickLoginAndRegistration()
                .clickNoAccount()
                .register(
                        user.getEmail(),
                        user.getPassword()
                );
    }

    @Then("the user is successfully registered")
    public void userSuccessfullyRegistered() {
        User user = context.getUser();

        Response response = userClient.signin(user);

        assertEquals(201, response.statusCode());

        String accessToken = response
                .jsonPath()
                .getString("token.access_token");

        assertNotNull(accessToken);

        context.setAccessToken(accessToken);
    }


    @When("the user tries to register again with the same email")
    public void tryRegisterAgain() {
        User user = context.getUser();
        open(TestConfig.BASE_URL);
        new HomePage()
                .clickLoginAndRegistration()
                .clickNoAccount()
                .register(
                        user.getEmail(),
                        user.getPassword()
                );
    }

    @Then("the error {string} is displayed")
    public void errorIsDisplayed(String expectedMessage) {
        String actualMessage = new RegistrationPage()
                .getRegistrationError();

        assertEquals(expectedMessage, actualMessage);
    }
}