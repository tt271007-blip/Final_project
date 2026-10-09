package praktikum.client;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import praktikum.config.RestAssuredConfig;
import praktikum.model.LoginRequest;
import praktikum.model.User;

import static io.restassured.RestAssured.given;

public class UserClient {

    private static final String SIGNUP = "/signup";
    private static final String SIGNIN = "/signin";

    @Step("Создание пользователя через API")
    public Response signup(User user) {
        return given()
                .spec(RestAssuredConfig.baseSpec())
                .body(user)
                .when()
                .post(SIGNUP);
    }

    @Step("Авторизация пользователя через API")
    public Response signin(User user) {
        LoginRequest request = new LoginRequest(
                user.getEmail(),
                user.getPassword()
        );

        return given()
                .spec(RestAssuredConfig.baseSpec())
                .body(request)
                .when()
                .post(SIGNIN);
    }
}