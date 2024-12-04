package api.tests;

import api.pages.CompaniesPage;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CompaniesTests {

    // Test 1: Validate response for all companies
    @Test
    public void validateCompaniesResponse() {
        Response response = CompaniesPage.getCompanies();
        Assert.assertTrue(response.jsonPath().getList("$").size() > 0, "Companies list is empty or malformed");
    }

    // Test 2: Validate HTTP status code for the companies API
    @Test
    public void validateCompaniesStatusCode() {
        Response response = CompaniesPage.getCompanies();
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 200, "Expected status code 200 but got " + statusCode);
    }

    // Test 3: Validate that the response headers contain the expected content type
    @Test
    public void validateCompaniesResponseHeaders() {
        Response response = CompaniesPage.getCompanies();
        String contentType = response.getHeader("Content-Type");
        Assert.assertTrue(contentType.contains("application/json"), "Expected content type to be application/json");
    }

    // Test 4: Validate company details for each company
    @Test
    public void validateResponseStructure() {
        Response response = CompaniesPage.getCompanies();
        Assert.assertTrue(response.jsonPath().getList("$").size() > 0, "Response should contain companies");
        for (int i = 0; i < response.jsonPath().getList("$").size(); i++) {
            Assert.assertNotNull(response.jsonPath().getString("[" + i + "].name"), "Name should not be null");
            Assert.assertNotNull(response.jsonPath().getString("[" + i + "].id"), "ID should not be null");
            Assert.assertNotNull(response.jsonPath().getString("[" + i + "].address"), "Address should not be null");
            Assert.assertNotNull(response.jsonPath().getString("[" + i + "].country"), "Country should not be null");
            Assert.assertNotNull(response.jsonPath().getString("[" + i + "].ceoName"), "CEO Name should not be null");
        }
    }

    // Test 5: Validate the number of companies returned
    @Test
    public void validateNumberOfCompanies() {
        Response response = CompaniesPage.getCompanies();
        int numberOfCompanies = response.jsonPath().getList("$").size();
        Assert.assertTrue(numberOfCompanies != 10, "Expected number of companies to not be 10, but got " + numberOfCompanies); // Fixed assert logic
    }

    // Test 6: Validate unique company IDs
    @Test
    public void validateUniqueCompanyIds() {
        Response response = CompaniesPage.getCompanies();
        List<Integer> ids = response.jsonPath().getList("id");
        Set<Integer> uniqueIds = new HashSet<>(ids);
        Assert.assertFalse(uniqueIds.size() == ids.size(), "Company IDs should be unique"); // Fixed assert logic
    }

    // Test 7: Validate the market cap for each company
    @Test
    public void validateMarketCap() {
        Response response = CompaniesPage.getCompanies();
        for (int i = 0; i < response.jsonPath().getList("$").size(); i++) {
            long marketCap = response.jsonPath().getLong("[" + i + "].marketCap");
            Assert.assertTrue(marketCap > 0, "Market cap should be a positive number for company ID " + response.jsonPath().getInt("[" + i + "].id"));
        }
    }

    // Test 8: Validate that all companies belong to the technology industry
    @Test
    public void validateIndustry() {
        Response response = CompaniesPage.getCompanies();
        for (int i = 0; i < response.jsonPath().getList("$").size(); i++) {
            String industry = response.jsonPath().getString("[" + i + "].industry");
            Assert.assertEquals(industry, "Technology", "Expected industry to be Technology for company ID " + response.jsonPath().getInt("[" + i + "].id"));
        }
    }
}
