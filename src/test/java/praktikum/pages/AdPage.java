package praktikum.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class AdPage {

    private final SelenideElement nameInput =
            $("input[name='name']");

    private final SelenideElement descriptionInput =
            $("textarea[name='description']");

    private final SelenideElement priceInput =
            $("input[name='price']");

    // Кнопка создания объявления
    private final SelenideElement publishButton =
            $$("button[type='submit']")
                    .findBy(text("Опубликовать"));

    // Кнопка сохранения изменений
    private final SelenideElement saveChangesButton =
            $$("button[type='submit']")
                    .findBy(text("Сохранить изменения"));

    @Step("Ввести название объявления: {name}")
    public AdPage enterName(String name) {
        nameInput.setValue(name);
        return this;
    }

    @Step("Ввести описание объявления")
    public AdPage enterDescription(String description) {
        descriptionInput.setValue(description);
        return this;
    }

    @Step("Ввести стоимость: {price}")
    public AdPage enterPrice(String price) {
        priceInput.setValue(price);
        return this;
    }

    @Step("Создать объявление")
    public HomePage createAd(
            String name,
            String description,
            String price
    ) {
        fillForm(name, description, price);
        publishButton.click();

        return new HomePage();
    }

    @Step("Редактировать объявление и сохранить изменения")
    public HomePage editAd(
            String name,
            String description,
            String price
    ) {
        fillForm(name, description, price);
        saveChangesButton.click();

        return new HomePage();
    }

    private void fillForm(
            String name,
            String description,
            String price
    ) {
        enterName(name);
        enterDescription(description);
        enterPrice(price);
    }
}