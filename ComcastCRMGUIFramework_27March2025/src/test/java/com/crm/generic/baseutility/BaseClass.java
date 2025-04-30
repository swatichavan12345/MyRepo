package com.crm.generic.baseutility;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

public class BaseClass {
	
	@BeforeSuite
	public void configBforeSuite()
	{
		System.out.println("======Connect to Db, Report config======");
	}
	
	@BeforeClass
	public void configBforeClass()
	{
		System.out.println("===Launch the browser===");
	}
	
	@BeforeMethod
	public void configBforeMethod()
	{
		System.out.println("====Login======");
	}

	@AfterMethod
	public void configAfterMethod()
	{
		System.out.println("====Logout======");
	}
	
	@AfterClass
	public void configAfterClass()
	{
		System.out.println("===Close the browser=====");
	}
	
	@AfterSuite
	public void configAfterSuite()
	{
		System.out.println("======close DB, Report Backup=======");
	}
	
}
