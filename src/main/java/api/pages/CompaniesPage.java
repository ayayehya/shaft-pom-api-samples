package api.pages;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class CompaniesPage {
    private static final String BASE_URL = "https://fake-json-api.mock.beeceptor.com/companies";

    // Get all companies
    public static Response getCompanies() {
        return RestAssured.get(BASE_URL);
    }
}