package com.nopCommerce.admin;

import java.util.Random;

import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import commons.BaseTest;
import commons.PageGeneratorManager;
import enviromentConfig.Enviroment;
import pageObjects.nopCommerce.admin.HomepageAdminPO;
import pageObjects.nopCommerce.admin.LoginAdminPO;
import pageObjects.nopCommerce.admin.ProductDetailPO;
import pageObjects.nopCommerce.admin.ProductListPO;
import reportConfig.ExtentTestManager;

public class Module_01_Search extends BaseTest {
	Enviroment enviroment;
	WebDriver driver;
	HomepageAdminPO homePage;
	LoginAdminPO adminLoginPage;
	ProductListPO productListPage;
	ProductDetailPO ProductDetailPage;
	String productName, SKU, price, stockQuantity;

	@Parameters({ "envName", "severName", "browser", "ipAddress", "portNumber", "osName" })
	@BeforeClass
	public void BeforeClass(@Optional("local") String envName, @Optional("testing") String severName,
			@Optional("chrome") String browserName, @Optional("localhost") String ipAddress,
			@Optional("4444") String portNumber, @Optional("Windows 10") String osName) {
		ConfigFactory.setProperty("env", severName);
		enviroment = ConfigFactory.create(Enviroment.class);
		productName = "Lenovo IdeaCentre 600 All-in-One PC";
		SKU = "LE_IC_600";
		price = "500";
		stockQuantity = "10000";

		ExtentTestManager.getTest().log(Status.INFO, "Pre-condition - Step 01: Open browser '" + browserName + "' and navigate to '"
				+ enviroment.adminAppUrl() + "' ");

		driver = getBrowserDriver(envName, enviroment.adminAppUrl(), browserName, ipAddress, portNumber, osName);
		adminLoginPage = PageGeneratorManager.getLoginAdminPage(driver);

		ExtentTestManager.getTest().log(Status.INFO, "Pre-condition - Step 02: Login with Admin account");
		adminLoginPage.enterTextToTextboxByName(driver, "Email", enviroment.adminAppname());
		adminLoginPage.enterTextToTextboxByName(driver, "Password", enviroment.adminAppPassword());
		adminLoginPage.clickToButtonByText(driver, "Log in");
		homePage = PageGeneratorManager.gethomepageAdmin(driver);

		ExtentTestManager.getTest().log(Status.INFO, "Pre-condition - Step 03: Open Products page");
		homePage.openSideMenuPageByText(driver, "Catalog");
		homePage.openSubSideMenuPageByText(driver, " Products");
		productListPage = PageGeneratorManager.getProductListPage(driver);
	}

	@Test
	public void TC_01_Search() {
		ExtentTestManager.getTest().log(Status.INFO, "Search 01 - Step 1: Enter text into product name textbox");
		productListPage.enterTextToTextboxByName(driver, "SearchProductName", productName);
		ExtentTestManager.getTest().log(Status.INFO, "Search 01 - Step 2: Click search button");
		productListPage.clickToButtonByText(driver, "Search");
		ExtentTestManager.getTest().log(Status.INFO, "Search 01 - Step 3: Verify search info");
		verifyEquals(productListPage.getValueInTableAtColumnTableAndRowIndexAndCardTitle(driver,
				"products-grid_wrapper", "Product name", "1"), productName);
		verifyEquals(productListPage.getValueInTableAtColumnTableAndRowIndexAndCardTitle(driver,
				"products-grid_wrapper", "SKU", "1"), SKU);
		verifyEquals(productListPage.getValueInTableAtColumnTableAndRowIndexAndCardTitle(driver,
				"products-grid_wrapper", "Price", "1"), price);
		verifyEquals(productListPage.getValueInTableAtColumnTableAndRowIndexAndCardTitle(driver,
				"products-grid_wrapper", "Stock quantity", "1"), stockQuantity);
		verifyTrue(productListPage.ischeckIconDisplayed(driver, "products-grid_wrapper", "Published", "1", "true"));
	}

	@Test
	public void TC_02_Search_With_Product_Name_ParentCategory_Unchecked() {
		ExtentTestManager.getTest().log(Status.INFO, "Search 01 - Step 1: Enter text into product name textbox");
		productListPage.enterTextToTextboxByName(driver, "SearchProductName", productName);
		
		productListPage.selectOptionInDropdownByText(driver, "SearchCategoryId", "Computers");
		productListPage.uncheckToCheckboxByLabelOnAdminPage(driver, "SearchIncludeSubCategories");
		
		ExtentTestManager.getTest().log(Status.INFO, "Search 01 - Step 2: Click search button");
		productListPage.clickToButtonByText(driver, "Search");

		ExtentTestManager.getTest().log(Status.INFO, "Search 02 - Verify no data message");

		verifyEquals(productListPage.getEmptyMsgAtTableByText(driver,"products-grid_wrapper"), "No data available in table");
	}

