package api.tests;

import api.pages.UsersPage;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UsersTest {

    // Test 1: Validate response for all users
    @Test
    public void validateUsersResponse() {
        Response response = UsersPage.getUsers();
        Assert.assertTrue(response.jsonPath().getList("$").size() > 0, "Users list is empty or malformed");
    }

    // Test 2: Validate HTTP status code for the users API
    @Test
    public void validateUsersStatusCode() {
        Response response = UsersPage.getUsers();
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 200, "Expected status code 200 but got " + statusCode);
    }

    // Test 3: Validate that the response headers contain the expected content type
    @Test
    public void validateUsersResponseHeaders() {
        Response response = UsersPage.getUsers();
        String contentType = response.getHeader("Content-Type");
        Assert.assertTrue(contentType.contains("application/json"), "Expected content type to be application/json");
    }

    // Test 4: Validate user details for each user
    @Test
    public void validateResponseStructure() {
        Response response = UsersPage.getUsers();
        Assert.assertTrue(response.jsonPath().getList("$").size() > 0, "Response should contain users");
        for (int i = 0; i < response.jsonPath().getList("$").size(); i++) {
            Assert.assertNotNull(response.jsonPath().getString("[" + i + "].name"), "Name should not be null");
            Assert.assertNotNull(response.jsonPath().getString("[" + i + "].email"), "Email should not be null");
            Assert.assertNotNull(response.jsonPath().getString("[" + i + "].id"), "ID should not be null");
            Assert.assertNotNull(response.jsonPath().getString("[" + i + "].company"), "Company should not be null");
        }
    }

    // Test 5: Validate that the response body contains a specific user ID (ID: 1)
    @Test
    public void validateUserIdInResponse() {
        Response response = UsersPage.getUsers();
        int userCount = response.jsonPath().getList("$").size();
        boolean userExists = false;
        for (int i = 0; i < userCount; i++) {
            if (response.jsonPath().getInt("[" + i + "].id") == 1) {
                userExists = true;
                break;
            }
        }
        Assert.assertTrue(userExists, "User with ID 1 is not present in the response");
    }

    // Test 6: Validate the number of users returned
    @Test
    public void validateNumberOfUsers() {
        Response response = UsersPage.getUsers();
        int numberOfUsers = response.jsonPath().getList("$").size();
        Assert.assertEquals(numberOfUsers, 10, "Expected 10 users, but got " + numberOfUsers);
    }

    // Test 7: Validate if the users list contains valid fields (e.g., address)
    @Test
    public void validateUsersFields() {
        Response response = UsersPage.getUsers();
        String address = response.jsonPath().getString("[0].address");
        Assert.assertNotNull(address, "User address is missing");
        Assert.assertFalse(address.isEmpty(), "User address should not be empty");
    }

    // Test 8: Validate that the response contains valid data types
    @Test
    public void validateDataTypes() {
        Response response = UsersPage.getUsers();
        int userCount = response.jsonPath().getList("$").size();
        for (int i = 0; i < userCount; i++) {
            int id = response.jsonPath().getInt("[" + i + "].id");
            String email = response.jsonPath().getString("[" + i + "].email");
            Assert.assertTrue(id > 0, "ID should be a positive number");
            Assert.assertTrue(email.contains("@"), "Email should contain '@'");
        }
    }

    // Test 9: Validate the photo URLs for users
    @Test
    public void validateUserPhotos() {
        Response response = UsersPage.getUsers();
        int userCount = response.jsonPath().getList("$").size();
        for (int i = 0; i < userCount; i++) {
            String photoUrl = response.jsonPath().getString("[" + i + "].photo");
            Assert.assertNotNull(photoUrl, "Photo URL should not be null");
            Assert.assertTrue(photoUrl.startsWith("http"), "Photo URL should be a valid URL");
        }
    }

    // Test 10: Validate non-empty fields for name and email
    @Test
    public void validateNonEmptyFields() {
        Response response = UsersPage.getUsers();
        int userCount = response.jsonPath().getList("$").size();
        for (int i = 0; i < userCount; i++) {
            String name = response.jsonPath().getString("[" + i + "].name");
            String email = response.jsonPath().getString("[" + i + "].email");
            Assert.assertFalse(name.isEmpty(), "Name should not be empty");
            Assert.assertFalse(email.isEmpty(), "Email should not be empty");
        }
    }

    // Test 11: Validate unique user IDs and Emails
    @Test
    public void validateUniqueUsers() {
        Response response = UsersPage.getUsers();
        List<String> ids = response.jsonPath().getList("id");
        List<String> emails = response.jsonPath().getList("email");
        Set<String> uniqueIds = new HashSet<>(ids);
        Set<String> uniqueEmails = new HashSet<>(emails);
        Assert.assertEquals(uniqueIds.size(), ids.size(), "User IDs should be unique");
        Assert.assertEquals(uniqueEmails.size(), emails.size(), "User Emails should be unique");
    }

    // Test 12: Validate the response time
    @Test
    public void validateResponseTime() {
        Response response = UsersPage.getUsers();
        long responseTime = response.getTime();
        Assert.assertTrue(responseTime < 2000, "Response time should be less than 2000ms");
    }

    // Test 13: Validate phone numbers
    @Test
    public void validatePhoneNumber() {
        Response response = UsersPage.getUsers();
        int userCount = response.jsonPath().getList("$").size();
        for (int i = 0; i < userCount; i++) {
            String phone = response.jsonPath().getString("[" + i + "].phone");
            Assert.assertNotNull(phone, "Phone number should not be null");
        }
    }

    // Test 14: Validate that address is non-empty
    @Test
    public void validateNonEmptyAddress() {
        Response response = UsersPage.getUsers();
        int userCount = response.jsonPath().getList("$").size();
        for (int i = 0; i < userCount; i++) {
            String address = response.jsonPath().getString("[" + i + "].address");
            Assert.assertFalse(address.isEmpty(), "Address should not be empty");
        }
    }
}
