package practice.testng;

import org.testng.annotations.Test;
//Practice: Make each TestCase independent & execuet. Don't give priority.
public class ContactNoPriorityTest {
	
	@Test
	public void createContactTest()
	{
		System.out.println("execute createContactTest with --> HDFC");
	}
	
	@Test
	public void modifyContactTest()
	{
		System.out.println("Execute query insert contact in DB==> ICICI");
		System.out.println("execute modifyContactTest --> ICICI=> ICICI_1");
	}
	
	@Test
	public void deleteContactTest()
	{
		System.out.println("Execute query insert contact in DB==> UPI");
		System.out.println("execute deleteContactTest UPI");
	}
}
