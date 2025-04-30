package com.comcast.crm.listenerutility;

import java.io.File;
import java.io.IOException;
import java.sql.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.comcast.crm.basetest.BaseClass;
import com.comcast.crm.generic.webdriverutility.UtilityClassObject;

public class ListenerImpleClass implements ITestListener, ISuiteListener {
	public ExtentSparkReporter spark;
	public static ExtentReports report;
	public static ExtentTest test;

	@Override
	public void onTestStart(ITestResult result) {
		System.out.println("===== ====>" + result.getMethod().getMethodName() + ">========START==========");
		test=report.createTest(result.getMethod().getMethodName());
		UtilityClassObject.setTest(test);
		test.log(Status.INFO, result.getMethod().getMethodName()+"=========Started===============");
		
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		System.out.println("===== ====>" + result.getMethod().getMethodName() + ">========END==========");
		test.log(Status.PASS, result.getMethod().getMethodName()+"=========Completed===============");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		String testName = result.getMethod().getMethodName();
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.amazon.in/");

		WebElement ele = driver.findElement(By.linkText("Mobiles"));

		File src = ele.getScreenshotAs(OutputType.FILE);
		
		String time = new Date(0).toString().replace(" ", "_").replace(":", "_");
		
		TakesScreenshot edriver=(TakesScreenshot) BaseClass.sdriver;
		String filepath=edriver.getScreenshotAs(OutputType.BASE64);
		test.addScreenCaptureFromBase64String(filepath, testName+"_"+time);
		test.log(Status.FAIL, result.getMethod().getMethodName()+"=========Failed===============");
		
		
		
/*
//		File src=sc.getScreenshotAs(OutputType.FILE);

		// File dest=new
		// File("../ComcastCRMGUIFramework_27March2025/Screenshot_Amazon_10thApril2025/amazon.png");
		// //hardcoded scrnnm
		File dest = new File(
				"../ComcastCRMGUIFramework_27March2025/Screenshot_Amazon_10thApril2025/" + testName + ".png"); // testcsenm=scrnnm.
		try {
			// FileUtils.copyFile(src, dest);
			// To tak etime base screenshots: timestamp to get the screenshots on different
			// timing.
			FileUtils.copyFile(src,
					new File("../ComcastCRMGUIFramework_27March2025/Screenshot_Amazon_10thApril2025/" + time + ".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		*/
	}

	@Override
	public void onStart(ISuite suite) {
		System.out.println("Report Configuration");
		String time = new Date(0).toString().replace(" ", "_").replace(":", "_");
		// spark report config
		ExtentSparkReporter spark = new ExtentSparkReporter("./AdvanceReport/report_"+time+".html");
		spark.config().setDocumentTitle("CRM Test Suite Results");
		spark.config().setReportName("CRm Report");
		spark.config().setTheme(Theme.DARK);
		// add Env Information & create test
		report.attachReporter(spark);
		report.setSystemInfo("OS", "windows-11");
		report.setSystemInfo("BROWSER", "CHROME-100");
		
		

	}

	@Override
	public void onFinish(ISuite suite) {
		System.out.println("Report Backup");
		report.flush();
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		//test.log(Status.SKIP, result.getMethod().getMethodName()+"=========Skipped===============");
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub

	}

}
