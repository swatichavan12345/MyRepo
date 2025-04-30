package practice.testng;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.comcast.crm.basetest.BaseClass1;

public class InvoiceTest3 {

	@Test(retryAnalyzer = com.comcast.crm.listenerutility.RetryListenerImplement.class)
	public void activateSIM()
	{
		System.out.println("InvoiceTest3:  activate SIM");
		Assert.assertEquals("","Login");
		System.out.println("Step-1");
		System.out.println("Step-2");
		System.out.println("Step-3");
		System.out.println("Step-4");	
	}
}

