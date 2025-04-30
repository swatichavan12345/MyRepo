package com.comcast.crm.generic.fileutility;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class FileUtility {
	
	public String getDtaFromPropertiesFile(String key) throws IOException
	{
		FileInputStream fis=new FileInputStream("./configAppData/Assigt2_vtiger.properties");
		Properties pObj=new Properties();
		pObj.load(fis);
		String data=pObj.getProperty(key);// it will gives value of the given key.
		return data;
		
	}

}
