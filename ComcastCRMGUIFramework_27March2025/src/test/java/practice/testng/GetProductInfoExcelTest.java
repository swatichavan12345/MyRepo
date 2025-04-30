package practice.testng;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.comcast.crm.generic.fileutility.ExcelUtility;

public class GetProductInfoExcelTest {
	// if excel has 2 rows test case execute for 2 times, 3 rows= testcase exctn 3 times.
	@Test(dataProvider = "getData")
	public void getProductInfoTest(String brandname, String productname) throws InterruptedException, NoSuchElementException
	{
		WebDriver driver=new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get("https://www.amazon.in/");
		
		//search product
		driver.findElement(By.id("twotabsearchtextbox")).sendKeys(brandname,Keys.ENTER);
		
		//capture product info
		String path="//span[text()='"+productname+"']/../../../..//div[@class='a-row a-size-base a-color-base']";
		String price= driver.findElement(By.xpath(path)).getText();
		System.out.println(price);
		
		driver.quit();
	}
	
		
		@DataProvider
		public Object[][] getData() throws Throwable, NullPointerException
		{
			
			ExcelUtility elib=new ExcelUtility();
			int rowCount=elib.getRowCount("product");
			Object[][] objArr=new Object[rowCount][2];
			
			for(int i=0; i<rowCount; i++)
			{
			objArr[i][0]= elib.getdataFromExcel("product", i+1, 0);
			objArr[i][1]= elib.getdataFromExcel("product", i+1, 1);
			}
			return objArr;
		}
}
