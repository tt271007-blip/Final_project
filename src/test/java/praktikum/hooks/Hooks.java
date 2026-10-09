package praktikum.hooks;

import com.codeborne.selenide.SelenideElement;
import io.cucumber.java.After;
import praktikum.config.TestConfig;
import praktikum.config.TestContext;
import praktikum.model.Ad;
import praktikum.pages.HomePage;

import java.time.Duration;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.closeWebDriver;
import static com.codeborne.selenide.WebDriverRunner.hasWebDriverStarted;

public class Hooks {

    private final TestContext context;

    public Hooks(TestContext context) {
        this.context = context;
    }

    @After
    public void tearDown() {
        try {
            Ad ad = context.getAd();

            if (ad != null && hasWebDriverStarted()) {
                open(TestConfig.BASE_URL);

                SelenideElement advertisementTitle =
                        $$(".card h2").findBy(exactText(ad.getName()));

                try {
                    advertisementTitle.shouldBe(
                            visible,
                            Duration.ofSeconds(5)
                    );

                    new HomePage()
                            .openAdvertisement(ad.getName())
                            .clickDeleteAdvertisement();

                    open(TestConfig.BASE_URL);
                } catch (com.codeborne.selenide.ex.ElementNotFound e) {
                    // Объявление уже удалено или не было создано.
                }
            }
        } catch (Exception e) {
            System.err.println(
                    "Не удалось очистить тестовое объявление: "
                            + e.getMessage()
            );
        } finally {
            if (hasWebDriverStarted()) {
                closeWebDriver();
            }
        }
    }
}