package practice.orgTest_GenericUtility;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
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

public class CreateOrganizationTest_tc01 {

	public static void main(String[] args) throws IOException, InterruptedException, NoSuchElementException {
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
	String orgName=elib.getdataFromExcel("org2",1,2)+ jlib.getRandomNumber();
		
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
		WebElement org=driver.findElement(By.name("accountname"));
		org.sendKeys(orgName); //--> takes TS data from excel 
		System.out.println("Reads TESTSCRIPT-data orgname from Excel file : " + orgName);
		driver.findElement(By.name("website")).sendKeys("https://www.ibm.com/in-en");
		driver.findElement(By.name("account_name")).sendKeys("IT");
		driver.findElement(By.id("employees")).sendKeys("600"); 
		
		Select ind=new Select(driver.findElement(By.name("industry")));
		ind.selectByVisibleText("Technology");
		Select type=new Select(driver.findElement(By.name("accounttype")));
		type.selectByVisibleText("Analyst");
		WebElement chkBox=driver.findElement(By.name("emailoptout"));
		chkBox.click();
		System.out.println("Checked the box of Email Opt Out");
		Thread.sleep(4000);
		WebElement radioBtn=driver.findElement(By.xpath("//input[@type='radio' and @value='T']"));
		Thread.sleep(4000);
		radioBtn.click();
		System.out.println("Assigned to : Group");
		Select markGrp= new Select(driver.findElement(By.name("assigned_group_id")));
		markGrp.selectByVisibleText("Team Selling");
		
		driver.findElement(By.name("button")).click();
		System.out.println("User get created organization and confirmed the Organization Name into header MSG.");
		/* handling Alert in Java Selenium: 1) Simple Alert: 
		The simple alert class in Selenium displays some information or warning on the screen.   
		Alert al= driver.switchTo().alert();
		String str=driver.switchTo().alert().getText();
		if (str.contains(org))   */
		Thread.sleep(4000);
		
		//Verify Header msg Expected Result
		
		String headerInfo=driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		//String headerInfo=driver.findElement(By.xpath("//span[contains(text(),'Organization Information']")).getText();
		if(headerInfo.contains(orgName))
		{
			System.out.println(orgName +" is created===PASS");
		}
		else
		{
			System.out.println(orgName +" is not created===FAIL");
		}
		Thread.sleep(4000); 
		//Verify Header OrgName info Expected Result
		
		String id=driver.findElement(By.id("dtlview_Organization Name")).getText();
		//String id=driver.findElement(By.xpath("//span[class='dtlview_Organization Name']")).getText();
		if(headerInfo.contains(id))
		{
			System.out.println(id +" is verified===PASS");
		}
		else
		{
			System.out.println(id +" is not verified===FAIL");
		} 
		Thread.sleep(4000);
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

