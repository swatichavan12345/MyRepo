package practice.hometest;

import java.lang.reflect.Method;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.mysql.jdbc.Driver;

public class HomePageSampleTest {
	
	@Test
	public void homePageTest(Method mtd)
	//to call method dynamically-> import java.lang.reflect.Method;
	{
		System.out.println(mtd.getName() +" Test Start ");
		System.out.println("Step-1");
		System.out.println("Step-2");
		//Assert.assertEquals("Home", "Home"); 
		//stops exctn after step 2 . TC failed.
		Assert.assertEquals("HomePage", "Home");
		System.out.println("Step-3");
		Assert.assertEquals("Home-CRM", "Home-CRM");
		System.out.println("Step-4");
		System.out.println(mtd.getName() + " Test End");
	}
	
	@Test
	public void verifyLogoHomePageTest(Method mtd)
	{
		System.out.println(mtd.getName() +" Test Start ");
		System.out.println("Step-1");
		System.out.println("Step-2");
		Assert.assertTrue(true);
		System.out.println("Step-3");
		System.out.println("Step-4");
		System.out.println(mtd.getName() +" Test End ");
	}
}