	@Test
	public void TC_03_Search_With_Product_Name_ParentCategory_Checked() {
		ExtentTestManager.getTest().log(Status.INFO, "Search 01 - Step 1: Enter text into product name textbox");
		productListPage.enterTextToTextboxByName(driver, "SearchProductName", productName);
		productListPage.selectOptionInDropdownByText(driver, "SearchCategoryId", "Computers");
		productListPage.checkToCheckboxByLabelOnAdminPage(driver, "SearchIncludeSubCategories");
		ExtentTestManager.getTest().log(Status.INFO, "Search 01 - Step 2: Click search button");
		productListPage.clickToButtonByText(driver, "Search");
		ExtentTestManager.getTest().log(Status.INFO, "Search 03 - Verify search info");
		verifyEquals(productListPage.getValueInTableAtColumnTableAndRowIndexAndCardTitle(driver,
				"products-grid_wrapper", "Product name", "1"), productName);
		verifyEquals(productListPage.getValueInTableAtColumnTableAndRowIndexAndCardTitle(driver,
				"products-grid_wrapper", "SKU", "1"), SKU);
		verifyEquals(productListPage.getValueInTableAtColumnTableAndRowIndexAndCardTitle(driver,
				"products-grid_wrapper", "Price", "1"), price);
		verifyEquals(productListPage.getValueInTableAtColumnTableAndRowIndexAndCardTitle(driver,
				"products-grid_wrapper", "Stock quantity", "1"), stockQuantity);

	}

	@Test
	public void TC_04_Search_With_Product_Name_Child_Category() {
		ExtentTestManager.getTest().log(Status.INFO, "Search 01 - Step 1: Enter text into product name textbox");
		productListPage.enterTextToTextboxByName(driver, "SearchProductName", productName);
		productListPage.selectOptionInDropdownByText(driver, "SearchCategoryId", "Computers >> Desktops");
		productListPage.checkToCheckboxByLabelOnAdminPage(driver, "SearchIncludeSubCategories");
		ExtentTestManager.getTest().log(Status.INFO, "Search 01 - Step 2: Click search button");
		productListPage.clickToButtonByText(driver, "Search");
		ExtentTestManager.getTest().log(Status.INFO, "Search 04 - Verify search info");
		verifyEquals(productListPage.getValueInTableAtColumnTableAndRowIndexAndCardTitle(driver,
				"products-grid_wrapper", "Product name", "1"), productName);
		verifyEquals(productListPage.getValueInTableAtColumnTableAndRowIndexAndCardTitle(driver,
				"products-grid_wrapper", "SKU", "1"), SKU);
		verifyEquals(productListPage.getValueInTableAtColumnTableAndRowIndexAndCardTitle(driver,
				"products-grid_wrapper", "Price", "1"), price);
		verifyEquals(productListPage.getValueInTableAtColumnTableAndRowIndexAndCardTitle(driver,
				"products-grid_wrapper", "Stock quantity", "1"), stockQuantity);

	}

	@Test
	public void TC_05_Search_With_Manufactuters() {
		ExtentTestManager.getTest().log(Status.INFO, "Search 01 - Step 1: Enter text into product name textbox");
		productListPage.enterTextToTextboxByName(driver, "SearchProductName", productName);
		productListPage.selectOptionInDropdownByText(driver, "SearchCategoryId", "All");
		productListPage.checkToCheckboxByLabelOnAdminPage(driver, "SearchIncludeSubCategories");
		productListPage.selectOptionInDropdownByText(driver, "SearchManufacturerId", "Apple");
		ExtentTestManager.getTest().log(Status.INFO, "Search 01 - Step 2: Click search button");
		productListPage.clickToButtonByText(driver, "Search");
		ExtentTestManager.getTest().log(Status.INFO, "Search 05 - Verify no data message");

		verifyEquals(productListPage.getEmptyMsgAtTableByText(driver,"products-grid_wrapper"), "No data available in table");
	}

	@Test
	public void TC_06_Search_With_SKU() {
		ExtentTestManager.getTest().log(Status.INFO, "Search 06 - Step 1: Enter text into SKU textbox");
		productListPage.enterTextToTextboxByName(driver, "GoDirectlyToSku", SKU);
		
		ExtentTestManager.getTest().log(Status.INFO, "Search 06 - Step 2: Click Go button");
		productListPage.clickToButtonByText(driver, "Go");
		ProductDetailPage = PageGeneratorManager.getProductDetailPageAdmin(driver);
		
		ExtentTestManager.getTest().log(Status.INFO, "Search 06 - Step 3: Verify product detail page displays");
		verifyTrue(productListPage.isPageTitleDisplayed(driver,"Lenovo IdeaCentre 600 All-in-One PC"));
		verifyEquals(productListPage.getTextboxValueByName(driver, "Name"), "Lenovo IdeaCentre 600 All-in-One PC");
	}

	private int getRandomNumber() {
		Random rand = new Random();
		return rand.nextInt(99999);
	}

}
