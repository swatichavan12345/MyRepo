package practice.testng;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.events.EventFiringDecorator;
import org.openqa.selenium.support.events.WebDriverListener;
import org.testng.annotations.Test;

public class SampleTestForScreenshot {
	
	@Test
	
	public  void amazonTest() throws InterruptedException, IOException
	{
		WebDriver driver=new ChromeDriver();
		driver.get("https://www.amazon.in/");
		
		WebElement ele=driver.findElement(By.linkText("Mobiles"));
		File src = ele.getScreenshotAs(OutputType.FILE);
//		TakesScreenshot sc=(TakesScreenshot) ele;
//		Thread.sleep(4000);
		
//		File src=sc.getScreenshotAs(OutputType.FILE);
		Thread.sleep(4000);
		
		File dest=new File("../ComcastCRMGUIFramework_27March2025/Screenshot_Amazon_10thApril2025/amazon.png");
		FileUtils.copyFile(src, dest);
		Thread.sleep(4000);
		
		driver.close();
	}

}
