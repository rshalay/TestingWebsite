package framework;

import java.net.URL;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class GridDriver {
	public static final String FIREFOX = "firefox";
	public static final String CHROME = "chrome";
	
	private RemoteWebDriver driver;
	
	public RemoteWebDriver getInstance(String browser) throws Exception{
		DesiredCapabilities capabilities = new DesiredCapabilities();
		if(CHROME.equals(browser)){
			capabilities = DesiredCapabilities.chrome();
			capabilities.setBrowserName(DesiredCapabilities.chrome().getBrowserName());
			
			driver = new RemoteWebDriver(new URL("http://localhost:5556/wd/hub"),capabilities);				
		} else if(FIREFOX.equals(browser)){
			capabilities = DesiredCapabilities.firefox();
			capabilities.setBrowserName(DesiredCapabilities.firefox().getBrowserName());
			
			driver = new RemoteWebDriver(new URL("http://localhost:5555/wd/hub"),capabilities);
		} else 
			throw new RuntimeException("Invalid browser setup");
		
		return driver;		
	}
}
