package com.comcast.crm.objectrepositoryutility5thApril2025;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactsInformationPage {
	

	WebDriver driver;
	public ContactsInformationPage(WebDriver driver) 
	{
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(xpath="//span[@class='dvHeaderText']")
	private WebElement headerMSG;
	public WebDriver getDriver() {
		return driver;
	}

	public WebElement getHeaderMSG() {
		return headerMSG;
	}
}
