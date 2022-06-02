package com.nopCommerce.user;

import java.util.Random;

import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.nopCommerce.common.Common_01_Register_to_system;

import commons.BaseTest;
import commons.PageGeneratorManager;
import enviromentConfig.Enviroment;
import pageObjects.nopCommerce.user.AddYourReviewPO;
import pageObjects.nopCommerce.user.HomePageObject;
import pageObjects.nopCommerce.user.LoginPO;
import pageObjects.nopCommerce.user.MyAccountPO;
import pageObjects.nopCommerce.user.ProductDetailPO;
import pageObjects.nopCommerce.user.TopMenuSubPagePO;
import pageObjects.nopCommerce.user.registerPO;
import reportConfig.ExtentTestManager;

public class Module_03_My_Account extends BaseTest {
	Enviroment enviroment;
	WebDriver driver;
	HomePageObject homePage;
	registerPO registerPage;
	LoginPO loginPage;
	TopMenuSubPagePO topMenuSubPage;
	MyAccountPO myAccountPage;
	ProductDetailPO productDetailPage;
	AddYourReviewPO addYourReviewPage;
	String password, emailAddress, newPassword;

	@Parameters({ "envName", "severName", "browser", "ipAddress", "portNumber", "osName" })
	@BeforeClass
	public void BeforeClass(@Optional("local") String envName, @Optional("testing") String severName,
			@Optional("chrome") String browserName, @Optional("localhost") String ipAddress,
			@Optional("4444") String portNumber, @Optional("Windows 10") String osName) {
		ConfigFactory.setProperty("env", severName);
		enviroment = ConfigFactory.create(Enviroment.class);
		
		emailAddress = Common_01_Register_to_system.emailAddress;
		password = Common_01_Register_to_system.password;
		newPassword = "buinhuhoa";

		ExtentTestManager.getTest().log(Status.INFO, "Pre-condition - Step 01: Open browser '" + browserName + "' and navigate to '" + enviroment.userAppUrl()
				+ "' ");
		driver = getBrowserDriver(envName, enviroment.userAppUrl(), browserName, ipAddress, portNumber, osName);
		homePage = PageGeneratorManager.getHompageObject(driver);

		ExtentTestManager.getTest().log(Status.INFO, "Pre-condition - Step 02: Open 'Login' page");
		homePage.openHeaderFooterPageByText(driver, "Log in");
		loginPage = PageGeneratorManager.getLoginPageObject(driver);

		ExtentTestManager.getTest().log(Status.INFO, "Pre-condition - Step 03: Set cookies and reload page");
		loginPage.setCookies(driver, Common_01_Register_to_system.loginPageCookie);

		loginPage.refreshCurrentPage(driver);
		homePage = PageGeneratorManager.getHompageObject(driver);
		loginPage.closeNotiBarByText(driver);

	}

	@Test
	public void TC_01_Customer_info() {
		ExtentTestManager.getTest().log(Status.INFO, "Customer info - Step 00: Open My account page");
		homePage.openHeaderFooterPageByText(driver, "My account");
		myAccountPage = PageGeneratorManager.getMyAccountPageObject(driver);

		ExtentTestManager.getTest().log(Status.INFO, "Customer info - Step 01: Select gender");
		myAccountPage.clickToRadioButtonByLabel(driver, "Male");

		ExtentTestManager.getTest().log(Status.INFO, "Customer info - Step 02: Input firstname");
		myAccountPage.enterTextToTextboxByName(driver, "FirstName", "Automation");

		ExtentTestManager.getTest().log(Status.INFO, "Customer info - Step 03: Input lastname");
		myAccountPage.enterTextToTextboxByName(driver, "LastName", "FC");

		ExtentTestManager.getTest().log(Status.INFO, "Customer info - Step 04: Select DOB");
		myAccountPage.selectOptionInDropdownByText(driver, "DateOfBirthDay", "1");
		myAccountPage.selectOptionInDropdownByText(driver, "DateOfBirthMonth", "January");
		myAccountPage.selectOptionInDropdownByText(driver, "DateOfBirthYear", "1990");

		ExtentTestManager.getTest().log(Status.INFO, "Customer info - Step 05: Input Email");
		myAccountPage.enterTextToTextboxByName(driver, "Email", "automationfc.vn@gmail.com");

		ExtentTestManager.getTest().log(Status.INFO, "Customer info - Step 06: Input Company name");
		myAccountPage.enterTextToTextboxByName(driver, "Company", "Automation FC");

		ExtentTestManager.getTest().log(Status.INFO, "Customer info - Step 07: Click 'Save' button");
		myAccountPage.clickToButtonByText(driver, "Save");

		ExtentTestManager.getTest().log(Status.INFO, "Customer info - Step 08: Verify updated info");
		verifyTrue(myAccountPage.isRadiobuttonSelected(driver, "Male"));
		verifyEquals(myAccountPage.getTextboxValueByName(driver, "FirstName"), "Automation");
		verifyEquals(myAccountPage.getTextboxValueByName(driver, "LastName"), "FC");
		verifyEquals(myAccountPage.getSelectedOption(driver, "DateOfBirthDay"), "1");
		verifyEquals(myAccountPage.getSelectedOption(driver, "DateOfBirthMonth"), "January");
		verifyEquals(myAccountPage.getSelectedOption(driver, "DateOfBirthYear"), "1990");
		verifyEquals(myAccountPage.getTextboxValueByName(driver, "Email"), "automationfc.vn@gmail.com");
		verifyEquals(myAccountPage.getTextboxValueByName(driver, "Company"), "Automation FC");
	}

