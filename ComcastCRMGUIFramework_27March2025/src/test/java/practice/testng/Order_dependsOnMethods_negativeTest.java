package practice.testng;

import org.testng.annotations.Test;

public class Order_dependsOnMethods_negativeTest {
	
	@Test
	public void createOrderTest()
	{
		System.out.println("execute createOrderTest ==> 123");
		String str=null;
		System.out.println(str.equals(123));
	}
	@Test(dependsOnMethods = "createOrderTest")
	public void billingOrderTest()
	{
		System.out.println("execute billingOrderTest ==> 123");
	}

	
}
