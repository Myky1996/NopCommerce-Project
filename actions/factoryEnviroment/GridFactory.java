package factoryEnviroment;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.opera.OperaOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariOptions;

 import commons.BROWSER;
import io.github.bonigarcia.wdm.WebDriverManager;

public class GridFactory {
	private String browserName;
	private String ipAddress;
	private String portNumber;
	private WebDriver driver;

	public GridFactory(String browserName, String ipAddress, String portNumber) {
		this.browserName = browserName;
		this.ipAddress = ipAddress;
		this.portNumber = portNumber;
	}

	public WebDriver createDriver() {
		BROWSER browser = BROWSER.valueOf(browserName.toUpperCase());
		DesiredCapabilities capability = null;

		if (browser == BROWSER.FIREFOX) {
			WebDriverManager.firefoxdriver().setup();
			FirefoxOptions options = new FirefoxOptions();
			capability = DesiredCapabilities.chrome();
			capability.setBrowserName("firefox");
			capability.setPlatform(Platform.ANY);
			options.merge(capability);
			
		} else if (browser == BROWSER.CHROME) {
			WebDriverManager.chromedriver().setup();
			ChromeOptions options = new ChromeOptions();
			capability = DesiredCapabilities.chrome();
			capability.setBrowserName("chrome");
			capability.setPlatform(Platform.ANY);
			options.merge(capability);
			
		} else if (browser == BROWSER.OPERA) {
			WebDriverManager.operadriver().setup();
			capability.setBrowserName("opera");
			capability.setPlatform(Platform.ANY);
			OperaOptions options = new OperaOptions();
			options.merge(capability);
			
		} else if (browser == BROWSER.SAFARI) {
			WebDriverManager.safaridriver().setup();
			capability.setBrowserName("safari");
			capability.setPlatform(Platform.ANY);
			SafariOptions options = new SafariOptions();
			options.merge(capability);
		} else {
			throw new RuntimeException("Please input valid browser name value!");
		}
		try {
			driver = new RemoteWebDriver(new URL(String.format("http://%s:%s/wd/hub", ipAddress, portNumber)),
					capability);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return driver;
	}

}
