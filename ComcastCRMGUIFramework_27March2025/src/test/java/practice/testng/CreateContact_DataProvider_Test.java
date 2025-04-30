package practice.testng;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class CreateContact_DataProvider_Test {
	@Test(dataProvider = "getData")
	
	public  void createContact(String firstname, String lastname)
	{
		System.out.println("firstname="+firstname+" ,lastname= "+lastname);
	}
	
	@DataProvider
	public Object[][] getData()
	{
		Object[][] objArr=new Object[3][2];
		objArr[0][0]= "Swati";
		objArr[0][1]= "HR";
		
		objArr[1][0]= "Sam";
		objArr[1][1]= "HD";
		
		objArr[2][0]= "John";
		objArr[2][1]= "Steve";
		
		return objArr;
	}
}
