package com.nopCommerce.user;

import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import commons.PageGeneratorManager;
import pageObjects.nopCommerce.user.HomePageObject;
import pageObjects.nopCommerce.user.LoginPO;
import pageObjects.nopCommerce.user.registerPO;

public class Module_02_Login extends BaseTest {
	WebDriver driver;
	HomePageObject homePage;
	registerPO registerPage;
	LoginPO loginPage;

	String firstName, lastName, existingEmail, password, confirmPassword, incorrectPassword;

	@Parameters({ "browser", "url" })
	@BeforeClass
	public void BeforeClass(String browserName, String appUrl) {

		firstName = "Ky";
		lastName = "My";
		existingEmail = "mickey7283@yopmail.com";
		password = "12345678";
		incorrectPassword = "12356";
		

		log.info("Pre-condition - Step 01: Open browser '" + browserName + "' and navigate to '" + appUrl + "' ");

		driver = getBrowserDriver(browserName, appUrl);
		homePage = PageGeneratorManager.getHompageObject(driver);

		log.info("Pre-condition - Step 02: Open 'Login' page");
		homePage.openHeaderFooterPageByText(driver, "Log in");
		loginPage = PageGeneratorManager.getLoginPageObject(driver);
	}

	@Test
	public void TC_01_Login_With_Empty_Data() {
		log.info("Register - Step 01: Click 'Login' button");
		loginPage.clickToButtonByText(driver,"Log in");

		log.info("Register - Step 02: Verify error msg at Email textbox");
		verifyEquals(loginPage.getErrorMessageAtEmailTextbox(driver), "Please enter your email");
		

	}

	@Test
	public void TC_02_Login_With_Invalid_Email() {

		log.info("Register - Step 01: Fill in email textbox");
		loginPage.enterTextToTextboxByName(driver, "Email", "buimyky");
		
		log.info("Register - Step 02: Click 'Login' button");
		loginPage.clickToButtonByText(driver,"Log in");

		log.info("Register - Step 03: Verify 'Wrong email'");
		verifyEquals(loginPage.getErrorMessageAtEmailTextbox(driver), "Wrong email");
	}

	@Test
	public void TC_03_Login_With_Unregisterd_Email() {
		log.info("Register - Step 01: Fill in email textbox");
		loginPage.enterTextToTextboxByName(driver, "Email", "buimyky@gmail.net");
		
		log.info("Register - Step 02: Click 'Login' button");
		loginPage.clickToButtonByText(driver,"Log in");

		log.info("Register - Step 03: Verify 'Wrong email'");
		verifyEquals(loginPage.getUnsuccesfullLoginMessage(driver), "Login was unsuccessful. Please correct the errors and try again.\nNo customer account found");
	}

	@Test
	public void TC_04_Login_with_Registered_Email_Empty_Password() {
		log.info("Register - Step 01: Fill in email textbox");
		loginPage.enterTextToTextboxByName(driver, "Email", existingEmail);
		
		log.info("Register - Step 02: Click 'Login' button");
		loginPage.clickToButtonByText(driver,"Log in");

		log.info("Register - Step 03: Verify 'Wrong email'");
		verifyEquals(loginPage.getUnsuccesfullLoginMessage(driver), "Login was unsuccessful. Please correct the errors and try again.\nThe credentials provided are incorrect");
	}

	@Test
	public void TC_05_Login_with_Registered_Email_Wrong_Password() {
		log.info("Register - Step 01: Fill in email textbox");
		loginPage.enterTextToTextboxByName(driver, "Email", existingEmail);
		
		log.info("Register - Step 02: Fill in incorrect password textbox");
		loginPage.enterTextToTextboxByName(driver, "Email", incorrectPassword);
		
		log.info("Register - Step 03: Click 'Login' button");
		loginPage.clickToButtonByText(driver,"Log in");

		log.info("Register - Step 04: Verify 'Wrong email'");
		verifyEquals(loginPage.getUnsuccesfullLoginMessage(driver), "Login was unsuccessful. Please correct the errors and try again.\nThe credentials provided are incorrect");
	}

	@Test
	public void TC_06_Login_with_Registered_Email_Correct_Password() {
		log.info("Register - Step 01: Fill in email textbox");
		loginPage.enterTextToTextboxByName(driver, "Email", existingEmail);
		
		log.info("Register - Step 02: Fill in password textbox");
		loginPage.enterTextToTextboxByName(driver, "Password", password);
		
		log.info("Register - Step 03: Click 'Login' button");
		loginPage.clickToButtonByText(driver,"Log in");
		
		homePage = PageGeneratorManager.getHompageObject(driver);
	}

	private int getRandomNumber() {
		Random rand = new Random();
		return rand.nextInt(99999);
	}

}
