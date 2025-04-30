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

public class HomePageSample2_SoftAssertTest {
	
	@Test
	public void homePageTest(Method mtd)
	//to call method dynamically-> import java.lang.reflect.Method;
	{
		System.out.println(mtd.getName() +" Test Start ");
		SoftAssert assObject=new SoftAssert();
		System.out.println("Step-1");
		System.out.println("Step-2");
		assObject.assertEquals("HomePage", "Home");
		System.out.println("Step-3");
		assObject.assertEquals("Home-CRM", "Home-CRM");
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
