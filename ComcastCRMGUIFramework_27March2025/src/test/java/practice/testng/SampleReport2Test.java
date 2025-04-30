package practice.testng;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.comcast.crm.basetest.BaseClass1;

public class SampleReport2Test extends BaseClass1{
	
	ExtentReports report;
	@BeforeSuite
	public void configBS() {
		// spark report config
		ExtentSparkReporter spark = new ExtentSparkReporter("./AdvanceReport2/Report2.html");
		spark.config().setDocumentTitle("CRM Test Suite Results");
		spark.config().setReportName("CRm Report");
		spark.config().setTheme(Theme.DARK);
		// add Env Information & create test
		report.attachReporter(spark);
		report.setSystemInfo("OS", "windows-11");
		report.setSystemInfo("BROWSER", "CHROME-100");
	}
	
	@AfterSuite
	public void configAS()
	{
		report.flush();
	}
	
	@Test
	public void createContactTest2() {

		ExtentTest test = report.createTest("createContactTest2");

		test.log(Status.INFO, "login to App");
		test.log(Status.INFO, "navigate to contact page");
		test.log(Status.INFO, "create contact");
		if ("HDFC".equals("HDFC")) {
			test.log(Status.PASS, "Contact is created");
		} else {
			test.log(Status.FAIL, "Contact is not created");
		}
		report.flush();
	}
	@Test
	public void createContactWithPhoneNoTest() {

		ExtentTest test = report.createTest("createContactWithPhoneNoTest");

		test.log(Status.INFO, "login to App");
		test.log(Status.INFO, "navigate to contact page");
		test.log(Status.INFO, "create contact");
		if ("HDFC".equals("HDFC")) {
			test.log(Status.PASS, "Contact is created");
		} else {
			test.log(Status.FAIL, "Contact is not created");
		}
	}
	@Test
	public void createContactWithORGTest() {

		ExtentTest test = report.createTest("createContactWithORGTest");

		test.log(Status.INFO, "login to App");
		test.log(Status.INFO, "navigate to contact page");
		test.log(Status.INFO, "create contact");
		if ("HDFC".equals("HDFC")) {
			test.log(Status.PASS, "Contact is created");
		} else {
			test.log(Status.FAIL, "Contact is not created");
		}
	}

}
