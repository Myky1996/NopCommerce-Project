package com.nopCommerce.admin;

import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import commons.PageGeneratorManager;
import pageObjects.nopCommerce.admin.HomepageAdminPO;
import pageObjects.nopCommerce.admin.LoginAdminPO;
import pageObjects.nopCommerce.admin.ProductDetailPO;
import pageObjects.nopCommerce.admin.ProductListPO;

public class Module_01_Search extends BaseTest {
	WebDriver driver;
	HomepageAdminPO homePage;
	LoginAdminPO adminLoginPage;
	ProductListPO productListPage;
	ProductDetailPO ProductDetailPage;
	String productName, SKU, price, stockQuantity;

	@Parameters({ "browser", "url" })
	@BeforeClass
	public void BeforeClass(String browserName, String appUrl) {
		productName = "Lenovo IdeaCentre 600 All-in-One PC";
		SKU = "LE_IC_600";
		price = "500";
		stockQuantity = "10000";

		log.info("Pre-condition - Step 01: Open browser '" + browserName + "' and navigate to '" + appUrl + "' ");

		driver = getBrowserDriver(browserName, appUrl);
		adminLoginPage = PageGeneratorManager.getLoginAdminPage(driver);

		log.info("Pre-condition - Step 02: Login with Admin account");
		adminLoginPage.enterTextToTextboxByName(driver, "Email", "admin@yourstore.com");
		adminLoginPage.enterTextToTextboxByName(driver, "Password", "admin");
		adminLoginPage.clickToButtonByText(driver, "Log in");
		homePage = PageGeneratorManager.gethomepageAdmin(driver);

		homePage.openSideMenuPageByText(driver, "Catalog");
		homePage.openSubSideMenuPageByText(driver, " Products");
		productListPage = PageGeneratorManager.getProductListPage(driver);
	}

	@Test
	public void TC_01_Search() {
		productListPage.enterTextToTextboxByName(driver, "SearchProductName", productName);
		productListPage.clickToButtonByText(driver, "Search");
		log.info("Search 01 - Verify search info");
		verifyEquals(productListPage.getValueInTableAtColumnTableAndRowIndex(driver, "Product name", "1"), productName);
		verifyEquals(productListPage.getValueInTableAtColumnTableAndRowIndex(driver, "SKU", "1"), SKU);
		verifyEquals(productListPage.getValueInTableAtColumnTableAndRowIndex(driver, "Price", "1"), price);
		verifyEquals(productListPage.getValueInTableAtColumnTableAndRowIndex(driver, "Stock quantity", "1"),
				stockQuantity);
		verifyTrue(productListPage.ischeckIconDisplayed(driver, "Published", "1", "true"));
	}

	@Test
	public void TC_02_Search_With_Product_Name_ParentCategory_Unchecked() {
		productListPage.enterTextToTextboxByName(driver, "SearchProductName", productName);
		productListPage.selectOptionInDropdownByText(driver, "SearchCategoryId", "Computers");
		productListPage.uncheckToCheckboxByLabel(driver, "SearchIncludeSubCategories");

		productListPage.clickToButtonByText(driver, "Search");

		log.info("Search 01 - Verify no data message");

		verifyEquals(productListPage.getEmptyMessageValue(driver), "No data available in table");
	}

	@Test
	public void TC_03_Search_With_Product_Name_ParentCategory_Checked() {
		productListPage.enterTextToTextboxByName(driver, "SearchProductName", productName);
		productListPage.selectOptionInDropdownByText(driver, "SearchCategoryId", "Computers");
		productListPage.checkToCheckboxByLabel(driver, "SearchIncludeSubCategories");

		productListPage.clickToButtonByText(driver, "Search");
		log.info("Search 01 - Verify search info");
		verifyEquals(productListPage.getValueInTableAtColumnTableAndRowIndex(driver, "Product name", "1"), productName);
		verifyEquals(productListPage.getValueInTableAtColumnTableAndRowIndex(driver, "SKU", "1"), SKU);
		verifyEquals(productListPage.getValueInTableAtColumnTableAndRowIndex(driver, "Price", "1"), price);
		verifyEquals(productListPage.getValueInTableAtColumnTableAndRowIndex(driver, "Stock quantity", "1"),
				stockQuantity);

	}

	@Test
	public void TC_04_Search_With_Product_Name_Child_Category() {
		productListPage.enterTextToTextboxByName(driver, "SearchProductName", productName);
		productListPage.selectOptionInDropdownByText(driver, "SearchCategoryId", "Computers >> Desktops");
		productListPage.uncheckToCheckboxByLabel(driver, "SearchIncludeSubCategories");

		productListPage.clickToButtonByText(driver, "Search");
		log.info("Search 01 - Verify search info");
		verifyEquals(productListPage.getValueInTableAtColumnTableAndRowIndex(driver, "Product name", "1"), productName);
		verifyEquals(productListPage.getValueInTableAtColumnTableAndRowIndex(driver, "SKU", "1"), SKU);
		verifyEquals(productListPage.getValueInTableAtColumnTableAndRowIndex(driver, "Price", "1"), price);
		verifyEquals(productListPage.getValueInTableAtColumnTableAndRowIndex(driver, "Stock quantity", "1"),
				stockQuantity);

	}

	@Test
	public void TC_05_Search_With_Manufactuters() {
		productListPage.enterTextToTextboxByName(driver, "SearchProductName", productName);
		productListPage.selectOptionInDropdownByText(driver, "SearchCategoryId", "All");
		productListPage.uncheckToCheckboxByLabel(driver, "SearchIncludeSubCategories");
		productListPage.selectOptionInDropdownByText(driver, "SearchManufacturerId", "Apple");

		productListPage.clickToButtonByText(driver, "Search");
		log.info("Search 01 - Verify no data message");

		verifyEquals(productListPage.getEmptyMessageValue(driver), "No data available in table");
	}
	@Test
	public void TC_06_Search_With_SKU() {
		productListPage.enterTextToTextboxByName(driver, "GoDirectlyToSku", SKU);
		
		productListPage.clickToButtonByText(driver, "Go");
		log.info("Search 01 - Verify product detail page displays");
		ProductDetailPage = PageGeneratorManager.getProductDetailPageAdmin(driver);

		verifyEquals(productListPage.getPageTitle(driver), "Edit product details - Lenovo IdeaCentre 600 All-in-One PC");
		verifyEquals(productListPage.getTextboxValueByName(driver, "Name"),"Lenovo IdeaCentre 600 All-in-One PC");
	}

	private int getRandomNumber() {
		Random rand = new Random();
		return rand.nextInt(99999);
	}

}
