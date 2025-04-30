package com.comcast.crm.objectrepositoryutility5thApril2025;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactsPage {
	

	WebDriver driver;
	public ContactsPage(WebDriver driver) 
	{
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	
	// these all are static data,& we add getters for that.
	@FindBy(name="search_text")
	private WebElement searchEdt;
	
	
	@FindBy(name="search_field")
	private WebElement searchDD;
	
	@FindBy(name="submit")
	private WebElement searchBtn;
	
	public WebDriver getDriver() {
		return driver;
	}

	public WebElement getHeaderMSG() {
		return headerMSG;
	}

	public WebElement getSearchBtn() {
		return searchBtn;
	}

	@FindBy(className = "dvHeaderText")
	private WebElement headerMSG;
	
	@FindBy(xpath="//img[@alt='Create Contact...']")
	private WebElement createNewContactBtn;


	public WebElement getSearchEdt() {
		return searchEdt;
	}

	public WebElement getSearchDD() {
		return searchDD;
	}


	public WebElement getCreateNewContactBtn() {
		return createNewContactBtn;
	}
	
	

}
