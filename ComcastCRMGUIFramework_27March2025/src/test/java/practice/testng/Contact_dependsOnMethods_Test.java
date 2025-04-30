package practice.testng;

import org.testng.annotations.Test;
//Practice: execuet with & Without priority.
public class Contact_dependsOnMethods_Test {
	
	@Test
	public void createContactTest()
	{
		System.out.println("execute createContactTest with ==>HDFC");
	}
	
	@Test(dependsOnMethods = "createContactTest")
	public void modifyContactTest()
	{
		System.out.println("execute modifyContactTest ==>HDFC->ICICI");
	}
	
	@Test(dependsOnMethods = "modifyContactTest")
	public void deleteContactTest()
	{
		System.out.println("execute deleteContactTest ICICI");
	}
}
