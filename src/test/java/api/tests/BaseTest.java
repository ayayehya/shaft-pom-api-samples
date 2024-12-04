package api.tests;

import api.reports.ExtentManager;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class BaseTest {
    protected static ExtentReports extent;
    protected static ExtentTest test;

    @BeforeSuite
    public void setupReport() {
        extent = ExtentManager.getExtentReports();
    }

    @AfterSuite
    public void tearDownReport() {
        extent.flush();
    }
}
