package praktikum.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$$;

public class AdvertisementDetailsPage {

    private final SelenideElement editButton = $$("button").findBy(exactText("Редактировать объявление"));
    private final SelenideElement deleteButton = $$("button").findBy(exactText("Удалить"));

    @Step("Нажать «Редактировать объявление»")
    public AdPage clickEditAdvertisement() {
        editButton.click();
        return new AdPage();
    }

    @Step("Удалить объявление")
    public AdvertisementDetailsPage clickDeleteAdvertisement() {
        deleteButton.click();
        return this;
    }
}