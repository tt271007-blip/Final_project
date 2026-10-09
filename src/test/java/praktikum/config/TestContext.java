package praktikum.config;

import praktikum.model.Ad;
import praktikum.model.User;

public class TestContext {

    private Ad ad;
    private User user;
    private String accessToken;

    public User getUser() {

        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public Ad getAd() {
        return ad;
    }

    public void setAd(Ad ad) {
        this.ad = ad;
    }
}