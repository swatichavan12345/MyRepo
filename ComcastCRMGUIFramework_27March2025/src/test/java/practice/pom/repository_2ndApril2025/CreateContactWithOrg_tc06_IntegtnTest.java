package practice.pom.repository_2ndApril2025;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.Iterator;
import java.util.Properties;
import java.util.Random;
import java.util.Set;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;

public class CreateContactWithOrg_tc06_IntegtnTest {

	public static void main(String[] args) throws Throwable {
		//precondition: atleast 1 orn should be cteated.(orname should not duplicated ; use random class.
		//impo: need to automat pre-condition also--> otherwise Tc get fail at any Envt.)
		//use JDBC OR do from selenium code.
		
		/* Create Object */
		FileUtility flib=new FileUtility();
		ExcelUtility elib=new ExcelUtility();
		JavaUtility jlib=new JavaUtility();
		WebDriverUtility wlib=new WebDriverUtility();
		
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

	//Read TestScript data from Excel file
	String ContactLastname=elib.getdataFromExcel("contacts",1,2)+jlib.getRandomNumber();
	String orgName=elib.getdataFromExcel("contacts",1,2)+ jlib.getRandomNumber();
	
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
	wlib.waitForPageToLoad(driver);
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
	driver.findElement(By.name("button")).click();
	System.out.println("User get created organization and confirmed the Organization Name into header MSG.");
	Thread.sleep(4000);
	
	//Verify Header msg orgName Expected Result
	String headerInfo=driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
	if(headerInfo.contains(orgName))
	{
		System.out.println(orgName +" header verified ===PASS");
	}
	else
	{
		System.out.println(orgName +" header not verified ===FAIL");
	}
	Thread.sleep(4000); 
	//step5: navigate to contact module
	
	//Step6: Navigate to Organization module
			driver.findElement(By.linkText("Contacts")).click();
			
			//Step7: Click on "Create Organization" button
			driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();
			
			//Step8: Enter all the details and create new organization
			driver.findElement(By.name("lastname")).sendKeys(ContactLastname); //--> takes TS data from excel 
				//integration code: click lookup window
			driver.findElement(By.xpath("//input[@name='account_name']/following-sibling::img")).click();
			
			//switch to child window
			wlib.switchToTabOnURL(driver, "module=Accounts");
	
			driver.findElement(By.id("search_txt")).sendKeys(orgName);
			driver.findElement(By.name("search")).click();
			driver.findElement(By.xpath("//a[text()='"+orgName+"']")).click();//make  dynamic-xpath with this syntax
			
			//switch to parent window
			wlib.switchToTabOnURL(driver, "Contacts&action");
			
			driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
			Thread.sleep(4000);
			//Verify Header msg orgName Expected Result
			String headerInfo1=driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
			//String headerInfo1=driver.findElement(By.xpath("//span[contains(text(),'Contact Information']")).getText();
			if(headerInfo1.contains(ContactLastname))
			{
				System.out.println(ContactLastname +" contactLastName header verified ===PASS");
			}
			else
			{
				System.out.println(ContactLastname +" contactLastName header not verified ===FAIL");
			}
			Thread.sleep(4000); 
			//Verify Header msg orgName info Expected Result
			String actOrgName=driver.findElement(By.id("mouseArea_Organization Name")).getText();
			if(actOrgName.contains(orgName))
			{
				System.out.println(orgName +" orgName info created ===PASS");
			}
			else
			{
				System.out.println(orgName +"  orgName info not created  ===FAIL");
			}
			Thread.sleep(4000); 
	driver.quit();
	
}

}
