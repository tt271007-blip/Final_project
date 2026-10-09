package praktikum.config;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

public class RestAssuredConfig {

    public static RequestSpecification baseSpec() {
        return new RequestSpecBuilder()
                .setBaseUri(TestConfig.API_URL)
                .setContentType("application/json")
                .build();
    }

    private RestAssuredConfig() {
    }
}