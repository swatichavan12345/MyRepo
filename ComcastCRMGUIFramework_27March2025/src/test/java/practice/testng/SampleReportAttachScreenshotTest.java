package practice.testng;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class SampleReportAttachScreenshotTest{
	
	ExtentReports report;
	@BeforeSuite
	public void configBS() {
		// spark report config
		ExtentSparkReporter spark = new ExtentSparkReporter("./AdvanceReport2/ScreenshotReport.html");
		spark.config().setDocumentTitle("CRM Test Suite Results");
		spark.config().setReportName("CRm Report");
		spark.config().setTheme(Theme.DARK);
		// add Env Information & create test
		report.attachReporter(spark);
		report.setSystemInfo("OS", "windows-10");
		report.setSystemInfo("BROWSER", "CHROME-100");
	}
	
	@AfterSuite
	public void configAS()
	{
		report.flush();
	}
	
	@Test
	public void createAContactTest() {
		WebDriver driver=new ChromeDriver();
		driver.get("localhost:8888/");
		
		TakesScreenshot edriver=(TakesScreenshot) driver;
		String filepath=edriver.getScreenshotAs(OutputType.BASE64);
		
		ExtentTest test = report.createTest("createAContactTest");

		test.log(Status.INFO, "login to App");
		test.log(Status.INFO, "navigate to contact page");
		test.log(Status.INFO, "create contact");
		if ("HDFC".equals("HDFC123")) {
			test.log(Status.PASS, "Contact is created");
		} else {
			test.addScreenCaptureFromBase64String(filepath, "ErrorFile");
		}
		driver.close();
	}
	
	
	
}