package api.tests;

import api.pages.ContinentsPage;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ContinentsTests {

    // Test 1: Validate response for all continents
    @Test
    public void validateContinentsResponse() {
        Response response = ContinentsPage.getContinents();
        Assert.assertTrue(response.jsonPath().getList("$").size() > 0, "Continents list is empty or malformed");
    }

    // Test 2: Validate HTTP status code for the continents API
    @Test
    public void validateContinentsStatusCode() {
        Response response = ContinentsPage.getContinents();
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 200, "Expected status code 200 but got " + statusCode);
    }

    // Test 3: Validate that the response headers contain the expected content type
    @Test
    public void validateContinentsResponseHeaders() {
        Response response = ContinentsPage.getContinents();
        String contentType = response.getHeader("Content-Type");
        Assert.assertTrue(contentType.contains("application/json"), "Expected content type to be application/json");
    }

    // Test 4: Validate continent details for each continent
    @Test
    public void validateResponseStructure() {
        Response response = ContinentsPage.getContinents();
        Assert.assertTrue(response.jsonPath().getList("$").size() > 0, "Response should contain continents");
        for (int i = 0; i < response.jsonPath().getList("$").size(); i++) {
            Assert.assertNotNull(response.jsonPath().getString("[" + i + "].code"), "Code should not be null");
            Assert.assertNotNull(response.jsonPath().getString("[" + i + "].name"), "Name should not be null");
            Assert.assertNotNull(response.jsonPath().getInt("[" + i + "].areaSqKm"), "Area should not be null");
            Assert.assertNotNull(response.jsonPath().getInt("[" + i + "].population"), "Population should not be null");
            Assert.assertNotNull(response.jsonPath().getList("[" + i + "].lines"), "Lines should not be null");
            Assert.assertNotNull(response.jsonPath().getInt("[" + i + "].countries"), "Countries count should not be null");
            Assert.assertNotNull(response.jsonPath().getList("[" + i + "].oceans"), "Oceans should not be null");
            Assert.assertNotNull(response.jsonPath().getList("[" + i + "].developedCountries"), "Developed countries should not be null");
        }
    }

    // Test 5: Validate the number of continents returned
    @Test
    public void validateNumberOfContinents() {
        Response response = ContinentsPage.getContinents();
        int numberOfContinents = response.jsonPath().getList("$").size();
        Assert.assertTrue(numberOfContinents > 0, "Expected at least one continent, but got " + numberOfContinents);
    }

    // Test 6: Validate unique continent codes
    @Test
    public void validateUniqueContinentCodes() {
        Response response = ContinentsPage.getContinents();
        List<String> codes = response.jsonPath().getList("code");
        Set<String> uniqueCodes = new HashSet<>(codes);
        Assert.assertEquals(uniqueCodes.size(), codes.size(), "Continent codes should be unique");
    }

    // Test 7: Validate that populations are non-negative
    @Test
    public void validatePopulation() {
        Response response = ContinentsPage.getContinents();
        for (int i = 0; i < response.jsonPath().getList("$").size(); i++) {
            int population = response.jsonPath().getInt("[" + i + "].population");
            Assert.assertTrue(population >= 0, "Population for continent " + response.jsonPath().getString("[" + i + "].name") + " should not be negative");
        }
    }
}