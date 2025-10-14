package users.api.genericutility;

import java.io.IOException;
import java.util.List;
import static io.restassured.RestAssured.*;
import com.jayway.jsonpath.JsonPath;
import io.restassured.response.Response;
//import org.json.simple.JSONObject;
//import org.json.simple.parser.JSONParser;
//import org.json.simple.parser.ParseException;

public class JSONUtility {

	/* public String getDataFromJSONFile(String key) throws IOException, ParseException
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
	*/
	FileUtility fLib=new FileUtility();
	public String getDataOnJsonPath(Response resp, String jsonXpath)
	{
		List<Object> list=JsonPath.read(resp.asString(),jsonXpath);
			return list.get(0).toString();
	}
	public String getDataOnXpathPath(Response resp, String xmlXpath)
	{
		return resp.xmlPath().get(xmlXpath);
	}
	public boolean VerifyDataOnJsonPath(Response resp, String jsonXpath, String expectedData)
	{
		List<String> list=JsonPath.read(resp.asString(),jsonXpath);
			boolean flag=false;
		for(String str: list) {
			if(str.equals(expectedData)) {
				System.out.println(expectedData ="is available==PASS");
				flag=true;
			}
		}
		if(flag ==false)
		{
			System.out.println(expectedData ="is not available==PASS");
		}
		return false;
	}
	
	public String getAccessToken() throws IOException
	{
		Response resp= given()
				.formParam("client_id", fLib.getDtaFromPropertiesFile("ClientID"))
				.formParam("client_secret",fLib.getDtaFromPropertiesFile("ClientSecret"))
				.formParam("grant_type","client_credentials")
			.when().post("http://49.249.28.218:8180/auth/realms/ninza/protocol/openid-connect/token");
				resp.then()
				.log().all();
		//capture data from the Response
		String token=resp.jsonPath().get("access_token");
		return token;
	}
}