	@Test
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

		ExtentTestManager.getTest().log(Status.INFO, "Customer info - Step 07: Click 'Save' button");
		myAccountPage.clickToButtonByText(driver, "Save");

	}

	// @Test
	public void TC_03_Change_Password() {

		ExtentTestManager.getTest().log(Status.INFO, "Change password - Step 01: Open changep password tab");
		myAccountPage.openSidePageByText(driver, "Change password");

		ExtentTestManager.getTest().log(Status.INFO, "Change password - Step 02: Fill in Old pw textbox");
		myAccountPage.enterTextToTextboxByName(driver, "OldPassword", password);

		ExtentTestManager.getTest().log(Status.INFO, "Change password - Step 03: Fill in New pw textbox");
		myAccountPage.enterTextToTextboxByName(driver, "NewPassword", newPassword);

		ExtentTestManager.getTest().log(Status.INFO, "Change password - Step 04: Fill in Confirm pw textbox");
		myAccountPage.enterTextToTextboxByName(driver, "ConfirmNewPassword", newPassword);

		ExtentTestManager.getTest().log(Status.INFO, "Customer info - Step 05: Click 'Change password' button");
		myAccountPage.clickToButtonByText(driver, "Change password");

		ExtentTestManager.getTest().log(Status.INFO, "Customer info - Step 06: Verify success message");
		verifyEquals(myAccountPage.getSuccessMsgOnNotiBarByClass(driver, "bar-notification success"),
				"Password was changed");

		ExtentTestManager.getTest().log(Status.INFO, "Customer info - Step 06: Close success message");
		myAccountPage.closeNotiBarByText(driver);

		ExtentTestManager.getTest().log(Status.INFO, "Customer info - Step 07: Logout");
		myAccountPage.openHeaderFooterPageByText(driver, "Log out");
		homePage = PageGeneratorManager.getHompageObject(driver);

		ExtentTestManager.getTest().log(Status.INFO, "Customer info - Step 08: Log in with Old password ");
		myAccountPage.openHeaderFooterPageByText(driver, "Log in");

		loginPage = PageGeneratorManager.getLoginPageObject(driver);

		loginPage.enterTextToTextboxByName(driver, "Email", emailAddress);

		loginPage.enterTextToTextboxByName(driver, "Password", password);

		ExtentTestManager.getTest().log(Status.INFO, " - Step 03: Click 'Login' button");
		loginPage.clickToButtonByText(driver, "Log in");
		verifyEquals(loginPage.getUnsuccesfullLoginMessage(driver),
				"Login was unsuccessful. Please correct the errors and try again.\nThe credentials provided are incorrect");

		ExtentTestManager.getTest().log(Status.INFO, "Customer info - Step 08: Log in with New password ");
		loginPage.enterTextToTextboxByName(driver, "Password", newPassword);

		ExtentTestManager.getTest().log(Status.INFO, " - Step 03: Click 'Login' button");
		loginPage.clickToButtonByText(driver, "Log in");
		homePage = PageGeneratorManager.getHompageObject(driver);

	}

	@Test
	public void TC_04_My_Product_Review() {
		String reviewTitle = "Ky's review";
		String reviewText = "Pretty good";

		ExtentTestManager.getTest().log(Status.INFO, "My product review - Step 01: Open Desktop page ");
		homePage.openTopMenuSubPageByText(driver, "Computers ", "Desktops ");
		topMenuSubPage = PageGeneratorManager.getTopMenuSubPage(driver);

		ExtentTestManager.getTest().log(Status.INFO, "My product review - Step 02: Click to product title ");
		topMenuSubPage.clickToProductTitleByText(driver, "Build your own computer");
		productDetailPage = PageGeneratorManager.getProductDetailPageObject(driver);

		ExtentTestManager.getTest().log(Status.INFO, "My product review - Step 03: Click to Add your product detail link ");
		addYourReviewPage = productDetailPage.clickToAddYourReviewLinkByText(driver);

		ExtentTestManager.getTest().log(Status.INFO, "My product review - Step 04: Fill in Review title textbox ");
		addYourReviewPage.enterTextToTextboxByName(driver, "AddProductReview.Title", reviewTitle);

		ExtentTestManager.getTest().log(Status.INFO, "My product review - Step 05: Fill in 'Review text' textarea ");
		addYourReviewPage.enterTextToTextAreaByName(driver, "AddProductReview.ReviewText", reviewText);

		ExtentTestManager.getTest().log(Status.INFO, "My product review - Step 06: Select Rating ");
		addYourReviewPage.clickToRadioRatingButton(driver, "addproductrating_3");

		ExtentTestManager.getTest().log(Status.INFO, "My product review - Step 07: Click to Submit button ");
		addYourReviewPage.clickToButtonByText(driver, "Submit review");

		ExtentTestManager.getTest().log(Status.INFO, "My product review - Step 07: Open My product review page ");
		homePage.openHeaderFooterPageByText(driver, "My account");
		myAccountPage = PageGeneratorManager.getMyAccountPageObject(driver);

		myAccountPage.openSidePageByText(driver, "My product reviews");

		ExtentTestManager.getTest().log(Status.INFO, "My product review - Step 07: Verify product detail display ");
		verifyEquals(myAccountPage.getTextProductReviewByClass(driver, "review-title"), reviewTitle);
		verifyEquals(myAccountPage.getTextProductReviewByClass(driver, "review-text"), reviewText);

	}

	private int getRandomNumber() {
		Random rand = new Random();
		return rand.nextInt(99999);
	}

}
