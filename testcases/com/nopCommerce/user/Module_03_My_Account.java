package com.nopCommerce.user;

import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import commons.PageGeneratorManager;
import pageObjects.nopCommerce.user.AddYourReviewPO;
import pageObjects.nopCommerce.user.HomePageObject;
import pageObjects.nopCommerce.user.LoginPO;
import pageObjects.nopCommerce.user.MyAccountPO;
import pageObjects.nopCommerce.user.ProductDetailPO;
import pageObjects.nopCommerce.user.TopMenuSubPagePO;
import pageObjects.nopCommerce.user.registerPO;

public class Module_03_My_Account extends BaseTest {
	WebDriver driver;
	HomePageObject homePage;
	registerPO registerPage;
	LoginPO loginPage;
	TopMenuSubPagePO topMenuSubPage;
	MyAccountPO myAccountPage;
	ProductDetailPO productDetailPage;
	AddYourReviewPO addYourReviewPage;
	String password, existingEmail, newPassword;

	@Parameters({ "browser", "url" })
	@BeforeClass
	public void BeforeClass(String browserName, String appUrl) {

		existingEmail = "onichan6@yopmail.com";
		password = "onichan6@yopmail.com";
		newPassword = "buinhuhoa";
		log.info("Pre-condition - Step 01: Open browser '" + browserName + "' and navigate to '" + appUrl + "' ");

		driver = getBrowserDriver(browserName, appUrl);
		homePage = PageGeneratorManager.getHompageObject(driver);

		log.info("Pre-condition - Step 02: Open 'Login' page");
		homePage.openHeaderFooterPageByText(driver, "Log in");
		loginPage = PageGeneratorManager.getLoginPageObject(driver);

		log.info("Register - Step 01: Fill in email textbox");
		loginPage.enterTextToTextboxByName(driver, "Email", existingEmail);

		log.info("Register - Step 02: Fill in password textbox");
		loginPage.enterTextToTextboxByName(driver, "Password", password);

		log.info("Register - Step 03: Click 'Login' button");
		loginPage.clickToButtonByText(driver, "Log in");

		homePage = PageGeneratorManager.getHompageObject(driver);

	}

//	@Test
	public void TC_01_Customer_info() {
		log.info("Customer info - Step 00: Open My account page");
		homePage.openHeaderFooterPageByText(driver, "My account");
		myAccountPage = PageGeneratorManager.getMyAccountPageObject(driver);

		log.info("Customer info - Step 01: Select gender");
		myAccountPage.clickToRadioButtonByLabel(driver, "Male");

		log.info("Customer info - Step 02: Input firstname");
		myAccountPage.enterTextToTextboxByName(driver, "FirstName", "Automation");

		log.info("Customer info - Step 03: Input lastname");
		myAccountPage.enterTextToTextboxByName(driver, "LastName", "FC");

		log.info("Customer info - Step 04: Select DOB");
		myAccountPage.selectOptionInDropdownByText(driver, "DateOfBirthDay", "1");
		myAccountPage.selectOptionInDropdownByText(driver, "DateOfBirthMonth", "January");
		myAccountPage.selectOptionInDropdownByText(driver, "DateOfBirthYear", "1990");

		log.info("Customer info - Step 05: Input Email");
		myAccountPage.enterTextToTextboxByName(driver, "Email", "automationfc.vn@gmail.com");

		log.info("Customer info - Step 06: Input Company name");
		myAccountPage.enterTextToTextboxByName(driver, "Company", "Automation FC");

		log.info("Customer info - Step 07: Click 'Save' button");
		myAccountPage.clickToButtonByText(driver, "Save");

		log.info("Customer info - Step 08: Verify updated info");
		verifyTrue(myAccountPage.isRadiobuttonSelected(driver, "gender-female"));
		verifyEquals(myAccountPage.getTextboxValueByName(driver, "FirstName"), "Automation");
		verifyEquals(myAccountPage.getTextboxValueByName(driver, "LastName"), "FC");
		verifyEquals(myAccountPage.getSelectedOption(driver, "DateOfBirthDay"), "1");
		verifyEquals(myAccountPage.getSelectedOption(driver, "DateOfBirthMonth"), "January");
		verifyEquals(myAccountPage.getSelectedOption(driver, "DateOfBirthYear"), "1990");
		verifyEquals(myAccountPage.getTextboxValueByName(driver, "Email"), "automationfc.vn@gmail.com");
		verifyEquals(myAccountPage.getTextboxValueByName(driver, "Company"), "Automation FC");
	}

//	@Test
	public void TC_02_Address() {
		myAccountPage.openSidePageByText(driver, "Addresses");
		myAccountPage.clickToButtonByText(driver, "Add new");
		myAccountPage.enterTextToTextboxByName(driver, "Address.FirstName", "Automation");
		myAccountPage.enterTextToTextboxByName(driver, "Address.LastName", "FC");
		myAccountPage.enterTextToTextboxByName(driver, "Address.Email", "automationfc.vn@gmail.com");
		myAccountPage.enterTextToTextboxByName(driver, "Address.Company", "Automation FC");

		myAccountPage.selectOptionInDropdownByText(driver, "Address.CountryId", "Viet Nam");
		myAccountPage.selectOptionInDropdownByText(driver, "Address.StateProvinceId", "Other");

		myAccountPage.enterTextToTextboxByName(driver, "Address.City", "Hanoi");
		myAccountPage.enterTextToTextboxByName(driver, "Address.Address1", "219 Trung Kinh");
		myAccountPage.enterTextToTextboxByName(driver, "Address.Address2", "1 Trung Yen");
		myAccountPage.enterTextToTextboxByName(driver, "Address.ZipPostalCode", "550000");
		myAccountPage.enterTextToTextboxByName(driver, "Address.PhoneNumber", "0123456789");
		myAccountPage.enterTextToTextboxByName(driver, "Address.FaxNumber", "0366823383");

		log.info("Customer info - Step 07: Click 'Save' button");
		myAccountPage.clickToButtonByText(driver, "Save");

	}

//	@Test
	public void TC_03_Change_Password() {

		log.info("Change password - Step 01: Open changep password tab");
		myAccountPage.openSidePageByText(driver, "Change password");

		log.info("Change password - Step 02: Fill in Old pw textbox");
		myAccountPage.enterTextToTextboxByName(driver, "OldPassword", password);

		log.info("Change password - Step 03: Fill in New pw textbox");
		myAccountPage.enterTextToTextboxByName(driver, "NewPassword", newPassword);

		log.info("Change password - Step 04: Fill in Confirm pw textbox");
		myAccountPage.enterTextToTextboxByName(driver, "ConfirmNewPassword", newPassword);

		log.info("Customer info - Step 05: Click 'Change password' button");
		myAccountPage.clickToButtonByText(driver, "Change password");

		log.info("Customer info - Step 06: Verify success message");
		verifyEquals(myAccountPage.getSuccessMsgOnNotiBarByClass(driver,"bar-notification success"), "Password was changed");

		log.info("Customer info - Step 06: Close success message");
		myAccountPage.closeSuccesNotiBarByText(driver);

		log.info("Customer info - Step 07: Logout");
		myAccountPage.openHeaderFooterPageByText(driver, "Log out");
		homePage = PageGeneratorManager.getHompageObject(driver);

		log.info("Customer info - Step 08: Log in with Old password ");
		myAccountPage.openHeaderFooterPageByText(driver, "Log in");

		loginPage = PageGeneratorManager.getLoginPageObject(driver);

		loginPage.enterTextToTextboxByName(driver, "Email", existingEmail);

		loginPage.enterTextToTextboxByName(driver, "Password", password);

		log.info(" - Step 03: Click 'Login' button");
		loginPage.clickToButtonByText(driver, "Log in");
		verifyEquals(loginPage.getUnsuccesfullLoginMessage(driver),
				"Login was unsuccessful. Please correct the errors and try again.\nThe credentials provided are incorrect");

		log.info("Customer info - Step 08: Log in with New password ");
		loginPage.enterTextToTextboxByName(driver, "Password", newPassword);

		log.info(" - Step 03: Click 'Login' button");
		loginPage.clickToButtonByText(driver, "Log in");
		homePage = PageGeneratorManager.getHompageObject(driver);

	}

