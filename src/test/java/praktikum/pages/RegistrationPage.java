package praktikum.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;

public class RegistrationPage {

    private final SelenideElement registrationError = $$("span").findBy(text("Ошибка"));
    private final SelenideElement emailInput = $("input[name='email']");
    private final SelenideElement passwordInput = $("input[name='password']");
    private final SelenideElement submitPasswordInput = $("input[name='submitPassword']");
    private final SelenideElement createAccountButton = $$("button[type='submit']").findBy(text("Создать аккаунт"));

    @Step("Ввести email: {email}")
    public RegistrationPage enterEmail(String email) {
        emailInput.setValue(email);
        return this;
    }

    @Step("Ввести пароль")
    public RegistrationPage enterPassword(String password) {
        passwordInput.setValue(password);
        return this;
    }

    @Step("Повторно ввести пароль")
    public RegistrationPage enterSubmitPassword(String password) {
        submitPasswordInput.setValue(password);
        return this;
    }

    @Step("Создать аккаунт")
    public RegistrationPage clickCreateAccount() {
        createAccountButton.click();
        return this;
    }

    @Step("Зарегистрировать пользователя")
    public RegistrationPage register(String email, String password) {
        enterEmail(email);
        enterPassword(password);
        enterSubmitPassword(password);
        clickCreateAccount();
        return this;
    }

    @Step("Проверить ошибку регистрации")
    public String getRegistrationError() {
        return registrationError
                .shouldBe(visible)
                .getText();
    }
}