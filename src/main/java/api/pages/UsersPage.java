package api.pages;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class UsersPage {

    private static final String BASE_URL = "https://fake-json-api.mock.beeceptor.com/users";

    // Get all users
    public static Response getUsers() {
        return RestAssured.get(BASE_URL);
    }
}
