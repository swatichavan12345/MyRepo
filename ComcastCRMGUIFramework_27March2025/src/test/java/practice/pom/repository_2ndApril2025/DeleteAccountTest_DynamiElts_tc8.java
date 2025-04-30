
	
	package practice.pom.repository_2ndApril2025;

	import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.json.JsonException;

import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;
import com.comcast.crm.objectrepositoryutility2ndApril2025.CreatingNewOrganizationPage;
import com.comcast.crm.objectrepositoryutility2ndApril2025.HomePage;
import com.comcast.crm.objectrepositoryutility2ndApril2025.LoginPage;
import com.comcast.crm.objectrepositoryutility2ndApril2025.OrganizationInformationPage;
import com.comcast.crm.objectrepositoryutility2ndApril2025.OrganizationsPage;
import com.comcast.crm.objectrepositoryutility5thApril2025.ContactsPage;
import com.comcast.crm.objectrepositoryutility5thApril2025.CreatingNewContactPage;

	public class DeleteAccountTest_DynamiElts_tc8 {

		public static void main(String[] args) throws IOException, InterruptedException, NoSuchElementException, JsonException, InvocationTargetException{
			//I will use properties file, Excel: 2 sheets: org2,contacts

			/* Create Object */
			FileUtility flib=new FileUtility();
			ExcelUtility elib=new ExcelUtility();
			JavaUtility jlib=new JavaUtility();
			WebDriverUtility wlib=new WebDriverUtility();
			
			String BROWSER=flib.getDtaFromPropertiesFile("browser");
			String URL=flib.getDtaFromPropertiesFile("url");
			String USERNAME=flib.getDtaFromPropertiesFile("username");
			String PASSWORD=flib.getDtaFromPropertiesFile("password");
			

		//Read TestScript data from Excel file
		String lastName=elib.getdataFromExcel("contacts",4,2)+ jlib.getRandomNumber();
			
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
			
			LoginPage lp=new LoginPage(driver);//(driverObject,POMclassname)
			
			lp.loginToApp(URL, "admin", "admin");
			
			//Step2: Navigate to Contacts module
			HomePage hp=new HomePage(driver);
			hp.getContactLink().click();//to perform single action use getters here. to perform multiple actions create a method(business method).
			
			//Step3: Click on "Create Contact" button
			ContactsPage cp=new ContactsPage(driver);
			cp.getCreateNewContactBtn().click();
			
			//Step4: Enter all the details and create new contact
			CreatingNewContactPage cncp=new CreatingNewContactPage(driver);
			cncp.createNewContact(lastName);
			
			//Verify Header msg Expected Result
			
			String actLastName=driver.findElement(By.id("dtlview_Last Name")).getText();
			if(actLastName.contains(lastName))
			{
				System.out.println(lastName+" name is verified == PASS");
			}else{
				System.out.println(lastName+" name is not verified == FAIL");
			}
			
			// go back to contacts page
			hp.getContactLink().click();
			
			//search for contact
			cp.getSearchEdt().sendKeys(lastName);
			wlib.select(cp.getSearchDD(), "Last Name");
			cp.getSearchBtn().click();
			
			//In dynamic webTable select & delete org
			driver.findElement(By.xpath("//a[text()='"+lastName+"']/../../td[10]/a[text()='del']")).click();
			//by using dr.FE we can write the xpath of dynamic elts. Not possible with @FindBy
			
			// accepting javascript alert
	        Alert alert = driver.switchTo().alert();
	        System.out.println(alert.getText());
	        alert.accept();
	        
	        System.out.println("javascript Simple Alert popup get handled by clicking OK btn.");
	        Thread.sleep(400);
	        
			//Step5: perform Logout Action
			hp.logout();
			driver.quit();
			
		}

	}

