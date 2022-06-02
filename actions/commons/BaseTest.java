package commons;

import java.util.List;
import java.util.concurrent.TimeUnit;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.testng.Assert;
import org.testng.Reporter;
import factoryEnviroment.GridFactory;
import factoryEnviroment.LocalFactory;
import factoryEnviroment.SaucelabFactory;

public class BaseTest {
	private WebDriver driver;
	protected final Log log;

	protected BaseTest() {
		log = LogFactory.getLog(getClass());
	}

	public WebDriver getDriverInstance() {
		return this.driver;
	}

	protected WebDriver getBrowserDriver(String envName, String severName, String browserName, String ipAddress,
			String portNumber, String osName) {
		switch (envName) {
		case "local":
			driver = new LocalFactory(browserName).createDriver();
			break;
		case "grid":
			driver = new GridFactory(browserName, ipAddress, portNumber).createDriver();
			break;
		case "saucelab":
			driver = new SaucelabFactory(browserName, osName).createDriver();
			break;
		default:
			driver = new LocalFactory(browserName).createDriver();
			break;
		}
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get(severName);
		return driver;
	}

	protected boolean verifyTrue(boolean condition) {
		boolean pass = true;
		try {
			if (condition == true) {
				log.info(" -------------------------- PASSED -------------------------- ");
			} else {
				log.info(" -------------------------- FAILED -------------------------- ");
			}
			Assert.assertTrue(condition);
		} catch (Throwable e) {
			pass = false;

			// Add lỗi vào ReportNG
//			VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
//			Reporter.getCurrentTestResult().setThrowable(e);
		}
		return pass;
	}

	protected boolean verifyFalse(boolean condition) {
		boolean pass = true;
		try {
			if (condition == false) {
				log.info(" -------------------------- PASSED -------------------------- ");
			} else {
				log.info(" -------------------------- FAILED -------------------------- ");
			}
			Assert.assertFalse(condition);
		} catch (Throwable e) {
			pass = false;
//			VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
//			Reporter.getCurrentTestResult().setThrowable(e);
		}
		return pass;
	}

	private boolean checkEquals(Object actual, Object expected) {
		boolean pass = true;
		try {
			Assert.assertEquals(actual, expected);
			log.info(" -------------------------- PASSED -------------------------- ");
		} catch (Throwable e) {
			pass = false;
			log.info(" -------------------------- FAILED -------------------------- ");
			VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
			Reporter.getCurrentTestResult().setThrowable(e);
		}
		return pass;
	}

	protected boolean verifyEquals(Object actual, Object expected) {
		return checkEquals(actual, expected);
	}

//	@BeforeTest
//	public void deleteAllFilesInReportNGScreenshot() {
//		log.info("---------- START delete file in folder ----------");
//
//		try {
//			String workingDir = System.getProperty("user.dir");
//			String pathFolderDownload = workingDir + "/allure-results";
//			File file = new File(pathFolderDownload);
//			File[] listOfFiles = file.listFiles();
//			for (int i = 0; i < listOfFiles.length; i++) {
//				if (listOfFiles[i].isFile()) {
//					System.out.println(listOfFiles[i].getName());
//					new File(listOfFiles[i].toString()).delete();
//				}
//			}
//		} catch (Exception e) {
//			System.out.print(e.getMessage());
//		}
//		log.info("---------- END delete file in folder ----------");
//	}

	protected void showBrowserConsoleLogs(WebDriver driver) {
		if (driver.toString().contains("chrome")) {
			LogEntries logs = driver.manage().logs().get("browser");
			List<LogEntry> logList = logs.getAll();
			for (LogEntry logging : logList) {
				log.info("-------------- " + logging.getLevel().toString() + "------------\n" + logging.getMessage());
			}
		}
	}

}
