package praktikum.util;

import praktikum.model.Ad;
import praktikum.model.User;

import java.util.UUID;

public class TestDataGenerator {

    public static User generateUser() {
        String uniqueId = UUID.randomUUID()
                .toString()
                .substring(0, 8);

        String email = "test_" + uniqueId + "@gmail.com";
        String password = "Pass_" + uniqueId;

        return new User(
                email,
                password,
                password
        );
    }
    public static Ad generateAd() {
        String uniqueId = UUID.randomUUID()
                .toString()
                .substring(0, 8);

        return new Ad(
                "Тестовое объявление " + uniqueId,
                "Описание объявления " + uniqueId,
                "100"
        );
    }

    private TestDataGenerator() {
    }
}