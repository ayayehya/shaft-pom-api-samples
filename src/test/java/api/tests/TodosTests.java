package api.tests;

import api.pages.TodosPage;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TodosTests {

    // Test 1: Validate response for all todos
    @Test
    public void validateTodosResponse() {
        Response response = TodosPage.getTodos();
        Assert.assertTrue(response.jsonPath().getList("$").size() > 0, "To Do list is empty or malformed");
    }

    // Test 2: Validate HTTP status code for the todos API
    @Test
    public void validateTodosStatusCode() {
        Response response = TodosPage.getTodos();
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 200, "Expected status code 200 but got " + statusCode);
    }

    // Test 3: Validate that the response headers contain the expected content type
    @Test
    public void validateTodosResponseHeaders() {
        Response response = TodosPage.getTodos();
        String contentType = response.getHeader("Content-Type");
        Assert.assertTrue(contentType.contains("application/json"), "Expected content type to be application/json");
    }

    // Test 4: Validate todo details for each task
    @Test
    public void validateResponseStructure() {
        Response response = TodosPage.getTodos();
        Assert.assertTrue(response.jsonPath().getList("$").size() > 0, "Response should contain todos");
        for (int i = 0; i < response.jsonPath().getList("$").size(); i++) {
            Assert.assertNotNull(response.jsonPath().getString("[" + i + "].title"), "Title should not be null");
            Assert.assertNotNull(response.jsonPath().getString("[" + i + "].id"), "ID should not be null");
            Assert.assertNotNull(response.jsonPath().getString("[" + i + "].userId"), "User ID should not be null");
            Assert.assertNotNull(response.jsonPath().getBoolean("[" + i + "].completed"), "Completed status should not be null");
        }
    }

    // Test 5: Validate the number of todos returned
    @Test
    public void validateNumberOfTodos() {
        Response response = TodosPage.getTodos();
        int numberOfTodos = response.jsonPath().getList("$").size();
        Assert.assertTrue(numberOfTodos > 0, "Expected at least one To Do item, but got " + numberOfTodos);
    }

    // Test 6: Validate unique todo IDs
    @Test
    public void validateUniqueTodoIds() {
        Response response = TodosPage.getTodos();
        List<Integer> ids = response.jsonPath().getList("id");
        Set<Integer> uniqueIds = new HashSet<>(ids);
        Assert.assertEquals(uniqueIds.size(), ids.size(), "To Do IDs should be unique");
    }

    // Test 7: Validate completion status of todos
    @Test
    public void validateCompletionStatus() {
        Response response = TodosPage.getTodos();
        for (int i = 0; i < response.jsonPath().getList("$").size(); i++) {
            boolean completed = response.jsonPath().getBoolean("[" + i + "].completed");
            Assert.assertNotNull(completed, "Completed status should not be null for todo ID " + response.jsonPath().getInt("[" + i + "].id"));
        }
    }
}