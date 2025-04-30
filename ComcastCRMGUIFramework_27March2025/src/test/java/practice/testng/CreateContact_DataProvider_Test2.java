package practice.testng;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class CreateContact_DataProvider_Test2 {
	@Test(dataProvider = "getData")
	
	public  void createContact(String firstname, String lastname, String phoneNumber)
	{
		System.out.println("firstname="+firstname+" ,lastname= "+lastname+" ,phoneNumber"+phoneNumber);
	}
	
	@DataProvider
	public Object[][] getData()
	{
		Object[][] objArr=new Object[3][3];
		objArr[0][0]= "Swati";
		objArr[0][1]= "HR";
		objArr[0][2]= "9977445611";
		
		objArr[1][0]= "Sam";
		objArr[1][1]= "HD";
		objArr[1][2]= "9926800022";
		
		objArr[2][0]= "John";
		objArr[2][1]= "Steve";
		objArr[2][2]= "9827885733";
		
		return objArr;
	}
}
