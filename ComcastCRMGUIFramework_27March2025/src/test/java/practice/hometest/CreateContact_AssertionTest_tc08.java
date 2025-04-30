package practice.hometest;

import java.io.IOException;
import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.comcast.crm.basetest.BaseClass1;
import com.comcast.crm.objectrepositoryutility5thApril2025.ContactsPage;
import com.comcast.crm.objectrepositoryutility5thApril2025.CreatingNewContactPage;
import com.comcast.crm.objectrepositoryutility5thApril2025.HomePage;

public class CreateContact_AssertionTest_tc08 extends BaseClass1{
	@Test(groups= {"SmokeTest", "regressionTest"})
	public void createOrgTest() throws EncryptedDocumentException, IOException, InterruptedException {
		// Read TestScript data from Excel file
		String lastName = elib.getdataFromExcel("contacts", 4, 2) + jlib.getRandomNumber();
		
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
		
		String actHeader=cp.getHeaderMSG().getText();
		System.out.println("actHeader: "+actHeader);
		boolean status=actHeader.contains(lastName);
		Assert.assertEquals(status, true);
		
		
		String actLastName=driver.findElement(By.id("dtlview_Last Name")).getText();
		SoftAssert assObj=new SoftAssert();
		assObj.assertEquals(actLastName, lastName);
		System.out.println(actLastName +" = actLastName, " +lastName+"\n = lastname");
		assObj.assertAll();
		
	}
}
