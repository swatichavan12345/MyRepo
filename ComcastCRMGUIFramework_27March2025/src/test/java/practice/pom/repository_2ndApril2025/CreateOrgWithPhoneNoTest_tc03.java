package practice.pom.repository_2ndApril2025;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;

public class CreateOrgWithPhoneNoTest_tc03 {

	public static void main(String[] args) throws Throwable, NullPointerException {
		//I will use properties file, Excel: 2 sheets: org2,contacts
		/* Create Object */
		FileUtility flib=new FileUtility();
		ExcelUtility elib=new ExcelUtility();
		JavaUtility jlib=new JavaUtility();
		
		//Read common data from properties file
		//FileInputStream fis=new FileInputStream("C:\\Users\\swati\\OneDrive\\Desktop\\TekPyramid\\Assigt2_vtiger.properties"); //absolute path.
		FileInputStream fis=new FileInputStream("./configAppData/Assigt2_vtiger.properties"); //use relative path.
		Properties pobj=new Properties();
		pobj.load(fis);
		
		String BROWSER=flib.getDtaFromPropertiesFile("browser");
		String URL=flib.getDtaFromPropertiesFile("url");
		String USERNAME=flib.getDtaFromPropertiesFile("username");
		String PASSWORD=flib.getDtaFromPropertiesFile("password");
		System.out.println("Reads COMMON-data from FileUtility properties file");
		
		//Read common data from properties file

	//Read TestScript data from Excel file
	String orgName=elib.getdataFromExcel("org2",1,2)+ jlib.getRandomNumber();
	String phoneNo=elib.getdataFromExcel("org2",7,3);//static data, so don't use random no here.
		
	
	WebDriver driver=null;
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
	//Step1: Login to App
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	driver.get(URL);
	
	driver.findElement(By.name("user_name")).sendKeys(USERNAME);
	driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
	driver.findElement(By.id("submitButton")).click();
	
	//Step2: Navigate to Organization module
	driver.findElement(By.linkText("Organizations")).click();
	
	//Step3: Click on "Create Organization" button
	driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
	
	//Step4: Enter all the details and create new organization
	driver.findElement(By.name("accountname")).sendKeys(orgName); //--> takes TS data from excel 
	driver.findElement(By.id("phone")).sendKeys(phoneNo);
	driver.findElement(By.name("button")).click();
	System.out.println("User get created organization");
	Thread.sleep(4000);
	
	//Verify Header phone number msg Expected Result
			String actPhoneNo=driver.findElement(By.id("dtlview_Phone")).getText();
			if(actPhoneNo.contains(phoneNo))
			{
				System.out.println(phoneNo +" info is created===PASS");
			}
			else
			{
				System.out.println(phoneNo +"  info is not created===FAIL");
			}
			Thread.sleep(4000); 
			//Verify Header OrgName info Expected Result
		
	//Step5: perform Logout Action
	Actions act=new Actions(driver);
	act.moveToElement(driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"))).perform();
	Thread.sleep(4000);
	
	driver.findElement(By.linkText("Sign Out")).click();
	System.out.println("User get Loged Out Successfully !");
	Thread.sleep(4000);
	driver.quit();
	
}

}
