package practice.hometest;

import java.lang.reflect.Method;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import com.mysql.jdbc.Driver;

public class HomePageVerificationTest {
	
	@Test
	public void homePageTest(Method mtd)
	//to call method dynamically-> import java.lang.reflect.Method;
	{
		System.out.println(mtd.getName() +" Test Start ");
		String expectPage="Home";
		WebDriver driver=new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get("http://localhost:8888/");
		
		driver.findElement(By.name("user_name")).sendKeys("admin");
		driver.findElement(By.name("user_password")).sendKeys("admin");
		driver.findElement(By.id("submitButton")).click();
		
		String actTitle=driver.findElement(By.xpath("//a[contains(text(),'Home')]")).getText();
		
		if(actTitle.trim().equals(expectPage))
		{
			System.out.println(expectPage+" page is verified==pass");
		}
		else
		{
			System.out.println(expectPage+" page is not verified==fail");
		}
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
		if(status)
		{
			System.out.println(" Logo is verified ");
		}
		else
		{
			System.out.println(" Logo is not verified ");	
		}
		driver.close();
		System.out.println(mtd.getName() +" Test End ");
	}
}
