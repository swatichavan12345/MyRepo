package practice.testng;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class GetProductInfoTest {
	
	@Test(dataProvider = "getData")
	public void getProductInfoTest(String brandname, String productname) throws InterruptedException, NoSuchElementException
	{
		WebDriver driver=new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get("https://www.amazon.in/");
		
		//search product
		driver.findElement(By.id("twotabsearchtextbox")).sendKeys("iphone");
		driver.findElement(By.id("nav-search-submit-button")).click();
		
		//capture product info
		String path="//span[text()='"+productname+"']/../../../..//div[@class='a-row a-size-base a-color-base']";
		//String path="//span[text()='"+productname+"']/../../../../div[3]/div[1]/div/div[1]/div[1]/a/span/span[2]/span[2]";
		String price= driver.findElement(By.xpath(path)).getText();
		System.out.println(price);
		driver.quit();
	}
	
		
		@DataProvider
		public Object[][] getData()
		{
			Object[][] objArr=new Object[3][2];
			objArr[0][0]= "iphone";
			objArr[0][1]= "Apple iPhone 15 (128 GB) - Black";
				
			objArr[1][0]= "iphone";
			objArr[1][1]= "Apple iPhone 15 (128 GB) - Green";
			
			objArr[2][0]= "iphone";
			objArr[2][1]= "Apple iPhone 15 (256 GB) - Black";
			
			return objArr;
		}
}
