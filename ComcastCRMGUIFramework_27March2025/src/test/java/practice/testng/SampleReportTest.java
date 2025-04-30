package practice.testng;

import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class SampleReportTest {
	@Test
	public void createContactTest()
	{
		//spark report config
		ExtentSparkReporter spark=new ExtentSparkReporter("./AdvanceReport/Report.html");
		spark.config().setDocumentTitle("CRM Test Suite Results");
		spark.config().setReportName("CRm Report");
		spark.config().setTheme(Theme.DARK);
		
		//add Env Information & create test
		ExtentReports report=new ExtentReports();
		report.attachReporter(spark);
		report.setSystemInfo("OS", "windows-11");
		report.setSystemInfo("BROWSER", "CHROME-100");
		ExtentTest test=report.createTest("createContactTest");
		
		test.log(Status.INFO, "login to App");
		test.log(Status.INFO, "navigate to contact page");
		test.log(Status.INFO, "create contact");
		if("HDFC".equals("HDFC"))
		{
			test.log(Status.PASS, "Contact is created");
		}
		else
		{
			test.log(Status.FAIL, "Contact is not created");
		}
		report.flush();
		System.out.println("login to App");
	}

}
