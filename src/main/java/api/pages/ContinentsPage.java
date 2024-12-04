package api.pages;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class ContinentsPage {
    private static final String BASE_URL = "https://dummy-json.mock.beeceptor.com/continents";

    // Get all continents
    public static Response getContinents() {
        return RestAssured.get(BASE_URL);
    }
}