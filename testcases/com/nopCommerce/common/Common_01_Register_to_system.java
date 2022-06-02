package com.nopCommerce.common;

import java.util.Random;
import java.util.Set;

import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.Status;

import commons.BaseTest;
import commons.PageGeneratorManager;
import enviromentConfig.Enviroment;
import pageObjects.nopCommerce.user.HomePageObject;
import pageObjects.nopCommerce.user.LoginPO;
import pageObjects.nopCommerce.user.registerPO;
import reportConfig.ExtentTestManager;

public class Common_01_Register_to_system extends BaseTest {
	WebDriver driver;
	Enviroment enviroment;
	HomePageObject homePage;
	registerPO registerPage;
	LoginPO loginPage;
	public static Set<Cookie> loginPageCookie;

	public static String emailAddress, password, firstName, lastName;

	@Parameters({ "envName", "severName", "browser", "ipAddress", "portNumber", "osName" })
	@BeforeTest
	public void BeforeClass(@Optional("local") String envName, @Optional("testing") String severName,
			@Optional("chrome") String browserName, @Optional("localhost") String ipAddress,
			@Optional("4444") String portNumber, @Optional("Windows 10") String osName) {
		ConfigFactory.setProperty("env", severName);
		enviroment = ConfigFactory.create(Enviroment.class);

		firstName = "Ky";
		lastName = "My";
		emailAddress = "mickey" + getRandomNumber() + "@yopmail.com";
		password = "12345678";

		ExtentTestManager.getTest().log(Status.INFO, "Pre-condition - Step 01: Open browser '" + browserName
				+ "' and navigate to '" + enviroment.userAppUrl() + "' ");
		driver = getBrowserDriver(envName, enviroment.userAppUrl(), browserName, ipAddress, portNumber, osName);
		homePage = PageGeneratorManager.getHompageObject(driver);

		ExtentTestManager.getTest().log(Status.INFO, "Common_01 - Step 00: Open 'Register' page");
		homePage.openHeaderFooterPageByText(driver, "Register");
		registerPage = PageGeneratorManager.getRegisterPageObject(driver);

		ExtentTestManager.getTest().log(Status.INFO, "Common_01 - Step 01: Fill in firstname textbox");
		registerPage.enterTextToTextboxByName(driver, "FirstName", firstName);

		ExtentTestManager.getTest().log(Status.INFO, "Common_01 - Step 02: Fill in lastname textbox");
		registerPage.enterTextToTextboxByName(driver, "LastName", lastName);

		ExtentTestManager.getTest().log(Status.INFO, "Common_01 - Step 03: Fill in email textbox");
		registerPage.enterTextToTextboxByName(driver, "Email", emailAddress);

		ExtentTestManager.getTest().log(Status.INFO, "Common_01 - Step 04: Fill in password textbox");
		registerPage.enterTextToTextboxByName(driver, "Password", password);

		ExtentTestManager.getTest().log(Status.INFO, "Common_01 - Step 05: Fill in confirm password textbox");
		registerPage.enterTextToTextboxByName(driver, "ConfirmPassword", password);

		ExtentTestManager.getTest().log(Status.INFO, "Common_01 - Step 06: Click 'Register' button");
		registerPage.clickToButtonByText(driver, "Register");

		ExtentTestManager.getTest().log(Status.INFO,
				"Common_01 - Step 07: Verify Register Successfully Message Displayed");
		verifyTrue(registerPage.isRegisterSuccessfullyMessageDisplayed());

		ExtentTestManager.getTest().log(Status.INFO, "Common_01 - Step 08: Click 'Logout' link");
		registerPage.openHeaderFooterPageByText(driver, "Log out");
		homePage = PageGeneratorManager.getHompageObject(driver);

		ExtentTestManager.getTest().log(Status.INFO, "Common_01: Step 09: Click to login link");
		homePage.openHeaderFooterPageByText(driver, "Log in");
		loginPage = PageGeneratorManager.getLoginPageObject(driver);

		ExtentTestManager.getTest().log(Status.INFO, "Common_01: Step 10: Input to email, pw textbox");
		loginPage.enterTextToTextboxByName(driver, "Email", emailAddress);
		loginPage.enterTextToTextboxByName(driver, "Password", password);

		ExtentTestManager.getTest().log(Status.INFO, "Common_01: Step 12: Click to login button");
		loginPage.clickToButtonByText(driver, "Log in");
		homePage = PageGeneratorManager.getHompageObject(driver);

		ExtentTestManager.getTest().log(Status.INFO, "Common_01: Step 13: Get all login page cookies");
		loginPageCookie = homePage.getAllCookies(driver);

		
		driver.quit();
	}

	private int getRandomNumber() {
		Random rand = new Random();
		return rand.nextInt(99999);
	}

}
