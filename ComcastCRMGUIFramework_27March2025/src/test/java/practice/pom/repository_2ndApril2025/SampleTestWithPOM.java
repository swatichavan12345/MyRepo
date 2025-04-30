package practice.pom.repository_2ndApril2025;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

public class SampleTestWithPOM {
	
	@FindBy(name="user_name")
	WebElement ele1;
	
	@FindBy(name="user_password")
	WebElement ele2;
	
	//AutoHealing in POM class:=> If anyone conditn fails check with next one & pass the test case exctn.
	@FindAll({
		@FindBy(id="submitButton"),
		@FindBy(xpath="//input[@type='submit']"),
		@FindBy(xpath="//input[@value='Login']")
	})
	WebElement ele3;
	
	
	@Test
	public void sampleTest()
	{
		WebDriver driver=new ChromeDriver();
		driver.get("http://localhost:8888/");
		
		//load all the elmts in object "s".
		SampleTestWithPOM s=PageFactory.initElements(driver,SampleTestWithPOM.class);
		
		s.ele1.sendKeys("admin");
		s.ele2.sendKeys("admin");
		//after refresing the webpage not giving StaleElementReferenceException due to @findBy. 
		//Because POM is used here.
		driver.navigate().refresh();
		
		s.ele1.sendKeys("admin");
		s.ele2.sendKeys("admin");
		s.ele3.click();
	}
	

}
