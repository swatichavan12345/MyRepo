 package com.comcast.crm.basetest;

import java.io.IOException;
import java.sql.SQLException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;
import com.comcast.crm.generic.databaseutility.DataBaseUtility;

import com.comcast.crm.objectrepositoryutility2ndApril2025.HomePage;
import com.comcast.crm.objectrepositoryutility2ndApril2025.LoginPage;

public class BaseClass1 {
	/// Create Object 
	public FileUtility flib=new FileUtility();
	public ExcelUtility elib=new ExcelUtility();
	public JavaUtility jlib=new JavaUtility();
	public WebDriverUtility wlib=new WebDriverUtility();//--> need to implement for logout.
	public DataBaseUtility dblib=new DataBaseUtility();
	public static WebDriver driver=null; //declare gobally.
	
	
	@BeforeSuite(groups= {"smokeTest", "regressionTest"})
	public void configBforeSuite() throws SQLException
	{
		System.out.println("======Connect to Db, Report config======");
		dblib.getDBConnection();
	}
	
	//@Parameters("BROWSER")
	@BeforeClass(groups= {"smokeTest", "regressionTest"})
	public void configBforeClass(String browser) throws IOException
	{
		System.out.println("===Launch the browser===");
		String BROWSER=//browser;
				flib.getDtaFromPropertiesFile("browser");
		
		if(BROWSER.equals("chrome")) {
			driver=new ChromeDriver();
		}else if(BROWSER.equals("safari")) {
			driver=new FirefoxDriver();
		}else if(BROWSER.equals("edge")) {
			driver=new EdgeDriver();
		}else {
			driver=new ChromeDriver();
		}
		driver.manage().window().maximize();
	}
	
	@BeforeMethod(groups= {"smokeTest", "regressionTest"})
	public void configBforeMethod() throws IOException
	{
		System.out.println("====Login======");
		String URL=flib.getDtaFromPropertiesFile("url");
		String USERNAME=flib.getDtaFromPropertiesFile("username");
		String PASSWORD=flib.getDtaFromPropertiesFile("password");
		LoginPage lp=new LoginPage(driver);
		lp.loginToApp(URL, USERNAME, PASSWORD);
	}

	@AfterMethod(groups= {"smokeTest", "regressionTest"})
	public void configAfterMethod()
	{
		System.out.println("====Logout======");
		HomePage hp=new HomePage(driver);
		hp.logout();
	}
	
	@AfterClass(groups= {"smokeTest", "regressionTest"})
	public void configAfterClass()
	{
		System.out.println("===Close the browser=====");
		driver.quit();
	}
	
	@AfterSuite(groups= {"smokeTest", "regressionTest"})
	public void configAfterSuite() throws SQLException
	{
		System.out.println("======close DB, Report Backup=======");
		dblib.closeDBConnection();
	}
	
}



