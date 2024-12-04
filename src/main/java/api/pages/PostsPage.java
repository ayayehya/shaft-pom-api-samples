package api.pages;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class PostsPage {
    private static final String BASE_URL = "https://dummy-json.mock.beeceptor.com/posts";

    // Get all posts
    public static Response getPosts() {
        return RestAssured.get(BASE_URL);
    }
}