package practice.hometest;

import java.lang.reflect.Method;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.mysql.jdbc.Driver;

public class HomePageVerification_Assertion_Hard_Test {
	
	@Test
	public void homePageTest(Method mtd)
	//to call method dynamically-> import java.lang.reflect.Method;
	{
		System.out.println(mtd.getName() +" Test Start ");
		//String expectPage="Home";
		String expectPage="Home page";
		WebDriver driver=new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get("http://localhost:8888/");
		
		driver.findElement(By.name("user_name")).sendKeys("admin");
		driver.findElement(By.name("user_password")).sendKeys("admin");
		driver.findElement(By.id("submitButton")).click();
		
		String actTitle=driver.findElement(By.xpath("//a[contains(text(),'Home')]")).getText();
		//use of assertion: Hard assert
		Assert.assertEquals(actTitle, expectPage);
		
		driver.close();
		System.out.println(mtd.getName() + " Test End");
	}
	
	@Test
	public void verifyLogoHomePageTest(Method mtd)
	{
		System.out.println(mtd.getName() +" Test Start ");
		WebDriver driver=new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get("http://localhost:8888/");
		
		driver.findElement(By.name("user_name")).sendKeys("admin");
		driver.findElement(By.name("user_password")).sendKeys("admin");
		driver.findElement(By.id("submitButton")).click();
		
		boolean status=driver.findElement(By.xpath("//img[@title='vtiger-crm-logo.gif']")).isEnabled();
		//use of assertion: Hard assert
		Assert.assertTrue(status);
		driver.close();
		System.out.println(mtd.getName() +" Test End ");
	}
}
