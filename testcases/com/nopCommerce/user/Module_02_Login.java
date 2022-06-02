package com.nopCommerce.user;

import java.util.Random;

import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import commons.BaseTest;
import commons.PageGeneratorManager;
import enviromentConfig.Enviroment;
import pageObjects.nopCommerce.user.HomePageObject;
import pageObjects.nopCommerce.user.LoginPO;
import pageObjects.nopCommerce.user.registerPO;
import reportConfig.ExtentTestManager;

public class Module_02_Login extends BaseTest {
	Enviroment enviroment;
	WebDriver driver;
	HomePageObject homePage;
	registerPO registerPage;
	LoginPO loginPage;

	String firstName, lastName, existingEmail, password, confirmPassword, incorrectPassword;

	@Parameters({ "envName", "severName", "browser", "ipAddress", "portNumber", "osName" })
	@BeforeClass
	public void BeforeClass(@Optional("local") String envName, @Optional("testing") String severName,
			@Optional("chrome") String browserName, @Optional("localhost") String ipAddress,
			@Optional("4444") String portNumber, @Optional("Windows 10") String osName) {
		ConfigFactory.setProperty("env", severName);
		enviroment = ConfigFactory.create(Enviroment.class);
		firstName = "Ky";
		lastName = "My";
		existingEmail = "mickey7283@yopmail.com";
		password = "12345678";
		incorrectPassword = "12356";

		ExtentTestManager.getTest().log(Status.INFO, "Pre-condition - Step 01: Open browser '" + browserName + "' and navigate to '" + enviroment.userAppUrl()
				+ "' ");

		driver = getBrowserDriver(envName, enviroment.userAppUrl(), browserName, ipAddress, portNumber, osName);
		homePage = PageGeneratorManager.getHompageObject(driver);

		ExtentTestManager.getTest().log(Status.INFO, "Pre-condition - Step 02: Open 'Login' page");
		homePage.openHeaderFooterPageByText(driver, "Log in");
		loginPage = PageGeneratorManager.getLoginPageObject(driver);
	}

	@Test
	public void TC_01_Login_With_Empty_Data() {
		ExtentTestManager.getTest().log(Status.INFO, "Register - Step 01: Click 'Login' button");
		loginPage.clickToButtonByText(driver, "Log in");

		ExtentTestManager.getTest().log(Status.INFO, "Register - Step 02: Verify error msg at Email textbox");
		verifyEquals(loginPage.getErrorMessageAtEmailTextbox(driver), "Please enter your email");

	}

	@Test
	public void TC_02_Login_With_Invalid_Email() {

		ExtentTestManager.getTest().log(Status.INFO, "Register - Step 01: Fill in email textbox");
		loginPage.enterTextToTextboxByName(driver, "Email", "buimyky");

		ExtentTestManager.getTest().log(Status.INFO, "Register - Step 02: Click 'Login' button");
		loginPage.clickToButtonByText(driver, "Log in");

		ExtentTestManager.getTest().log(Status.INFO, "Register - Step 03: Verify 'Wrong email'");
		verifyEquals(loginPage.getErrorMessageAtEmailTextbox(driver), "Wrong email");
	}

	@Test
	public void TC_03_Login_With_Unregisterd_Email() {
		ExtentTestManager.getTest().log(Status.INFO, "Register - Step 01: Fill in email textbox");
		loginPage.enterTextToTextboxByName(driver, "Email", "buimyky@gmail.net");

		ExtentTestManager.getTest().log(Status.INFO, "Register - Step 02: Click 'Login' button");
		loginPage.clickToButtonByText(driver, "Log in");

		ExtentTestManager.getTest().log(Status.INFO, "Register - Step 03: Verify 'Wrong email'");
		verifyEquals(loginPage.getUnsuccesfullLoginMessage(driver),
				"Login was unsuccessful. Please correct the errors and try again.\nNo customer account found");
	}

	@Test
	public void TC_04_Login_with_Registered_Email_Empty_Password() {
		ExtentTestManager.getTest().log(Status.INFO, "Register - Step 01: Fill in email textbox");
		loginPage.enterTextToTextboxByName(driver, "Email", existingEmail);

		ExtentTestManager.getTest().log(Status.INFO, "Register - Step 02: Click 'Login' button");
		loginPage.clickToButtonByText(driver, "Log in");

		ExtentTestManager.getTest().log(Status.INFO, "Register - Step 03: Verify 'Wrong email'");
		verifyEquals(loginPage.getUnsuccesfullLoginMessage(driver),
				"Login was unsuccessful. Please correct the errors and try again.\nThe credentials provided are incorrect");
	}

	@Test
	public void TC_05_Login_with_Registered_Email_Wrong_Password() {
		ExtentTestManager.getTest().log(Status.INFO, "Register - Step 01: Fill in email textbox");
		loginPage.enterTextToTextboxByName(driver, "Email", existingEmail);

		ExtentTestManager.getTest().log(Status.INFO, "Register - Step 02: Fill in incorrect password textbox");
		loginPage.enterTextToTextboxByName(driver, "Email", incorrectPassword);

		ExtentTestManager.getTest().log(Status.INFO, "Register - Step 03: Click 'Login' button");
		loginPage.clickToButtonByText(driver, "Log in");

		ExtentTestManager.getTest().log(Status.INFO, "Register - Step 04: Verify 'Wrong email'");
		verifyEquals(loginPage.getUnsuccesfullLoginMessage(driver),
				"Login was unsuccessful. Please correct the errors and try again.\nThe credentials provided are incorrect");
	}

	@Test
	public void TC_06_Login_with_Registered_Email_Correct_Password() {
		ExtentTestManager.getTest().log(Status.INFO, "Register - Step 01: Fill in email textbox");
		loginPage.enterTextToTextboxByName(driver, "Email", existingEmail);

		ExtentTestManager.getTest().log(Status.INFO, "Register - Step 02: Fill in password textbox");
		loginPage.enterTextToTextboxByName(driver, "Password", password);

		ExtentTestManager.getTest().log(Status.INFO, "Register - Step 03: Click 'Login' button");
		loginPage.clickToButtonByText(driver, "Log in");

		homePage = PageGeneratorManager.getHompageObject(driver);
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	private int getRandomNumber() {
		Random rand = new Random();
		return rand.nextInt(99999);
	}

}
