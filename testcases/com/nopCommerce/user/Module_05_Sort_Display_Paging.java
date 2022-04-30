package com.nopCommerce.user;

import java.util.ArrayList;
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
import pageObjects.nopCommerce.user.SearchPO;
import pageObjects.nopCommerce.user.TopMenuSubPagePO;
import pageObjects.nopCommerce.user.registerPO;

public class Module_05_Sort_Display_Paging extends BaseTest {
	WebDriver driver;
	HomePageObject homePage;
	registerPO registerPage;
	LoginPO loginPage;
	TopMenuSubPagePO topMenuSubPage;
	MyAccountPO myAccountPage;
	ProductDetailPO productDetailPage;
	AddYourReviewPO addYourReviewPage;
	SearchPO searchPage;
	String password, existingEmail, newPassword;

	@Parameters({ "browser", "url" })
	@BeforeClass
	public void BeforeClass(String browserName, String appUrl) {

		existingEmail = "kinu@yopmail.com";
		password = "kinu@yopmail.com";
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

		log.info("Verify 3 items or less than 3 item per page");
		verifyTrue(homePage.isNumberOfProductsCorrect(driver, 3));

		log.info("Verify next icon at page 1 ");
		verifyTrue(homePage.isNextIconDisplayed(driver));
		log.info("Verify previous icon at page 2 ");
		verifyTrue(homePage.isPreviousIconDisplayed(driver));
	}

	@Test
	public void TC_06_Display_6_items_per_page() {
		homePage.selectOptionInDropdownByText(driver, "products-pagesize", "6");

		log.info("Verify 6 items or less than 6 item per page");
		verifyTrue(homePage.isNumberOfProductsCorrect(driver, 6));
		
		log.info("Verify paging is not displayed");
		verifyTrue(homePage.isPagingUndisplayed(driver));

	}
	@Test
	public void TC_07_Display_9_items_per_page() {
		homePage.selectOptionInDropdownByText(driver, "products-pagesize", "9");

		log.info("Verify 9 items or less than 9 item per page");
		verifyTrue(homePage.isNumberOfProductsCorrect(driver, 9));
		
		log.info("Verify paging is not displayed");
		verifyTrue(homePage.isPagingUndisplayed(driver));
	}

	private int getRandomNumber() {
		Random rand = new Random();
		return rand.nextInt(99999);
	}

}
