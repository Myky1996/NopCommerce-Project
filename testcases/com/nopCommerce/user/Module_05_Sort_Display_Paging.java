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
import pageObjects.nopCommerce.user.SearchPO;
import pageObjects.nopCommerce.user.TopMenuSubPagePO;
import pageObjects.nopCommerce.user.registerPO;
import reportConfig.ExtentTestManager;

public class Module_05_Sort_Display_Paging extends BaseTest {
	Enviroment enviroment;
	WebDriver driver;
	HomePageObject homePage;
	registerPO registerPage;
	LoginPO loginPage;
	TopMenuSubPagePO topMenuSubPage;
	MyAccountPO myAccountPage;
	ProductDetailPO productDetailPage;
	AddYourReviewPO addYourReviewPage;
	SearchPO searchPage;
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

		ExtentTestManager.getTest().log(Status.INFO, "Pre-condition - Step 01: Open browser '" + browserName + "' and navigate to '"
				+ enviroment.userAppUrl() + "' ");
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

		homePage.openTopMenuSubPageByText(driver, "Computers", "Notebooks");

	}

	@Test
	public void TC_01_Sort_By_Name_A_Z() {

		homePage.selectOptionInDropdownByText(driver, "products-orderby", "Name: A to Z");

		verifyTrue(homePage.isProductSortByAscending(driver));

	}

	@Test
	public void TC_02_Sort_By_Name_Z_A() {

		homePage.selectOptionInDropdownByText(driver, "products-orderby", "Name: Z to A");

		verifyTrue(homePage.isProductSortedByDescending(driver));
	}

	@Test
	public void TC_03_Sort_By_Price_Low_High() {
		homePage.selectOptionInDropdownByText(driver, "products-orderby", "Price: Low to High");
		verifyTrue(homePage.isPriceSortLowToHigh(driver));

	}

	@Test
	public void TC_04_Sort_By_Price_High_Low() {
		homePage.selectOptionInDropdownByText(driver, "products-orderby", "Price: High to Low");
		verifyTrue(homePage.isPriceSortHighToLow(driver));

	}

	@Test
	public void TC_05_Display_3_items_per_page() {
		homePage.selectOptionInDropdownByText(driver, "products-pagesize", "3");

		ExtentTestManager.getTest().log(Status.INFO, "Verify 3 items or less than 3 item per page");
		verifyTrue(homePage.isNumberOfProductsCorrect(driver, 3));

		ExtentTestManager.getTest().log(Status.INFO, "Verify next icon at page 1 ");
		verifyTrue(homePage.isNextIconDisplayed(driver));
		ExtentTestManager.getTest().log(Status.INFO, "Verify previous icon at page 2 ");
		verifyTrue(homePage.isPreviousIconDisplayed(driver));
	}

	@Test
	public void TC_06_Display_6_items_per_page() {
		homePage.selectOptionInDropdownByText(driver, "products-pagesize", "6");

		ExtentTestManager.getTest().log(Status.INFO, "Verify 6 items or less than 6 item per page");
		verifyTrue(homePage.isNumberOfProductsCorrect(driver, 6));

		ExtentTestManager.getTest().log(Status.INFO, "Verify paging is not displayed");
		verifyTrue(homePage.isPagingUndisplayed(driver));

	}

	@Test
	public void TC_07_Display_9_items_per_page() {
		homePage.selectOptionInDropdownByText(driver, "products-pagesize", "9");

		ExtentTestManager.getTest().log(Status.INFO, "Verify 9 items or less than 9 item per page");
		verifyTrue(homePage.isNumberOfProductsCorrect(driver, 9));

		ExtentTestManager.getTest().log(Status.INFO, "Verify paging is not displayed");
		verifyTrue(homePage.isPagingUndisplayed(driver));
	}

	private int getRandomNumber() {
		Random rand = new Random();
		return rand.nextInt(99999);
	}

}
