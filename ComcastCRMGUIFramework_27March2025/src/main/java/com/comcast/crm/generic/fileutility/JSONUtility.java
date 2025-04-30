package com.comcast.crm.generic.fileutility;

import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JSONUtility {

	public String getDataFromJSONFile(String key) throws IOException, ParseException
	{
		FileReader fileR=new FileReader("./configAppData/appCommonData2.json");
		JSONParser parser=new JSONParser();
		Object obj=parser.parse(fileR);
		JSONObject map=(JSONObject) obj;//downcasting java object into jsonobject class.
		//get the value from JSon file using key
		System.out.println(map.get("url"));
		String data=(String) map.get(key);
		return data;
	}
	
}
