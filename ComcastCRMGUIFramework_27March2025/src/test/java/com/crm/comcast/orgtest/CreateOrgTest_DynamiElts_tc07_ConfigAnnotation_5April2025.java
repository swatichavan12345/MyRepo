
package com.crm.comcast.orgtest;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.json.JsonException;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import com.comcast.crm.basetest.BaseClass1;
import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;
import com.comcast.crm.objectrepositoryutility2ndApril2025.CreatingNewOrganizationPage;
import com.comcast.crm.objectrepositoryutility2ndApril2025.HomePage;
import com.comcast.crm.objectrepositoryutility2ndApril2025.LoginPage;
import com.comcast.crm.objectrepositoryutility2ndApril2025.OrganizationInformationPage;
import com.comcast.crm.objectrepositoryutility2ndApril2025.OrganizationsPage;
//this prog takes config annotan from baseClass Test.
public class CreateOrgTest_DynamiElts_tc07_ConfigAnnotation_5April2025 extends BaseClass1 {

	@Test(groups= "smokeTest")
	public void createOrgTest() throws EncryptedDocumentException, IOException {
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
	@Test(groups= "regressionTest")
	public void createOrgTest3() throws EncryptedDocumentException, IOException {
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

