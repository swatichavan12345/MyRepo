package practice.testng;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class CreateContactConfigAnnotationsTest {
	
	@BeforeSuite
	public void configBforeSuite()
	{
		System.out.println("execute BS");
	}
	
	@AfterSuite
	public void configAfterSuite()
	{
		System.out.println("execute AS");
	}
	
	@BeforeClass
	public void configBforeClass()
	{
		System.out.println("execute BC");
	}
	
	@BeforeMethod
	public void configBforeMethod()
	{
		System.out.println("execute BM");
	}
	
	@Test
	public void createContact()
	{
		System.out.println("execute createContact");
	}
	
	@Test
	public void createContactWithdate()
	{
		System.out.println("execute createContactWithdate");
	}
	
	@AfterMethod
	public void configAfterMethod()
	{
		System.out.println("execute AM");
	}
	@AfterClass
	public void configAfterClass()
	{
		System.out.println("execute AC");
	}
}
