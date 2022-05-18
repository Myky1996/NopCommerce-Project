package com.nopCommerce.user;

import java.util.Random;

import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

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

public class Module_04_Search extends BaseTest {
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
		newPassword = "buinhuhoa";
		
		log.info("Pre-condition - Step 01: Open browser '" + browserName + "' and navigate to '" + enviroment.userAppUrl() + "' ");
		driver = getBrowserDriver(envName, enviroment.userAppUrl(), browserName, ipAddress, portNumber, osName);
		homePage = PageGeneratorManager.getHompageObject(driver);

		log.info("Pre-condition - Step 02: Open 'Login' page");
		homePage.openHeaderFooterPageByText(driver, "Log in");
		loginPage = PageGeneratorManager.getLoginPageObject(driver);

		log.info("Pre-condition - Step 03: Set cookies and reload page");
		loginPage.setCookies(driver, Common_01_Register_to_system.loginPageCookie);

		loginPage.refreshCurrentPage(driver);
		homePage = PageGeneratorManager.getHompageObject(driver);
		loginPage.closeNotiBarByText(driver);

		homePage.openHeaderFooterPageByText(driver, "Search");
		searchPage = PageGeneratorManager.getSearchPage(driver);
	}

	@Test
	public void TC_01_Search_empty_data() {

		log.info("Search 01 - Step 01: click to search icon");
		searchPage.clickToSearchButton(driver);

		log.info("Search 01 - Step 02: verify error message");
		verifyEquals(searchPage.getValueWarningMsg(driver), "Search term minimum length is 3 characters");
	}

	@Test
	public void TC_02_Search_Data_Not_Exist() {
		log.info("Search 02 - Step 01: enter text to search field");
		searchPage.enterTextToSearchBox(driver, "Macbook Pro 2050");

		log.info("Search 02 - Step 02: click to search icon");
		searchPage.clickToSearchButton(driver);

		log.info("Search 02 - Step 03: verify error message");
		verifyEquals(searchPage.getValueNoResultMsg(driver), "No products were found that matched your criteria.");

	}

	@Test
	public void TC_03_Search_Product_Name() {
		log.info("Search 03 - Step 01: enter text to search field");
		searchPage.enterTextToSearchBox(driver, "Lenovo");

		log.info("Search 03 - Step 02: click to search icon");
		searchPage.clickToSearchButton(driver);

		log.info("Search 03 - Step 03: verify 2 products display");
		searchPage.isNumberOfProductsCorrect(driver, 2);
		searchPage.verifyProductTitleDisplay(driver, "Lenovo");

	}

	@Test
	public void TC_04_Search_Product_Name() {
		log.info("Search 04 - Step 01: enter text to search field");
		searchPage.enterTextToSearchBox(driver, "ThinkPad X1 Carbon");

		log.info("Search 04 - Step 02: click to search icon");
		searchPage.clickToSearchButton(driver);

		log.info("Search 04 - Step 03: verify only 1 product display");
		searchPage.isNumberOfProductsCorrect(driver, 1);
		searchPage.verifyProductTitleDisplay(driver, "ThinkPad X1 Carbon");
	}

	@Test
	public void TC_05_Advance_Search_Parent_Categories() {
		log.info("Search 05 - Step 01: enter text to search field");
		searchPage.enterTextToSearchBox(driver, "Apple Macbook Pro");

		log.info("Search 05 - Step 02: enter text to search field");
		searchPage.checkToCheckboxByLabel(driver, "Advanced search");

		log.info("Search 05 - Step 03: select category: Computer");
		searchPage.selectOptionInDropdownByText(driver, "cid", "Computers");

		log.info("Search 05 - Step 04: Uncheck to checkbox 'Automaticlly search sub categories");
		searchPage.uncheckToCheckboxByLabel(driver, "Automatically search sub categories");
		
		log.info("Search 06 - Step 05: verify 1 product display");
		searchPage.clickToSearchButton(driver);
		
		log.info("Search 05 - Step 06: verify error message display");
		verifyEquals(searchPage.getValueNoResultMsg(driver), "No products were found that matched your criteria.");

	}

	public void TC_06_Advance_Search_Sub_Categories() {
		log.info("Search 06 - Step 01: enter text to search field");
		searchPage.enterTextToSearchBox(driver, "Apple Macbook Pro");

		log.info("Search 06 - Step 02: enter text to search field");
		searchPage.checkToCheckboxByLabel(driver, "Advanced search");

		log.info("Search 06 - Step 03: select category: Computer");
		searchPage.selectOptionInDropdownByText(driver, "cid", "Computers");

		log.info("Search 06 - Step 04: check to checkbox 'Automaticlly search sub categories");
		searchPage.checkToCheckboxByLabel(driver, "Automatically search sub categories");
		
		log.info("Search 06 - Step 05: verify 1 product display");
		searchPage.clickToSearchButton(driver);
		
		log.info("Search 06 - Step 06: verify 1 product display");
		searchPage.isNumberOfProductsCorrect(driver, 1);
		searchPage.verifyProductTitleDisplay(driver, "Apple Macbook Pro");
		

	}

	public void TC_07_Advance_Search_Incorrect_Manufacturer() {
		log.info("Search 07 - Step 01: enter text to search field");
		searchPage.enterTextToSearchBox(driver, "Apple Macbook Pro");

		log.info("Search 07 - Step 02: enter text to search field");
		searchPage.checkToCheckboxByLabel(driver, "Advanced search");

		log.info("Search 07 - Step 03: select category: Computer");
		searchPage.selectOptionInDropdownByText(driver, "cid", "Computers");

		log.info("Search 07 - Step 04: check to checkbox 'Automaticlly search sub categories");
		searchPage.checkToCheckboxByLabel(driver, "Automatically search sub categories");

		log.info("Search 07 - Step 05: select incorrect manufacturer");
		searchPage.selectOptionInDropdownByText(driver, "mid", "HP");
		searchPage.clickToSearchButton(driver);
		log.info("Search 07 - Step 06: verify error message display");
		verifyEquals(searchPage.getValueNoResultMsg(driver), "No products were found that matched your criteria.");

	}

	public void TC_08_Advance_Search_Correct_Manufacturer() {
		log.info("Search 08 - Step 01: enter text to search field");
		searchPage.enterTextToSearchBox(driver, "Apple Macbook Pro");

		log.info("Search 08 - Step 02: enter text to search field");
		searchPage.checkToCheckboxByLabel(driver, "Advanced search");

		log.info("Search 08 - Step 03: select category: Computer");
		searchPage.selectOptionInDropdownByText(driver, "cid", "Computers");

		log.info("Search 08 - Step 04: check to checkbox 'Automaticlly search sub categories");
		searchPage.checkToCheckboxByLabel(driver, "Automatically search sub categories");
		
		log.info("Search 08 - Step 05: select correct manufacturer: Apple");
		searchPage.selectOptionInDropdownByText(driver, "mid", "Apple");
		
		log.info("Search 08 - Step 06: select correct manufacturer: Apple");
		searchPage.clickToSearchButton(driver);
		
		log.info("Search 08 - Step 07: verify 1 product display");
		searchPage.isNumberOfProductsCorrect(driver, 1);
		searchPage.verifyProductTitleDisplay(driver, "Apple Macbook Pro 13-inch");
	}

	private int getRandomNumber() {
		Random rand = new Random();
		return rand.nextInt(99999);
	}

}
