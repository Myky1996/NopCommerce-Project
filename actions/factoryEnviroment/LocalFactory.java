package factoryEnviroment;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class LocalFactory {
	private String browserName;
	private WebDriver driver;

	public LocalFactory(String browserName) {
		this.browserName = browserName;
	}

	public WebDriver createDriver() {
		if (browserName.equals("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		} else if (browserName.equals("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
//			File file = new File(projectPath + File.separator + "browserExtensions" + File.separator + "extension_1_6_6_0 (1).crx");
//			ChromeOptions options = new ChromeOptions();
			// options.addExtensions(file);
//			options.addArguments("--lang=vi");
//			options.addArguments("--disable-inforbars");
//			options.addArguments("--disable-notifications");
//			options.addArguments("--disable-geolocation");

		} else if (browserName.equals("safari")) {
			driver = new SafariDriver();
		} else {
			throw new RuntimeException("Browser name is invalid");
		}
		return driver;
	}

}
