 package praktikum.steps;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import praktikum.config.TestConfig;
import praktikum.config.TestContext;
import praktikum.model.Ad;
import praktikum.pages.HomePage;
import praktikum.util.TestDataGenerator;
import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AdSteps {

    private final TestContext context;

    public AdSteps(TestContext context) {
        this.context = context;
    }

    @When("the user creates a new advertisement")
    public void createNewAdvertisement() {
        Ad ad = TestDataGenerator.generateAd();
        context.setAd(ad);

        new HomePage()
                .clickCreateAdvertisement()
                .createAd(
                        ad.getName(),
                        ad.getDescription(),
                        ad.getPrice()
                );
    }

    @Then("the advertisement is displayed")
    public void advertisementIsDisplayed() {
        Ad ad = context.getAd();

        assertTrue(
                new HomePage().isAdvertisementDisplayed(ad.getName()),
                "The created advertisement should be displayed"
        );
    }

    @When("the user edits the advertisement")
    public void editAdvertisement() {
        Ad originalAd = context.getAd();

        String updatedName = originalAd.getName() + " edited";
        String updatedDescription =
                originalAd.getDescription() + " updated";
        String updatedPrice = "250";

        new HomePage()
                .openAdvertisement(originalAd.getName())
                .clickEditAdvertisement()
                .editAd(
                        updatedName,
                        updatedDescription,
                        updatedPrice
                );

        context.setAd(new Ad(
                updatedName,
                updatedDescription,
                updatedPrice
        ));
    }

    @Then("the updated advertisement is displayed")
    public void updatedAdvertisementIsDisplayed() {
        Ad updatedAd = context.getAd();

        assertTrue(
                new HomePage()
                        .isAdvertisementDisplayed(updatedAd.getName()),
                "The updated advertisement should be displayed"
        );
    }

    @When("the user deletes the advertisement")
    public void deleteAdvertisement() {
        Ad ad = context.getAd();

        new HomePage()
                .openAdvertisement(ad.getName())
                .clickDeleteAdvertisement();

        open(TestConfig.BASE_URL);
    }

    @Then("the advertisement is no longer displayed")
    public void advertisementIsNoLongerDisplayed() {
        Ad ad = context.getAd();

        assertTrue(
                new HomePage().isAdvertisementAbsent(ad.getName()),
                "The deleted advertisement should not be displayed"
        );
    }
}
