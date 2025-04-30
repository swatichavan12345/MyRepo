package practice.hometest;

import java.lang.reflect.Method;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class HomePageSample_ReporterTest {
	
	@Test
	public void homePageTest(Method mtd)
	//to call method dynamically-> import java.lang.reflect.Method;
	{
		Reporter.log(mtd.getName() +" Test Start ");
		//logs displayed into TestNG Report-> emailable-report.html
		Reporter.log("Step-1");
		Reporter.log("Step-2");
		Reporter.log("Step-3");
		Reporter.log("Step-4");
		//logs displayed into both: 1)TestNG report-> emailable-report.html, 2)eclipse.
		Reporter.log("Step-5", true);
		Reporter.log("Step-6", true);
		Reporter.log(mtd.getName() +" Test End ");
	}
	
	@Test
	public void verifyLogoHomePageTest(Method mtd)
	{
		Reporter.log(mtd.getName() +" Test Start ");
		Reporter.log("Step-1");
		Reporter.log("Step-2");
		Reporter.log("Step-3");
		Reporter.log("Step-4");
		Reporter.log("Step-5", true);
		Reporter.log("Step-6", true);
		Reporter.log(mtd.getName() +" Test End ");
	}
}
