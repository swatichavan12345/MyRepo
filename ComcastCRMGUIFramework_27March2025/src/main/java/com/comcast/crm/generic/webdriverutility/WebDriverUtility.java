package com.comcast.crm.generic.webdriverutility;

import java.sql.Driver;
import java.time.Duration;
import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebDriverUtility {
	
	public void waitForPageToLoad(WebDriver driver)
	{
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));	
	}

	public void waitForElementPresent(WebDriver driver, WebElement ele)
	{
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOf(ele));	
	}
	public void switchToTabOnURL(WebDriver driver, String partialURL)
	{
		Set<String> set =driver.getWindowHandles();
		Iterator<String> it=set.iterator();
		while(it.hasNext())//pointing to 1st elemt
		{
			String windowId=it.next();
			driver.switchTo().window(windowId);
			
			String actUrl=driver.getCurrentUrl();
			if(actUrl.contains(partialURL))
			{
				break;
			}
		}
	}
	public void switchToTabOnTitle(WebDriver driver, String partialTitle)
	{
		Set<String> set =driver.getWindowHandles();
		Iterator<String> it=set.iterator();
		while(it.hasNext())//pointing to 1st elemt
		{
			String windowId=it.next();
			driver.switchTo().window(windowId);
			
			String actUrl=driver.getCurrentUrl();
			if(actUrl.contains(partialTitle))
			{
				break;
			}
		}
	}
	//****3 ways to SwirchTo Fram, These are overloaded methods.
	public void switchToFrame(WebDriver driver, int index)
	{
		driver.switchTo().frame(index);
	}
	public void switchToFrame(WebDriver driver, String nameID)
	{
		driver.switchTo().frame(nameID);
	}
	public void switchToFrame(WebDriver driver, WebElement ele)
	{
		driver.switchTo().frame(ele);
	}
	public void switchTOAlertAndAccept(WebDriver driver)
	{
		driver.switchTo().alert().accept();
	}
	public void switchTOAlertAndDismiss(WebDriver driver)
	{
		driver.switchTo().alert().dismiss();
	}
	public void select(WebElement ele, String text)
	{
		Select sel=new Select(ele);
		sel.selectByVisibleText(text);
	}
	public void select(WebElement ele, int index)
	{
		Select sel=new Select(ele);
		sel.selectByIndex(index);
	}
	public void mouseMoveOnElement(WebDriver driver,WebElement ele)
	{
		Actions act=new Actions(driver);
		act.moveToElement(ele).perform();
	}
	public void doubleClick(WebDriver driver,WebElement ele)
	{
		Actions act=new Actions(driver);
		act.doubleClick(ele).perform();
	}
}
