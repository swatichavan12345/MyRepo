package practice.pom.repository_2ndApril2025;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class SampleTestWithoutPOM {

	public static void main(String[] args) {
		WebDriver driver=new ChromeDriver();
		driver.get("http://localhost:8888/");
		WebElement ele1= driver.findElement(By.name("user_name"));
		ele1.sendKeys("admin");
		
		WebElement ele2= driver.findElement(By.name("user_password"));
		ele2.sendKeys("admin");
		
		WebElement ele3=driver.findElement(By.id("submitButton"));
		ele3.click();
		//till this code is ok, but after refresing the webpage the StaleElementReferenceException. 
		//so POM is used.
		driver.navigate().refresh();
		
		ele1.sendKeys("admin");
		ele2.sendKeys("admin");
		ele3.click();
		
	}
}
