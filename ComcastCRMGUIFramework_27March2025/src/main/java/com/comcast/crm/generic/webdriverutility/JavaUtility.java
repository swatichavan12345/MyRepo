package com.comcast.crm.generic.webdriverutility;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class JavaUtility {
	
	public int getRandomNumber()
	{
		Random ranDom=new Random();
		int randNum=ranDom.nextInt(5000);
		return randNum;
	}
	
	public String getSystemDateYYYYMMDD()
	{
		Date dateObj=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		String date=sdf.format(dateObj);
		return date;
		
	}
	
	public String getRequiredDateYYYYMMDD(int days)
	{
		Date dateObject=new Date();
		SimpleDateFormat simple=new SimpleDateFormat("yyyy-MM-dd");//MM should be in upper case
		//String endDate=simple.format(dateObject);
		//date after 30 days
		//use Calendar class--> capture all calendar
		Calendar cal=simple.getCalendar();
		cal.add(Calendar.DAY_OF_MONTH, days);// since before 30 days--> 30
		//String startDate=simple.format(cal.getTime());
		//System.out.println("30 days before act date: "+startDate);
		String requiredDate=simple.format(cal.getTime());
		return requiredDate;
	}// + days=> after days, -days=> before days.
}
