package practice.testng;

import org.testng.annotations.Test;

public class Order_dependsOnMethodsTest {
	
	@Test
	public void createOrderTest()
	{
		System.out.println("execute createOrderTest ==> 123");
	}
	@Test(dependsOnMethods = "createOrderTest")
	public void billingOrderTest()
	{
		System.out.println("execute billingOrderTest ==> 123");
	}

	
}
