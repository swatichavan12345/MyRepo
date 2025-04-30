package com.comcast.crm.objectrepositoryutility2ndApril2025;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrganizationsPage {
	

	WebDriver driver;
	public OrganizationsPage(WebDriver driver) 
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
	
	public WebElement getSearchBtn() {
		return searchBtn;
	}

	@FindBy(xpath="//img[@alt='Create Organization...']")
	private WebElement createNewOrgBtn;


	public WebElement getSearchEdt() {
		return searchEdt;
	}

	public WebElement getSearchDD() {
		return searchDD;
	}


	public WebElement getCreateNewOrgBtn() {
		return createNewOrgBtn;
	}
	
	

}
