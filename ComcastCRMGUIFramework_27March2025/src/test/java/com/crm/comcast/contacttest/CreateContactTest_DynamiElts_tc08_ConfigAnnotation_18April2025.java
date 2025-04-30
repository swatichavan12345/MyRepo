
	
	package com.crm.comcast.contacttest;

	import java.io.IOException;
import java.time.Duration;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.json.JsonException;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.comcast.crm.basetest.BaseClass1;
import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.UtilityClassObject;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;
import com.comcast.crm.listenerutility.ListenerImpleClass;
import com.comcast.crm.objectrepositoryutility2ndApril2025.CreatingNewOrganizationPage;
import com.comcast.crm.objectrepositoryutility2ndApril2025.HomePage;
import com.comcast.crm.objectrepositoryutility2ndApril2025.LoginPage;
import com.comcast.crm.objectrepositoryutility2ndApril2025.OrganizationInformationPage;
import com.comcast.crm.objectrepositoryutility2ndApril2025.OrganizationsPage;
import com.comcast.crm.objectrepositoryutility5thApril2025.ContactsPage;
import com.comcast.crm.objectrepositoryutility5thApril2025.CreatingNewContactPage;


	public class CreateContactTest_DynamiElts_tc08_ConfigAnnotation_18April2025 extends BaseClass1{

		@Test(groups= {"smokeTest","regressionTest"})
		public void createOrgTest() throws EncryptedDocumentException, IOException, InterruptedException {
			//ListenerImpleClass.test.log(Status.INFO, "Read data from excel");
			UtilityClassObject.getTest().log(Status.INFO, "read data from excel");
			// Read TestScript data from Excel file
			String lastName = elib.getdataFromExcel("contacts", 4, 2) + jlib.getRandomNumber();
			
			//Step2: Navigate to Contacts module
			//ListenerImpleClass.test.log(Status.INFO, "Navigate to Contacts module");
			UtilityClassObject.getTest().log(Status.INFO, "Navigate to Contacts module");
			WebDriver driver = null;
			//WebDriver driver;
			HomePage hp=new HomePage(driver);
			hp.getContactLink().click();//to perform single action use getters here. to perform multiple actions create a method(business method).
			
			//Step3: Click on "Create Contact" button
			//ListenerImpleClass.test.log(Status.INFO, "Navigate to Create Contact button");
			UtilityClassObject.getTest().log(Status.INFO, "Navigate to Create Contact button");
			ContactsPage cp=new ContactsPage(driver);
			cp.getCreateNewContactBtn().click();
			
			//Step4: Enter all the details and create new contact
			//ListenerImpleClass.test.log(Status.INFO, "create new contact");
			UtilityClassObject.getTest().log(Status.INFO, "create new contact");
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
			
		}
		
		@Test(groups= "regressionTest")
		public void createOrgTest2() throws EncryptedDocumentException, IOException {
			// Read TestScript data from Excel file
			String orgName = elib.getdataFromExcel("org2", 4, 2) + jlib.getRandomNumber();

			// Step2: Navigate to Organization module
			HomePage hp = new HomePage(driver);
			hp.getOrgLink().click();// to perform single action use getters here. to perform multiple actions create
									// a method(business method).

			// Step3: Click on "Create Organization" button
			OrganizationsPage cnp = new OrganizationsPage(driver);
			cnp.getCreateNewOrgBtn().click();
			;

			// Step4: Enter all the details and create new organization
			CreatingNewOrganizationPage cnop = new CreatingNewOrganizationPage(driver);
			cnop.createOrg(orgName);

			// Verify Header msg Expected Result
			OrganizationInformationPage oip = new OrganizationInformationPage(driver);
			String actOrgName = oip.getHeaderMSG().getText();
			if (actOrgName.contains(orgName)) {
				System.out.println(orgName + " name is verified == PASS");
			} else {
				System.out.println(orgName + " name is not verified == FAIL");
			}

		}
		
	}

