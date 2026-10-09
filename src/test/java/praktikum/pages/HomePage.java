package praktikum.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.CollectionCondition.size;


public class HomePage {

    private final SelenideElement loginAndRegistrationButton = $$("button").findBy(text("Вход и регистрация"));
    private final SelenideElement logoutButton = $$("button").findBy(text("Выйти"));
    private final SelenideElement createAdButton = $$("button").findBy(text("Разместить объявление"));

    @Step("Нажать «Вход и регистрация»")
    public LoginPage clickLoginAndRegistration() {
        loginAndRegistrationButton.click();
        return new LoginPage();
    }

    @Step("Открыть форму создания объявления")
    public AdPage clickCreateAdvertisement() {
        createAdButton.click();
        return new AdPage();
    }

    @Step("Проверить, что пользователь авторизован")
    public boolean isUserAuthorized() {
        logoutButton.shouldBe(visible);
        return true;
    }

    @Step("Открыть объявление: {name}")
    public AdvertisementDetailsPage openAdvertisement(String name) {
        SelenideElement card = $$(".card")
                .findBy(text(name));

        card.click();

        return new AdvertisementDetailsPage();
    }

    @Step("Проверить отображение объявления: {name}")
    public boolean isAdvertisementDisplayed(String name) {
        $$(".card h2")
                .findBy(exactText(name))
                .shouldBe(visible);

        return true;
    }

    @Step("Проверить, что объявление отсутствует: {name}")
    public boolean isAdvertisementAbsent(String name) {
        $$(".card h2")
                .filterBy(exactText(name))
                .shouldHave(size(0));

        return true;
    }
}