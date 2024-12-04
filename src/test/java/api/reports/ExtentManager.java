package api.reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {
    private static ExtentReports extent;
    private static ExtentSparkReporter sparkReporter;

    public static ExtentReports getExtentReports() {
        if (extent == null) {
            // تحديد موقع التقرير
            sparkReporter = new ExtentSparkReporter("test-output/extent-report.html");
            extent = new ExtentReports();
            extent.attachReporter(sparkReporter);

            // تخصيص التقرير
            sparkReporter.config().setReportName("API Testing Report");
            sparkReporter.config().setDocumentTitle("Test Report");
        }
        return extent;
    }
}
