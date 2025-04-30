package practice.hometest;

import java.lang.reflect.Method;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.mysql.jdbc.Driver;

public class HomePageSample3_IntegrationTest {
	
	@Test
	public void homePageTest(Method mtd)
	//Integration test case- use of HardAssert and SoftAssert in same testScript.
	{
		System.out.println(mtd.getName() +" Test Start ");
		SoftAssert assObject=new SoftAssert();
		System.out.println("Step-1");
		System.out.println("Step-2");
		Assert.assertEquals("HomePage", "Home");
		System.out.println("Step-3");
		assObject.assertEquals("Title", "Title - 1");
		System.out.println("Step-4");
		assObject.assertAll();
		System.out.println(mtd.getName() + " Test End");
	}
	
	@Test
	public void verifyLogoHomePageTest(Method mtd)
	{
		System.out.println(mtd.getName() +" Test Start ");
		SoftAssert assObject=new SoftAssert();
		System.out.println("Step-1");
		System.out.println("Step-2");
		assObject.assertTrue(true);
		System.out.println("Step-3");
		System.out.println("Step-4");
		assObject.assertAll();
		System.out.println(mtd.getName() +" Test End ");
	}
}
