package praktikum.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Condition.text;

public class LoginPage {

    private final SelenideElement noAccountButton = $$("button").findBy(text("Нет аккаунта"));
    private final SelenideElement emailInput = $("input[name='email']");
    private final SelenideElement passwordInput = $("input[name='password']");
    private final SelenideElement loginButton = $$("button").findBy(text("Войти"));

    @Step("Перейти к регистрации")
    public RegistrationPage clickNoAccount() {
        noAccountButton.click();
        return new RegistrationPage();
    }

    @Step("Ввести email: {email}")
    public LoginPage enterEmail(String email) {
        emailInput.setValue(email);
        return this;
    }

    @Step("Ввести пароль")
    public LoginPage enterPassword(String password) {
        passwordInput.setValue(password);
        return this;
    }

    @Step("Нажать «Войти»")
    public HomePage clickLogin() {
        loginButton.click();
        return new HomePage();
    }
}