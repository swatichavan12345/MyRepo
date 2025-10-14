package users.api.BaseAPIClass;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import users.api.genericutility.DataBaseUtility;
import users.api.genericutility.FileUtility;
import users.api.genericutility.JavaUtility;

import static io.restassured.RestAssured.*;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class BaseAPIClass {

    public static ExtentReports extent;
    public static ExtentTest test;
    public static RequestSpecification specReqObj;
    public static ResponseSpecification specRespObj;

    public DataBaseUtility dbLib = new DataBaseUtility();
    public FileUtility fLib = new FileUtility();
    public JavaUtility jLib = new JavaUtility();

    @BeforeSuite
    public void setupSuite() throws IOException {
        // Initialize Extent Report
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String reportPath = System.getProperty("user.dir") + "/Reports/API_Report_" + timestamp + ".html";

        ExtentSparkReporter spark = new ExtentSparkReporter(reportPath);
        spark.config().setDocumentTitle("API Automation Report");
        spark.config().setReportName("User Management API Test Results");
        spark.config().setTheme(Theme.STANDARD);

        extent = new ExtentReports();
        extent.attachReporter(spark);
        extent.setSystemInfo("Tester", "Swati Chavan");
        extent.setSystemInfo("Environment", "QA");
        extent.setSystemInfo("Framework", "RestAssured + TestNG");

        // Request and Response specification setup
        specReqObj = new RequestSpecBuilder()
                .setBaseUri(fLib.getDtaFromPropertiesFile("BASEUri"))
                .setContentType(ContentType.JSON)
                .build();

        specRespObj = new ResponseSpecBuilder()
                .expectContentType(ContentType.JSON)
                .build();
    }

    @AfterSuite
    public void tearDownSuite() throws Exception {
        if (extent != null) {
            extent.flush();
        }
        dbLib.closeDBConnection();
    }

    // 🔹 Utility method to capture API log as "screenshot" equivalent
    public String captureApiResponse(String responseBody) {
        try {
            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String path = System.getProperty("user.dir") + "/Reports/Response_" + timestamp + ".txt";
            File file = new File(path);
            java.nio.file.Files.write(file.toPath(), responseBody.getBytes());
            return path;
        } catch (Exception e) {
            return "Failed to capture API response log";
        }
    }
}