	@Test
	public void TC_04_My_Product_Review() {
		String reviewTitle = "Ky's review";
		String reviewText = "Pretty good";

		log.info("My product review - Step 01: Open Desktop page ");
		homePage.openTopMenuSubPageByText(driver, "Computers ", "Desktops ");
		topMenuSubPage = PageGeneratorManager.getTopMenuSubPage(driver);

		log.info("My product review - Step 02: Click to product title ");
		topMenuSubPage.clickToProductTitleByText(driver, "Build your own computer");
		productDetailPage = PageGeneratorManager.getProductDetailPageObject(driver);

		log.info("My product review - Step 03: Click to Add your product detail link ");
		addYourReviewPage = productDetailPage.clickToAddYourReviewLinkByText(driver);

		log.info("My product review - Step 04: Fill in Review title textbox ");
		addYourReviewPage.enterTextToTextboxByName(driver, "AddProductReview.Title", reviewTitle);

		log.info("My product review - Step 05: Fill in 'Review text' textarea ");
		addYourReviewPage.enterTextToTextAreaByName(driver, "AddProductReview.ReviewText", reviewText);

		log.info("My product review - Step 06: Select Rating ");
		addYourReviewPage.clickToRadioRatingButton(driver, "addproductrating_3");

		log.info("My product review - Step 07: Click to Submit button ");
		addYourReviewPage.clickToButtonByText(driver, "Submit review");

		log.info("My product review - Step 07: Open My product review page ");
		homePage.openHeaderFooterPageByText(driver, "My account");
		myAccountPage = PageGeneratorManager.getMyAccountPageObject(driver);

		myAccountPage.openSidePageByText(driver, "My product reviews");

		log.info("My product review - Step 07: Verify product detail display ");
		verifyEquals(myAccountPage.getTextProductReviewByClass(driver, "review-title"), reviewTitle);
		verifyEquals(myAccountPage.getTextProductReviewByClass(driver, "review-text"), reviewText);

	}

	private int getRandomNumber() {
		Random rand = new Random();
		return rand.nextInt(99999);
	}

}