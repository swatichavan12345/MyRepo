package browser_stack;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;

public class BrowserStackCode {

	@Test
	public void sampleTest() throws Throwable {
		String userName = "swatichavan_VSJmcn";
		String pwssword = "hXhWzR241yuN6sNdWWAz";

		// also pass the capability object
		MutableCapabilities capabilities = new MutableCapabilities();
		HashMap<String, Object> bstackOptions = new HashMap<String, Object>();
		capabilities.setCapability("browserName", "Chrome");
		bstackOptions.put("os", "Windows");
		bstackOptions.put("osVersion", "10");
		bstackOptions.put("browserVersion", "120.0");
		//bstackOptions.put("userName", "swatichavan_VSJmcn");
		//bstackOptions.put("accessKey", "hXhWzR241yuN6sNdWWAz");
		bstackOptions.put("consoleLogs", "info");
		capabilities.setCapability("bstack:options", bstackOptions);

		// we have to pass 3 params/argnts while using RemoteWebdriver, doing
		// concatenation for username and password.
		RemoteWebDriver driver = new RemoteWebDriver(
				new URL("https://" + userName + ":" + pwssword + "@hub-cloud.browserstack.com/wd/hub"), capabilities);

		driver.get("https://www.google.com/");
		System.out.println(driver.getTitle());driver.close();
	}

}
