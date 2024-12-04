package api.pages;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class TodosPage {
    private static final String BASE_URL = "https://dummy-json.mock.beeceptor.com/todos";

    // Get all todos
    public static Response getTodos() {
        return RestAssured.get(BASE_URL);
    }
}