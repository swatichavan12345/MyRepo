package com.comcast.crm.objectrepositoryutility5thApril2025;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreatingNewContactPage {
	

	WebDriver driver;
	public CreatingNewContactPage(WebDriver driver) 
	{
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}

	@FindBy(name="firstname")
	private WebElement firstNameEdt;
	
	@FindBy(name="lastname")
	private WebElement lastNameEdt;
	
	@FindBy(id="contact_no")
	private WebElement contactNOAutoGen;
	
	@FindBy(id="mobile")
	private WebElement mobileNoEdt;
	
	
	public WebElement getmobile() {
		return mobileNoEdt;
	}

	public WebDriver getDriver() {
		return driver;
	}

	public WebElement getFirstNameEdt() {
		return firstNameEdt;
	}

	public WebElement getLastNameEdt() {
		return lastNameEdt;
	}


	@FindBy(xpath="//input[@title='Save [Alt+S]']")
	private WebElement saveBtn;

	public WebElement getSaveBtn() {
		return saveBtn;
	}
	public void createNewContact(String lastName)
	{
		lastNameEdt.sendKeys(lastName);
		saveBtn.click();
	}
	
	public void createNewContact(String lastName, String contactno)
	{
		lastNameEdt.sendKeys(lastName);
		mobileNoEdt.sendKeys(contactno);
		saveBtn.click();
	}

	
}
