package qtriptest;

import com.relevantcodes.extentreports.ExtentReports;
import java.io.File;

public class ReportSingleton {
    private static ExtentReports report;

    private ReportSingleton() {}

    public static ExtentReports getReport() {
        if (report == null) {
            report = new ExtentReports(System.getProperty("user.dir") + "/Report/ExtentReports.html", true);
            report.loadConfig(new File(System.getProperty("user.dir") + "/extent_customization_configs.xml"));
        }
        return report;
    }
}