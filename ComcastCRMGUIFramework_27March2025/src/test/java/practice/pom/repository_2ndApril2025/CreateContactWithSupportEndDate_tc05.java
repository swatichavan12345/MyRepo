package practice.pom.repository_2ndApril2025;

import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;

public class CreateContactWithSupportEndDate_tc05 {
	
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
			
		//Read TestScript data from Excel file
		String ContactLastname=elib.getdataFromExcel("contacts",1,2)+jlib.getRandomNumber();
		
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
		driver.findElement(By.linkText("Contacts")).click();
		
		//Step3: Click on "Create Organization" button
		driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();
		
		//Step4: Enter all the details and create new organization
		String startDate=jlib.getSystemDateYYYYMMDD();
		String endDate=jlib.getRequiredDateYYYYMMDD(30);
		
		driver.findElement(By.name("lastname")).sendKeys(ContactLastname); //--> takes TS data from excel 
		driver.findElement(By.name("support_start_date")).clear();
		driver.findElement(By.name("support_start_date")).sendKeys(startDate);
		
		driver.findElement(By.name("support_end_date")).clear();
		driver.findElement(By.name("support_end_date")).sendKeys(endDate);
		//to capture system date-> go to java program-> new-> class->create class 'JavaBasics'-> 
		//type:     Date dateobject =new date();  from java.util sopln(dateobject);
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		System.out.println("User creates contact");
		Thread.sleep(4000);
		
		//Verify Header last name msg Expected Result
				String actStartDate=driver.findElement(By.id("dtlview_Support Start Date")).getText();
				if(actStartDate.contains(startDate))
				{
					System.out.println(startDate +" startDate info is verified===PASS");
				}
				else
				{
					System.out.println(startDate +" startDate info is not verified===FAIL");
				}
				Thread.sleep(4000); 
				String actEndDate=driver.findElement(By.id("dtlview_Support End Date")).getText();
				if(actEndDate.contains(endDate))
				{
					System.out.println(endDate +" endDate info is verified===PASS");
				}
				else
				{
					System.out.println(endDate +" endDate info is not verified===FAIL");
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
