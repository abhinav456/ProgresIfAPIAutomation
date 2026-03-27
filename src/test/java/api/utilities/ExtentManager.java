package api.utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {

    public static ExtentReports extent;

    public static ExtentReports getInstance() {

        if (extent == null) {

            ExtentSparkReporter spark =
                    new ExtentSparkReporter("Reports/ProgresIfAPIReport.html");

            extent = new ExtentReports();
            extent.attachReporter(spark);
        }

        return extent;
    }
}