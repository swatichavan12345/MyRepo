package practice.testng;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.comcast.crm.basetest.BaseClass1;

public class InvoiceTest2 extends BaseClass1{

	@Test
	public void createInvoiceTest()
	{
		System.out.println("create Invoicetest");
		String actTitle=driver.getTitle();
		Assert.assertEquals(actTitle,"Login");
		System.out.println("Step-1");
		System.out.println("Step-2");
		System.out.println("Step-3");
		System.out.println("Step-4");	
	}
	
	@Test
	public void createInvoiceWithContactTest()
	{
		System.out.println("create createInvoiceWithContactTest");
		System.out.println("Step-1");
		System.out.println("Step-2");
		System.out.println("Step-3");
		System.out.println("Step-4");	
	}
	
}

