package api.tests;

import api.pages.PostsPage;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PostsTests {

    // Test 1: Validate response for all posts
    @Test
    public void validatePostsResponse() {
        Response response = PostsPage.getPosts();
        Assert.assertTrue(response.jsonPath().getList("$").size() > 0, "Posts list is empty or malformed");
    }

    // Test 2: Validate HTTP status code for the posts API
    @Test
    public void validatePostsStatusCode() {
        Response response = PostsPage.getPosts();
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 200, "Expected status code 200 but got " + statusCode);
    }

    // Test 3: Validate that the response headers contain the expected content type
    @Test
    public void validatePostsResponseHeaders() {
        Response response = PostsPage.getPosts();
        String contentType = response.getHeader("Content-Type");
        Assert.assertTrue(contentType.contains("application/json"), "Expected content type to be application/json");
    }

    // Test 4: Validate post details for each blog post
    @Test
    public void validateResponseStructure() {
        Response response = PostsPage.getPosts();
        Assert.assertTrue(response.jsonPath().getList("$").size() > 0, "Response should contain posts");
        for (int i = 0; i < response.jsonPath().getList("$").size(); i++) {
            Assert.assertNotNull(response.jsonPath().getString("[" + i + "].title"), "Title should not be null");
            Assert.assertNotNull(response.jsonPath().getString("[" + i + "].id"), "ID should not be null");
            Assert.assertNotNull(response.jsonPath().getString("[" + i + "].userId"), "User ID should not be null");
            Assert.assertNotNull(response.jsonPath().getString("[" + i + "].body"), "Body should not be null");
            Assert.assertNotNull(response.jsonPath().getString("[" + i + "].link"), "Link should not be null");
            Assert.assertNotNull(response.jsonPath().getInt("[" + i + "].comment_count"), "Comment count should not be null");
        }
    }

    // Test 5: Validate the number of posts returned
    @Test
    public void validateNumberOfPosts() {
        Response response = PostsPage.getPosts();
        int numberOfPosts = response.jsonPath().getList("$").size();
        Assert.assertTrue(numberOfPosts > 0, "Expected at least one blog post, but got " + numberOfPosts);
    }

    // Test 6: Validate unique post IDs
    @Test
    public void validateUniquePostIds() {
        Response response = PostsPage.getPosts();
        List<Integer> ids = response.jsonPath().getList("id");
        Set<Integer> uniqueIds = new HashSet<>(ids);
        Assert.assertEquals(uniqueIds.size(), ids.size(), "Post IDs should be unique");
    }

    // Test 7: Validate comment counts are non-negative
    @Test
    public void validateCommentCounts() {
        Response response = PostsPage.getPosts();
        for (int i = 0; i < response.jsonPath().getList("$").size(); i++) {
            int commentCount = response.jsonPath().getInt("[" + i + "].comment_count");
            Assert.assertTrue(commentCount >= 0, "Comment count for post ID " + response.jsonPath().getInt("[" + i + "].id") + " should not be negative");
        }
    }
}